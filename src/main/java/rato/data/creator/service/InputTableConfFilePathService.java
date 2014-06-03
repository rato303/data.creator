package rato.data.creator.service;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.InputValue;

/**
 * <p>テーブル定義ファイルのファイルパスを処理するサービスクラスです。</p>
 *
 * @author toshiya
 */
public class InputTableConfFilePathService extends BaseCommandLineService {

    /**
     * コンストラクタ
     */
    public InputTableConfFilePathService() {
        super();
    }

    @Override
    public void question() {
        System.out.println("テーブル定義ファイルのファイルパスを入力してください。");
        System.out.println("q:終了する");
    }

    @Override
    protected CommandLineServiceResultBo mainProcess(InputValue inputValue) {
        CommandLineServiceResultBo result = new CommandLineServiceResultBo();
        return result;
    }

}
