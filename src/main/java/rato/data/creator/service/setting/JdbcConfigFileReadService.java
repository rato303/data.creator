package rato.data.creator.service.setting;

import static rato.data.creator.config.DataBaseConfig.PROPERTY_KEY_JDBC_DRIVER_CLASS;
import static rato.data.creator.config.DataBaseConfig.PROPERTY_KEY_JDBC_PASSWORD;
import static rato.data.creator.config.DataBaseConfig.PROPERTY_KEY_JDBC_SCHEMA;
import static rato.data.creator.config.DataBaseConfig.PROPERTY_KEY_JDBC_URL;
import static rato.data.creator.config.DataBaseConfig.PROPERTY_KEY_JDBC_USER;
import static rato.data.creator.util.ResourceUtil.propertiesFileLoad;

import java.io.File;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.InputValue;
import rato.data.creator.config.DataBaseConfig;
import rato.data.creator.exception.RetryException;
import rato.data.creator.service.BaseCommandLineService;
import rato.data.creator.service.factory.DistDirectoryPathInputServiceFactory;
import rato.data.creator.service.factory.JdbcConfigFileReadServiceFactory;
import rato.data.creator.util.ResourceUtil;

/**
 * <p>
 * テーブル定義ファイルのファイルパスを処理するサービスクラスです。
 * </p>
 *
 * @author toshiya
 */
public class JdbcConfigFileReadService extends BaseCommandLineService {

	@Override
	protected String getQuestionMessage(ResourceBundle bundle) {
		return MessageFormat.format(
				bundle.getString("question.table.conf.file"),
				this.getDefaultPath());
	}

	@Override
	protected void validateProcess(CommandLineServiceResultBo beforeResult,
			InputValue inputValue) {

		String tagetPath;

		if (inputValue.isEmpty()) {
			tagetPath = this.getDefaultPath();
		} else {
			tagetPath = inputValue.getValue();
		}

		File jdbcConfigFile = new File(tagetPath);

		if (!jdbcConfigFile.exists()) {
			this.throwRetryException("error.jdbc.file.not.found", beforeResult);
		}

		Properties properties = propertiesFileLoad(jdbcConfigFile);

		if (StringUtils.isBlank(properties
				.getProperty(PROPERTY_KEY_JDBC_DRIVER_CLASS))) {
			this.throwRetryException("error.jdbc.driver.class.name.empty",
					beforeResult);
		}

		if (StringUtils.isBlank(properties.getProperty(PROPERTY_KEY_JDBC_URL))) {
			this.throwRetryException("error.jdbc.url.empty", beforeResult);
		}

		if (StringUtils.isBlank(properties
				.getProperty(PROPERTY_KEY_JDBC_SCHEMA))) {
			this.throwRetryException("error.jdbc.schema.empty", beforeResult);
		}

		if (StringUtils.isBlank(properties.getProperty(PROPERTY_KEY_JDBC_USER))) {
			this.throwRetryException("error.jdbc.user.empty", beforeResult);
		}

		if (StringUtils.isBlank(properties
				.getProperty(PROPERTY_KEY_JDBC_PASSWORD))) {
			this.throwRetryException("error.jdbc.password.empty", beforeResult);
		}

		// TODO jdbcドライバクラスが読み込めるかチェック
		// TODO jdbcurl、スキーマ名、ユーザー名、パスワードの組み合わせで接続できるかチェック
	}

	@Override
	protected CommandLineServiceResultBo mainProcess(
			CommandLineServiceResultBo beforeResult, InputValue inputValue) {

		String tagetPath = StringUtils.defaultIfBlank(inputValue.getValue(),
				this.getDefaultPath());

		return CommandLineServiceResultBo
				.create(beforeResult)
				.setFactory(new DistDirectoryPathInputServiceFactory())
				.setDatabaseConfig(
						new DataBaseConfig(propertiesFileLoad(tagetPath)));
	}

	/**
	 * <p>
	 * {@link RetryException}にメッセージキーを設定してthrowします。
	 * </p>
	 *
	 * @param messageKey
	 *            {@link RetryException}に設定するメッセージキー
	 * @param beforeResult
	 *            TODO
	 */
	private void throwRetryException(String messageKey,
			CommandLineServiceResultBo beforeResult) {
		throw new RetryException(messageKey,
				beforeResult.setFactory(new JdbcConfigFileReadServiceFactory()));
	}

	/**
	 * データベース接続情報ファイルのデフォルトパスを取得します。
	 *
	 * @return データベース接続情報ファイルのデフォルトパス
	 */
	private String getDefaultPath() {
		return ResourceUtil.getExecutePath("..", "config", "jdbc.properties");
	}

}
