package rato.data.creator.service;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.InputValue;
import rato.data.creator.service.factory.DistDirectoryPathInputServiceFactory;

/**
 * <p>
 * テーブル定義ファイルのファイルパスを処理するサービスクラスです。
 * </p>
 *
 * @author toshiya
 */
public class TableConfFilePathInputService extends BaseCommandLineService {

    /**
     * コンストラクタ
     */
    public TableConfFilePathInputService() {
        super();
    }

    @Override
    protected CommandLineServiceResultBo mainProcess(InputValue inputValue) {
        return new CommandLineServiceResultBo(new DistDirectoryPathInputServiceFactory());
    }

    @Override
    protected String getQuestionMessageKey() {
        return "question.table.conf.file";
    }

}
