package rato.data.creator.service;

import java.util.ResourceBundle;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.InputValue;
import rato.data.creator.exception.RetryException;

/**
 * <p>コマンドライン処理をするサービスの基底クラスです。</p>
 *
 * @author toshiya
 */
public abstract class BaseCommandLineService implements CommandLineService {

    /* (non-Javadoc)
     * @see rato.data.creator.service.CommandLineService#question()
     */
    @Override
    public final void question() {
        ResourceBundle bundle = ResourceBundle.getBundle("message");
        System.out.println(this.getQuestionMessage(bundle));
    }

    /* (non-Javadoc)
     * @see rato.data.creator.service.CommandLineService#execute(rato.data.creator.bo.InputValue)
     */
    @Override
    public final CommandLineServiceResultBo execute(CommandLineServiceResultBo beforeResult, InputValue inputValue) {
        CommandLineServiceResultBo result;

        if ("q".equals(inputValue.getValue())) {    // TODO 列挙型にする
            return new CommandLineServiceResultBo();
        }

        result = this.doValidate(inputValue);

        if (result == null) {
            result = this.mainProcess(beforeResult, inputValue);
        }

        return result;
    }

    /**
     * 各コマンドライン処理のメッセージキーを取得します。
     *
     * @param bundle メッセージ取得用リソース
     *
     * @return 各コマンドライン処理のメッセージキー
     */
    protected abstract String getQuestionMessage(ResourceBundle bundle);

    /**
     * 入力された値のチェックを行います。
     *
     * @param inputValue 入力された値
     */
    protected abstract void validateProcess(InputValue inputValue);

    /**
     * 各コマンドライン処理をするサービスの主処理を実行します。
     *
     * @param beforeResult 1つ前のサービスの処理結果
     *
     * @param inputValue コマンドラインから入力された値
     *
     * @return コマンドラインの処理結果
     */
    protected abstract CommandLineServiceResultBo mainProcess(CommandLineServiceResultBo beforeResult, InputValue inputValue);

    private CommandLineServiceResultBo doValidate(InputValue inputValue) {
        try {
            this.validateProcess(inputValue);
        } catch (RetryException e) {
            System.out.println(e.getMessage());
            return e.getCommandLineServiceResultBo();
        }
        return null;    // TODO CommandLineServiceResultBoにNullパターンオブジェクトを適用する
    }

}
