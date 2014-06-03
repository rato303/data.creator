package rato.data.creator.service;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.InputValue;

/**
 * <p>コマンドライン処理をするサービスの基底クラスです。</p>
 *
 * @author toshiya
 */
public abstract class BaseCommandLineService implements CommandLineService {

    /* (non-Javadoc)
     * @see rato.data.creator.service.CommandLineService#execute(rato.data.creator.bo.InputValue)
     */
    @Override
    public CommandLineServiceResultBo execute(InputValue inputValue) {

        if ("q".equals(inputValue.getValue())) {    // TODO 列挙型にする
            return new CommandLineServiceResultBo();
        }

        return this.mainProcess(inputValue);
    }

    /**
     * 各コマンドライン処理をするサービスの主処理を実行します。
     *
     * @param inputValue コマンドラインから入力された値
     *
     * @return コマンドラインの処理結果
     */
    protected abstract CommandLineServiceResultBo mainProcess(InputValue inputValue);

}
