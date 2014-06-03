package rato.data.creator.service;

/**
 * <p>コマンドライン処理をするサービスのファクトリです。</p>
 *
 * @author toshiya
 *
 */
public interface CommandLineServiceFactory {

    /**
     * コマンドライン処理をするサービスを生成します。
     *
     * @return コマンドライン処理をするサービスのインスタンス
     */
    CommandLineService create();

}
