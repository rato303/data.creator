package rato.data.creator.service;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.ConfigurationBo;
import rato.data.creator.bo.InputValue;

/**
 * <p>コマンドライン処理をするサービスのインタフェースです。</p>
 *
 * @author toshiya
 */
public interface CommandLineService {

    /**
     * コマンドライン処理の質問を出力します。
     */
    void question();

    /**
     * コマンドライン処理を実行します。
     *
     * @param configurationBo アプリケーションの設定情報
     *
     * @param inputValue コマンドラインから入力された値
     *
     * @return コマンドラインの処理結果
     */
    CommandLineServiceResultBo execute(ConfigurationBo configurationBo, InputValue inputValue);

}
