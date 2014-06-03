package rato.data.creator.bo;

import java.io.Serializable;

import rato.data.creator.service.factory.CommandLineServiceFactory;

/**
 * <p>コマンドライン処理をするサービスの処理結果を格納するビジネスオブジェクトです。</p>
 *
 * @author toshiya
 *
 */
public class CommandLineServiceResultBo implements Serializable {

    /** シリアルID */
    private static final long serialVersionUID = -1389195551324371770L;

    /** 次のコマンドライン処理をするサービスのファクトリ */
    private CommandLineServiceFactory factory;

    /**
     * コンストラクタ
     */
    public CommandLineServiceResultBo() {
        super();
    }

    /**
     * コンストラクタ
     *
     * @param factory 次のコマンドライン処理をするサービスのファクトリ
     */
    public CommandLineServiceResultBo(CommandLineServiceFactory factory) {
        super();
        this.factory = factory;
    }

    /**
     * 次のコマンドライン処理をするサービスのファクトリを取得します。
     * @return 次のコマンドライン処理をするサービスのファクトリ
     */
    public CommandLineServiceFactory getFactory() {
        return factory;
    }

    /**
     * 次のコマンドライン処理をするサービスのファクトリを保持していないか判定します。
     *
     * @return 保持していない場合は「true」保持している場合は「false」
     */
    public boolean hasNotNextServiceFactory() {
        return this.factory == null;
    }

}
