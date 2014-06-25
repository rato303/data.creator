package rato.data.creator.service.cli.base;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.InputValue;

/**
 * <p>コマンドライン処理をするサービスのインタフェースです。</p>
 *
 * @author toshiya
 */
public interface CommandLineService {

    /**
     * コマンドライン処理の質問を出力します。
     *
     * @param beforeResult 1つ前のサービスの処理結果
     */
    void question(CommandLineServiceResultBo beforeResult);

    /**
     * コマンドライン処理を実行します。
     *
     * @param beforeResult 1つ前のサービスの処理結果
     *
     * @param inputValue コマンドラインから入力された値
     *
     * @return コマンドラインの処理結果
     */
    CommandLineServiceResultBo execute(CommandLineServiceResultBo beforeResult, InputValue inputValue);

}
