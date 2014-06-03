package rato.data.creator.service.setting;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.ConfigurationBo;
import rato.data.creator.bo.InputValue;
import rato.data.creator.service.factory.DistDirectoryPathInputServiceFactory;

/**
 * <p>
 * テーブル定義ファイルのファイルパスを処理するサービスクラスです。
 * </p>
 *
 * @author toshiya
 */
public class TableConfFilePathInputService extends SettingCommandLineService {

    /**
     * コンストラクタ
     */
    public TableConfFilePathInputService() {
        super(null);
    }

    @Override
    protected String getQuestionMessageKey() {
        return "question.table.conf.file";
    }

    @Override
    protected void doValidate(InputValue inputValue) {
        // TODO 自動生成されたメソッド・スタブ
    }

    @Override
    protected CommandLineServiceResultBo configurationMainProcess(ConfigurationBo configurationBo, InputValue inputValue) {
        return new CommandLineServiceResultBo(new DistDirectoryPathInputServiceFactory(configurationBo));
    }

}
