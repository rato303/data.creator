package rato.data.creator.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import rato.data.creator.config.DataBaseConfig;
import rato.data.creator.entity.ColumnInfo;
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

	/** カラム情報 */
	private List<ColumnInfo> columnInfos;

	/**
	 * コンストラクタ
	 */
	private CommandLineServiceResultBo() {
		this.factory = null;
		this.configurationBo = new ConfigurationBo();
		this.dataBaseConfig = new DataBaseConfig();
		this.tableInfos = new ArrayList<TableInfo>(0);
		this.columnInfos = new ArrayList<ColumnInfo>(0);
	}

	public static CommandLineServiceResultBo create() {
		return new CommandLineServiceResultBo();
	}

	public static CommandLineServiceResultBo create(CommandLineServiceResultBo beforeResult) {
		CommandLineServiceResultBo instance = create();
		instance.factory = beforeResult.factory;
		instance.configurationBo = beforeResult.configurationBo;
		instance.dataBaseConfig = beforeResult.dataBaseConfig;
		instance.tableInfos = beforeResult.tableInfos;
		instance.columnInfos = beforeResult.columnInfos;
		return instance;
	}

	public CommandLineServiceResultBo setFactory(CommandLineServiceFactory<? extends CommandLineService> factory) {
		this.factory = factory;
		return this;
	}

	public CommandLineServiceResultBo setDatabaseConfig(DataBaseConfig dataBaseConfig) {
		this.dataBaseConfig = dataBaseConfig;
		return this;
	}

	public CommandLineServiceResultBo setTableInfos(List<TableInfo> tableInfos) {
		this.tableInfos = tableInfos;
		return this;
	}

	public CommandLineServiceResultBo setColumnsInfos(List<ColumnInfo> columnInfos) {
		this.columnInfos = columnInfos;
		return this;
	}

	// TODO メソッドチェーン式に変更する？

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

	/**
	 * カラム情報を取得します。
	 * @return カラム情報
	 */
	public List<ColumnInfo> getColumnInfos() {
	    return columnInfos;
	}

}
