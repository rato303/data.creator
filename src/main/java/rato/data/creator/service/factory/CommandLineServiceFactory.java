package rato.data.creator.service.factory;

import rato.data.creator.service.cli.base.CommandLineService;

/**
 * <p>コマンドライン処理をするサービスのファクトリです。</p>
 *
 * @param <S> コマンドライン処理をするサービスクラス
 *
 * @author toshiya
 *
 */
public interface CommandLineServiceFactory<S extends CommandLineService> {

    /**
     * コマンドライン処理をするサービスを生成します。
     *
     * @return コマンドライン処理をするサービスのインスタンス
     */
    S create();

}
