package rato.data.creator.bo;

import java.io.Serializable;

/**
 * アプリケーションの設定情報です。
 *
 * @author toshiya
 */
public class ConfigurationBo implements Serializable {

    /** シリアルID */
    private static final long serialVersionUID = -8417244527747610744L;

    /** テーブル定義ファイルのファイルパス */
    private String tableConfFilePath;

    /** 出力先ディレクトリのパス */
    private String distDirectoryPath;

    /**
     * コンストラクタ
     */
    public ConfigurationBo() {
        super();
    }

    /**
     * テーブル定義ファイルのファイルパスを取得します。
     * @return テーブル定義ファイルのファイルパス
     */
    public String getTableConfFilePath() {
        return tableConfFilePath;
    }

    /**
     * テーブル定義ファイルのファイルパスを設定します。
     * @param tableConfFilePath テーブル定義ファイルのファイルパス
     */
    public void setTableConfFilePath(String tableConfFilePath) {
        this.tableConfFilePath = tableConfFilePath;
    }

    /**
     * 出力先ディレクトリのパスを取得します。
     * @return 出力先ディレクトリのパス
     */
    public String getDistDirectoryPath() {
        return distDirectoryPath;
    }

    /**
     * 出力先ディレクトリのパスを設定します。
     * @param distDirectoryPath 出力先ディレクトリのパス
     */
    public void setDistDirectoryPath(String distDirectoryPath) {
        this.distDirectoryPath = distDirectoryPath;
    }

}
