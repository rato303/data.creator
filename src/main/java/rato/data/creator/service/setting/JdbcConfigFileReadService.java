package rato.data.creator.service.setting;

import static rato.data.creator.config.DataBaseConfig.*;
import static rato.data.creator.util.ResourceUtil.*;

import java.io.File;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.ConfigurationBo;
import rato.data.creator.bo.InputValue;
import rato.data.creator.config.DataBaseConfig;
import rato.data.creator.exception.RetryException;
import rato.data.creator.service.factory.DistDirectoryPathInputServiceFactory;
import rato.data.creator.service.factory.JdbcConfigFileReadServiceFactory;

/**
 * <p>
 * テーブル定義ファイルのファイルパスを処理するサービスクラスです。
 * </p>
 *
 * @author toshiya
 */
public class JdbcConfigFileReadService extends SettingCommandLineService {

	/**
	 * {@link JdbcConfigFileReadService}を生成します。
	 */
	public JdbcConfigFileReadService() {
		super(new ConfigurationBo());
	}

	/**
	 * アプリケーションの設定情報を保持した{@link JdbcConfigFileReadService}を生成します。
	 *
	 * @param configurationBo
	 *            アプリケーションの設定情報
	 */
	public JdbcConfigFileReadService(ConfigurationBo configurationBo) {
		super(configurationBo);
	}

	@Override
	protected String getQuestionMessageKey() {
		return "question.table.conf.file";
	}

	@Override
	protected void validateProcess(InputValue inputValue) {

		if (inputValue.isEmpty()) {
			this.throwRetryException("error.jdbc.file.path.empty");
		}

		File jdbcConfigFile = new File(inputValue.getValue());

		if (!jdbcConfigFile.exists()) {
			this.throwRetryException("error.jdbc.file.not.found");
		}

		Properties properties = propertiesFileLoad(jdbcConfigFile);

		if (StringUtils.isBlank(properties
				.getProperty(PROPERTY_KEY_JDBC_DRIVER_CLASS))) {
			this.throwRetryException("error.jdbc.driver.class.name.empty");
		}

		if (StringUtils.isBlank(properties.getProperty(PROPERTY_KEY_JDBC_URL))) {
			this.throwRetryException("error.jdbc.url.empty");
		}

		if (StringUtils.isBlank(properties
				.getProperty(PROPERTY_KEY_JDBC_SCHEMA))) {
			this.throwRetryException("error.jdbc.schema.empty");
		}

		if (StringUtils.isBlank(properties.getProperty(PROPERTY_KEY_JDBC_USER))) {
			this.throwRetryException("error.jdbc.user.empty");
		}

		if (StringUtils.isBlank(properties
				.getProperty(PROPERTY_KEY_JDBC_PASSWORD))) {
			this.throwRetryException("error.jdbc.password.empty");
		}

		// TODO jdbcドライバクラスが読み込めるかチェック
		// TODO jdbcurl、スキーマ名、ユーザー名、パスワードの組み合わせで接続できるかチェック
	}

	@Override
	protected CommandLineServiceResultBo configurationMainProcess(
			ConfigurationBo configurationBo, InputValue inputValue) {

		configurationBo.setDataBaseConfig(new DataBaseConfig(
				propertiesFileLoad(inputValue.getValue())));

		return new CommandLineServiceResultBo(
				new DistDirectoryPathInputServiceFactory(configurationBo));
	}

	/**
	 * <p>
	 * {@link RetryException}にメッセージキーを設定してthrowします。
	 * </p>
	 *
	 * @param messageKey
	 *            {@link RetryException}に設定するメッセージキー
	 */
	private void throwRetryException(String messageKey) {
		throw new RetryException(messageKey,
				new JdbcConfigFileReadServiceFactory());
	}

}
