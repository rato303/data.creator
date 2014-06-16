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
        System.out.println(bundle.getString(this.getQuestionMessageKey()));
    }

    /* (non-Javadoc)
     * @see rato.data.creator.service.CommandLineService#execute(rato.data.creator.bo.InputValue)
     */
    @Override
    public final CommandLineServiceResultBo execute(InputValue inputValue) {
        CommandLineServiceResultBo result;

        if ("q".equals(inputValue.getValue())) {    // TODO 列挙型にする
            return new CommandLineServiceResultBo();
        }

        result = this.doValidate(inputValue);

        if (result == null) {
            result = this.mainProcess(inputValue);
        }

        return result;
    }

    /**
     * 各コマンドライン処理のメッセージキーを取得します。
     *
     * @return 各コマンドライン処理のメッセージキー
     */
    protected abstract String getQuestionMessageKey();

    /**
     * 入力された値のチェックを行います。
     *
     * @param inputValue 入力された値
     */
    protected abstract void validateProcess(InputValue inputValue);

    /**
     * 各コマンドライン処理をするサービスの主処理を実行します。
     *
     * @param inputValue コマンドラインから入力された値
     *
     * @return コマンドラインの処理結果
     */
    protected abstract CommandLineServiceResultBo mainProcess(InputValue inputValue);

    private CommandLineServiceResultBo doValidate(InputValue inputValue) {
        try {
            this.validateProcess(inputValue);
        } catch (RetryException e) {
            ResourceBundle bundle = ResourceBundle.getBundle("message");
            System.out.println(bundle.getString(e.getMessage()));
            return e.getCommandLineServiceResultBo();
        }
        return null;    // TODO CommandLineServiceResultBoにNullパターンオブジェクトを適用する
    }

}
