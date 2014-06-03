package rato.data.creator.service.setting;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.ConfigurationBo;
import rato.data.creator.bo.InputValue;

/**
 * <p>生成物の出力先のディレクトリパスを処理するサービスクラスです。</p>
 *
 * @author toshiya
 */
public class DistDirectoryPathInputService extends SettingCommandLineService {

    /**
     * コンストラクタ
     */
    public DistDirectoryPathInputService() {
        super();
    }

    @Override
    protected String getQuestionMessageKey() {
        return "question.dist.dir";
    }

    @Override
    protected void doValidate(InputValue inputValue) {
        // TODO 自動生成されたメソッド・スタブ
    }

    @Override
    protected CommandLineServiceResultBo configurationMainProcess(ConfigurationBo configurationBo, InputValue inputValue) {
        // TODO 自動生成されたメソッド・スタブ
        return new CommandLineServiceResultBo();
    }

}
