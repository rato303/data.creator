package rato.data.creator.service.setting;

import java.io.File;

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
     * @param configurationBo アプリケーションの設定情報
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
            throw new RetryException("error.jdbc.file.path.empty", new JdbcConfigFileReadServiceFactory());
        }

        File jdbcConfigFile = new File(inputValue.getValue());

        if (!jdbcConfigFile.exists()) {
            throw new RetryException("error.jdbc.file.not.found", new JdbcConfigFileReadServiceFactory());
        }

        /*
         * 設定ファイル内容チェック
         * ・jdbcドライバクラス、スキーマ名、ユーザー名、パスワードが設定されているかチェック
         * ・jdbcドライバクラスが読み込めるかチェック
         * ・jdbcurl、スキーマ名、ユーザー名、パスワードの組み合わせで接続できるかチェック
         */
    }

    @Override
    protected CommandLineServiceResultBo configurationMainProcess(ConfigurationBo configurationBo, InputValue inputValue) {
        return new CommandLineServiceResultBo(new DistDirectoryPathInputServiceFactory(configurationBo));
    }

}
