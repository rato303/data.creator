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

	/** 設定された情報が何もないか */
	private boolean isNone;

	/**
	 * コンストラクタ
	 */
	public ConfigurationBo() {
		super();
		this.isNone = true;
		this.dataBaseConfig = new DataBaseConfig();
	}

	/**
	 * <p>
	 * データベース接続情報を設定します。
	 * </p>
	 *
	 * @param dataBaseConfig
	 *            データベース接続情報
	 */
	public void setDataBaseConfig(DataBaseConfig dataBaseConfig) {
		this.isNone = false;
		this.dataBaseConfig = dataBaseConfig;
	}

	/**
	 * <p>
	 * 出力先のディレクトリパスをが設定します。
	 * </p>
	 *
	 * @param distDirectoryPath
	 *            出力先のディレクトリパス
	 */
	public void setDistDirectoryPath(String distDirectoryPath) {
		this.isNone = false;
		this.distDirectoryPath = distDirectoryPath;
	}

	/**
	 * <p>
	 * アプリケーション設定情報が空か判定します。
	 * </p>
	 *
	 * @return アプリケーション設定情報が空の場合は「true」、空でない場合は「false」を返します。
	 */
	public boolean isNone() {
		return this.isNone;
	}

	/**
	 * データベースの接続情報を取得します。
	 *
	 * @return データベースの接続情報
	 */
	public DataBaseConfig getDataBaseConfig() {
		return this.dataBaseConfig;
	}

	/**
	 * 出力先ディレクトリのパスを取得します。
	 *
	 * @return 出力先ディレクトリのパス
	 */
	public String getDistDirectoryPath() {
		return this.distDirectoryPath;
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
