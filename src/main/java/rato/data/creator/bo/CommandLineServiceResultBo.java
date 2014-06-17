package rato.data.creator.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import rato.data.creator.config.DataBaseConfig;
import rato.data.creator.entity.TableInfo;
import rato.data.creator.service.CommandLineService;
import rato.data.creator.service.factory.CommandLineServiceFactory;

/**
 * <p>
 * コマンドライン処理をするサービスの処理結果です。
 * </p>
 *
 * @author toshiya
 *
 */
public class CommandLineServiceResultBo implements Serializable {

	/** シリアルID */
	private static final long serialVersionUID = -1389195551324371770L;

	/** 次のコマンドライン処理をするサービスのファクトリ */
	private CommandLineServiceFactory<? extends CommandLineService> factory;

	/** アプリケーションの設定情報 */
	private ConfigurationBo configurationBo;

	/** データベース接続情報 */
	private DataBaseConfig dataBaseConfig;

	/** テーブル情報検索結果 */
	private List<TableInfo> tableInfos;

	/**
	 * コンストラクタ
	 */
	public CommandLineServiceResultBo() {
		this.factory = null;
		this.configurationBo = new ConfigurationBo();
		this.dataBaseConfig = new DataBaseConfig();
		this.tableInfos = new ArrayList<TableInfo>(0);
	}

	public CommandLineServiceResultBo(CommandLineServiceResultBo beforeResult) {
		this.factory = beforeResult.factory;
		this.configurationBo = beforeResult.configurationBo;
		this.dataBaseConfig = beforeResult.dataBaseConfig;
		this.tableInfos = beforeResult.tableInfos;
	}

	public CommandLineServiceResultBo(CommandLineServiceResultBo beforeResult, CommandLineServiceFactory<? extends CommandLineService> factory) {
		this(beforeResult);
		this.factory = factory;
	}

	public CommandLineServiceResultBo(CommandLineServiceResultBo beforeResult, CommandLineServiceFactory<? extends CommandLineService> factory, DataBaseConfig dataBaseConfig) {
		this(beforeResult, factory);
		this.dataBaseConfig = dataBaseConfig;
	}

	public CommandLineServiceResultBo(CommandLineServiceResultBo beforeResult, ConfigurationBo configurationBo) {
		this(beforeResult);
		this.configurationBo = configurationBo;
	}

	public CommandLineServiceResultBo(CommandLineServiceResultBo beforeResult, List<TableInfo> tableInfos) {
		this(beforeResult);
		this.tableInfos = tableInfos;
	}

	/**
	 * 次のコマンドライン処理をするサービスのファクトリを取得します。
	 *
	 * @return 次のコマンドライン処理をするサービスのファクトリ
	 */
	public CommandLineServiceFactory<? extends CommandLineService> getFactory() {
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

	/**
	 * アプリケーションの設定情報を取得します。
	 *
	 * @return アプリケーションの設定情報
	 */
	public ConfigurationBo getConfigurationBo() {
		return configurationBo;
	}

	/**
	 * データベース接続情報を取得します。
	 *
	 * @return データベース接続情報
	 */
	public DataBaseConfig getDataBaseConfig() {
		return dataBaseConfig;
	}

	/**
	 * テーブル情報検索結果を取得します。
	 *
	 * @return テーブル情報検索結果
	 */
	public List<TableInfo> getTableInfos() {
		return tableInfos;
	}

}
