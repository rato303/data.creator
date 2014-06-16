package rato.data.creator.service.setting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.ConfigurationBo;
import rato.data.creator.bo.InputValue;
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

		Properties properties = this.propertiesFileLoad(jdbcConfigFile);

		if (StringUtils.isBlank(properties.getProperty("jdbc.driver.class"))) {
			this.throwRetryException("error.jdbc.driver.class.name.empty");
		}

		if (StringUtils.isBlank(properties.getProperty("jdbc.url"))) {
			this.throwRetryException("error.jdbc.url.empty");
		}

		if (StringUtils.isBlank(properties.getProperty("jdbc.schema"))) {
			this.throwRetryException("error.jdbc.schema.empty");
		}

		if (StringUtils.isBlank(properties.getProperty("jdbc.user"))) {
			this.throwRetryException("error.jdbc.user.empty");
		}

		if (StringUtils.isBlank(properties.getProperty("jdbc.password"))) {
			this.throwRetryException("error.jdbc.password.empty");
		}

		// TODO jdbcドライバクラスが読み込めるかチェック
		// TODO jdbcurl、スキーマ名、ユーザー名、パスワードの組み合わせで接続できるかチェック
	}

	@Override
	protected CommandLineServiceResultBo configurationMainProcess(
			ConfigurationBo configurationBo, InputValue inputValue) {
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

	/**
	 * <p>
	 * Propertiesファイルを読み込みます。
	 * </p>
	 *
	 * @param jdbcConfigFile
	 *            データベース接続情報{@link File}
	 *
	 * @return 読み込んだPropertiesのインスタンス
	 */
	private Properties propertiesFileLoad(File jdbcConfigFile) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(jdbcConfigFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

}
