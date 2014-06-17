package rato.data.creator.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.seasar.doma.jdbc.DomaAbstractConfig;
import org.seasar.doma.jdbc.SimpleDataSource;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.OracleDialect;
import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.seasar.doma.jdbc.tx.LocalTransactionalDataSource;

/**
 * <p>
 * データベースの接続情報です。
 * </p>
 *
 * @author toshiya
 *
 */
public class DataBaseConfig extends DomaAbstractConfig {

	/** jdbcDriverClassのFQDNの設定キー */
	public static final String PROPERTY_KEY_JDBC_DRIVER_CLASS = "jdbc.driver.class";

	/** jdbcUrlの設定キー */
	public static final String PROPERTY_KEY_JDBC_URL = "jdbc.url";

	/** 接続先スキーマ名の設定キー */
	public static final String PROPERTY_KEY_JDBC_SCHEMA = "jdbc.schema";

	/** 接続先ユーザー名の設定キー */
	public static final String PROPERTY_KEY_JDBC_USER = "jdbc.user";

	/** 接続先パスワードの設定キー */
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

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.sql.DataSource
	 */
	private LocalTransactionalDataSource dataSource;

	/**
	 * コンストラクタ
	 */
	public DataBaseConfig() {
		super();
		this.jdbcDriverClassName = "";
		this.jdbcUrl = "";
		this.jdbcSchema = "";
		this.userName = "";
		this.password = "";
	}

	/**
	 * <p>
	 * JDBC接続情報が設定された接続情報を生成します。
	 * </p>
	 *
	 * @param properties
	 *            jdbc接続情報propertiesファイルのインスタンス
	 */
	public DataBaseConfig(Properties properties) {
		super();
		this.jdbcDriverClassName = properties
				.getProperty(PROPERTY_KEY_JDBC_DRIVER_CLASS);
		this.jdbcUrl = properties.getProperty(PROPERTY_KEY_JDBC_URL);
		this.jdbcSchema = properties.getProperty(PROPERTY_KEY_JDBC_SCHEMA);
		this.userName = properties.getProperty(PROPERTY_KEY_JDBC_USER);
		this.password = properties.getProperty(PROPERTY_KEY_JDBC_PASSWORD);

		dataSource = this.createDataSource();
	}

	@Override
	public DataSource getDataSource() {
		return this.dataSource;
	}

	@Override
	public Dialect getDialect() {
		// TODO ファクトリクラスを作成してjdbcdriverclassnameを正規表現でマッチさせてダイアレクトを変更する
		// MysqlDialect
		// PostgresDialect
		return new OracleDialect();
	}

	// TODO 本来はstatic
	private LocalTransactionalDataSource createDataSource() {
		SimpleDataSource dataSource = new SimpleDataSource();
		dataSource.setUrl(this.getJdbcUrl());
		dataSource.setUser(this.getUserName());
		dataSource.setPassword(this.getPassword());
		return new LocalTransactionalDataSource(dataSource);
	}

	// TODO 本来はstatic
	public LocalTransaction getLocalTransaction() {
		return dataSource.getLocalTransaction(defaultJdbcLogger);
	}

	/**
	 * JDBC接続に使用するドライバークラスのFQDNを取得します。
	 *
	 * @return JDBC接続に使用するドライバークラスのFQDN
	 */
	public String getJdbcDriverClassName() {
		return jdbcDriverClassName;
	}

	/**
	 * JDBC接続に使用するJDBCのURLを取得します。
	 *
	 * @return JDBC接続に使用するJDBCのURL
	 */
	public String getJdbcUrl() {
		return jdbcUrl;
	}

	/**
	 * JDBC接続に使用するスキーマ名を取得します。
	 *
	 * @return JDBC接続に使用するスキーマ名
	 */
	public String getJdbcSchema() {
		return jdbcSchema;
	}

	/**
	 * ユーザー名を取得します。
	 *
	 * @return ユーザー名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * パスワードを取得します。
	 *
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object paramObject) {
		return EqualsBuilder.reflectionEquals(this, paramObject);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
