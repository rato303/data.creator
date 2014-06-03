package rato.data.creator.service;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.InputValue;

/**
 * <p>生成物の出力先のディレクトリパスを処理するサービスクラスです。</p>
 *
 * @author toshiya
 */
public class DistDirectoryPathInputService extends BaseCommandLineService {

    /**
     * コンストラクタ
     */
    public DistDirectoryPathInputService() {
        super();
    }

    @Override
    protected CommandLineServiceResultBo mainProcess(InputValue inputValue) {
        return new CommandLineServiceResultBo();
    }

    @Override
    protected String getQuestionMessageKey() {
        return "question.dist.dir";
    }

}
