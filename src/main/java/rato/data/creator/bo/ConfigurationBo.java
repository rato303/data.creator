package rato.data.creator.bo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import rato.data.creator.config.DataBaseConfig;

/**
 * アプリケーションの設定情報です。
 *
 * @author toshiya
 */
public class ConfigurationBo implements Serializable {

	/** シリアルID */
	private static final long serialVersionUID = -8417244527747610744L;

	/** データベースの接続情報 */
	private DataBaseConfig dataBaseConfig;

	/** 出力先ディレクトリのパス */
	private String distDirectoryPath;

	/**
	 * コンストラクタ
	 */
	public ConfigurationBo() {
		super();
	}

	/**
	 * データベースの接続情報を取得します。
	 * @return データベースの接続情報
	 */
	public DataBaseConfig getDataBaseConfig() {
	    return dataBaseConfig;
	}

	/**
	 * データベースの接続情報を設定します。
	 * @param dataBaseConfig データベースの接続情報
	 */
	public void setDataBaseConfig(DataBaseConfig dataBaseConfig) {
	    this.dataBaseConfig = dataBaseConfig;
	}

	/**
	 * 出力先ディレクトリのパスを取得します。
	 *
	 * @return 出力先ディレクトリのパス
	 */
	public String getDistDirectoryPath() {
		return distDirectoryPath;
	}

	/**
	 * 出力先ディレクトリのパスを設定します。
	 *
	 * @param distDirectoryPath
	 *            出力先ディレクトリのパス
	 */
	public void setDistDirectoryPath(String distDirectoryPath) {
		this.distDirectoryPath = distDirectoryPath;
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
