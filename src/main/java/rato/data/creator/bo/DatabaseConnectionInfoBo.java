package rato.data.creator.bo;

import java.io.Serializable;

/**
 * データベースの接続情報です。
 *
 * @author toshiya
 */
public class DatabaseConnectionInfoBo implements Serializable {

    /** シリアルID */
    private static final long serialVersionUID = 3250637578828491424L;

    /** JDBC接続に使用するドライバークラスのFQDN */
    private final String jdbcDriverClassName;

    /** JDBC接続に使用するJDBCのURL */
    private final String jdbcUrl;

    /** JDBC接続に使用するスキーマ名 */
    private final String jdbcShema;

    /** ユーザー名 */
    private final String userName;

    /** パスワード */
    private final String password;

    /**
     * <p>JDBC接続情報が設定された接続情報を生成します。</p>
     *
     * @param jdbcDriverClassName JDBC接続に使用するドライバークラスのFQDN
     * @param jdbcUrl JDBC接続に使用するJDBCのURL
     * @param jdbcShema JDBC接続に使用するスキーマ名
     * @param userName ユーザー名
     * @param password パスワード
     */
    public DatabaseConnectionInfoBo(String jdbcDriverClassName, String jdbcUrl, String jdbcShema, String userName, String password) {
        super();
        this.jdbcDriverClassName = jdbcDriverClassName;
        this.jdbcUrl = jdbcUrl;
        this.jdbcShema = jdbcShema;
        this.userName = userName;
        this.password = password;
    }

    /**
     * JDBC接続に使用するドライバークラスのFQDNを取得します。
     * @return JDBC接続に使用するドライバークラスのFQDN
     */
    public String getJdbcDriverClassName() {
        return jdbcDriverClassName;
    }

    /**
     * JDBC接続に使用するJDBCのURLを取得します。
     * @return JDBC接続に使用するJDBCのURL
     */
    public String getJdbcUrl() {
        return jdbcUrl;
    }

    /**
     * JDBC接続に使用するスキーマ名を取得します。
     * @return JDBC接続に使用するスキーマ名
     */
    public String getJdbcShema() {
        return jdbcShema;
    }

    /**
     * ユーザー名を取得します。
     * @return ユーザー名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * パスワードを取得します。
     * @return パスワード
     */
    public String getPassword() {
        return password;
    }

}
