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
    public void question() {
        System.out.println("処理結果の出力ディレクトリを入力してください。");
        System.out.println("q:終了する");
    }

    @Override
    protected CommandLineServiceResultBo mainProcess(InputValue inputValue) {
        return new CommandLineServiceResultBo();
    }

}
