package rato.data.creator.bo;

import java.io.Serializable;
import java.util.Properties;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * データベースの接続情報です。
 *
 * @author toshiya
 */
public class DatabaseConnectionInfoBo implements Serializable {

	/** シリアルID */
	private static final long serialVersionUID = 3250637578828491424L;

	public static final String PROPERTY_KEY_JDBC_DRIVER_CLASS = "jdbc.driver.class";

	public static final String PROPERTY_KEY_JDBC_URL = "jdbc.url";

	public static final String PROPERTY_KEY_JDBC_SCHEMA = "jdbc.schema";

	public static final String PROPERTY_KEY_JDBC_USER = "jdbc.user";

	public static final String PROPERTY_KEY_JDBC_PASSWORD = "jdbc.password";

	/** JDBC接続に使用するドライバークラスのFQDN */
	private final String jdbcDriverClassName;

	/** JDBC接続に使用するJDBCのURL */
	private final String jdbcUrl;

	/** JDBC接続に使用するスキーマ名 */
	private final String jdbcSchema;

	/** ユーザー名 */
	private final String userName;

	/** パスワード */
	private final String password;

	/**
	 * <p>
	 * JDBC接続情報が設定された接続情報を生成します。
	 * </p>
	 *
	 * @param properties jdbc接続情報propertiesファイルのインスタンス
	 */
	public DatabaseConnectionInfoBo(Properties properties) {
		super();
		this.jdbcDriverClassName = properties.getProperty(PROPERTY_KEY_JDBC_DRIVER_CLASS);
		this.jdbcUrl = properties.getProperty(PROPERTY_KEY_JDBC_URL);
		this.jdbcSchema = properties.getProperty(PROPERTY_KEY_JDBC_SCHEMA);
		this.userName = properties.getProperty(PROPERTY_KEY_JDBC_USER);
		this.password = properties.getProperty(PROPERTY_KEY_JDBC_PASSWORD);
	}

	/**
	 * JDBC接続に使用するドライバークラスのFQDNを取得します。
	 *
	 * @return JDBC接続に使用するドライバークラスのFQDN
	 */
	public String getJdbcDriverClassName() {
		return this.jdbcDriverClassName;
	}

	/**
	 * JDBC接続に使用するJDBCのURLを取得します。
	 *
	 * @return JDBC接続に使用するJDBCのURL
	 */
	public String getJdbcUrl() {
		return this.jdbcUrl;
	}

	/**
	 * JDBC接続に使用するスキーマ名を取得します。
	 *
	 * @return JDBC接続に使用するスキーマ名
	 */
	public String getJdbcSchema() {
		return this.jdbcSchema;
	}

	/**
	 * ユーザー名を取得します。
	 *
	 * @return ユーザー名
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * パスワードを取得します。
	 *
	 * @return パスワード
	 */
	public String getPassword() {
		return this.password;
	}

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object paramObject) {
        return EqualsBuilder.reflectionEquals(this, paramObject);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
