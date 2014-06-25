package rato.data.creator.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import rato.data.creator.config.DataBaseConfig;
import rato.data.creator.entity.ColumnInfo;
import rato.data.creator.entity.TableInfo;
import rato.data.creator.service.cli.base.CommandLineService;
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

	/** 読み込み中のカラムのインデックス */
	private int columnIndex;

	/**
	 * <p>
	 * コンストラクタ
	 * </p>
	 */
	private CommandLineServiceResultBo() {
		this.factory = null;
		this.configurationBo = new ConfigurationBo();
		this.dataBaseConfig = new DataBaseConfig();
		this.tableInfos = new ArrayList<TableInfo>(0);
		this.columnInfos = new ArrayList<ColumnInfo>(0);
		this.columnIndex = 0;
	}

	/**
	 * <p>
	 * コマンドライン処理をするサービスの処理結果のインスタンスを生成します。
	 * </p>
	 *
	 * @return コマンドライン処理をするサービスの処理結果のインスタンス
	 */
	public static CommandLineServiceResultBo create() {
		return new CommandLineServiceResultBo();
	}

	/**
	 * <p>
	 * 引数で渡された処理結果の内容を引き継いだインスタンスを生成します。
	 * </p>
	 *
	 * @param beforeResult
	 *            引き継ぎ対象の処理結果
	 *
	 * @return 引数で渡された処理結果の内容を引き継いだインスタンス
	 */
	public static CommandLineServiceResultBo create(
			CommandLineServiceResultBo beforeResult) {
		CommandLineServiceResultBo instance = create();
		instance.factory = beforeResult.factory;
		instance.configurationBo = beforeResult.configurationBo;
		instance.dataBaseConfig = beforeResult.dataBaseConfig;
		instance.tableInfos = beforeResult.tableInfos;
		instance.columnInfos = beforeResult.columnInfos;
		instance.columnIndex = beforeResult.columnIndex;
		return instance;
	}

	/**
	 * <p>
	 * 次のコマンドライン処理をするサービスのファクトリを設定したインスタンスを取得します。
	 * </p>
	 *
	 * @param factory
	 *            次のコマンドライン処理をするサービスのファクトリ
	 *
	 * @return 次のコマンドライン処理をするサービスのファクトリを設定したインスタンス
	 */
	public CommandLineServiceResultBo setFactory(
			CommandLineServiceFactory<? extends CommandLineService> factory) {
		this.factory = factory;
		return this;
	}

	/**
	 * <p>
	 * データベース接続情報を設定したインスタンスを取得します。
	 * </p>
	 *
	 * @param dataBaseConfig
	 *            データベース接続情報
	 * @return データベース接続情報を設定したインスタンス
	 */
	public CommandLineServiceResultBo setDatabaseConfig(
			DataBaseConfig dataBaseConfig) {
		this.dataBaseConfig = dataBaseConfig;
		return this;
	}

	/**
	 * <p>
	 * テーブル情報検索結果を設定したインスタンスを取得します。
	 * </p>
	 *
	 * @param tableInfos
	 *            テーブル情報検索結果
	 *
	 * @return テーブル情報検索結果を設定したインスタンス
	 */
	public CommandLineServiceResultBo setTableInfos(List<TableInfo> tableInfos) {
		this.tableInfos = tableInfos;
		return this;
	}

	/**
	 * <p>
	 * カラム情報を設定したインスタンスを取得します。
	 * </p>
	 *
	 * @param columnInfos
	 *            カラム情報
	 * @return カラム情報を設定したインスタンス
	 */
	public CommandLineServiceResultBo setColumnsInfos(
			List<ColumnInfo> columnInfos) {
		this.columnInfos = columnInfos;
		return this;
	}

	/**
	 * <p>
	 * 読み込み中のカラムのインデックスを増加します。
	 * </p>
	 *
	 * @return 読み込み中のカラムのインデックスを増加したインスタンス
	 */
	public CommandLineServiceResultBo addColumnIndex() {
		this.columnIndex++;
		return this;
	}

	/**
	 * <p>
	 * 読み込み中のカラム情報を取得します。
	 * </p>
	 *
	 * @return 読み込み中のカラム情報
	 */
	public ColumnInfo getReadingColumnInfo() throws IndexOutOfBoundsException {
		// TODO columnInfosを拡張リストにしてmaxIndexの比較メソッドが欲しい
		if (columnIndex < this.columnInfos.size()) {
			return this.columnInfos.get(columnIndex);
		}
		return null;
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
	 *
	 * @return カラム情報
	 */
	public List<ColumnInfo> getColumnInfos() {
		return columnInfos;
	}

	/**
	 * 読み込み中のカラムのインデックスを取得します。
	 *
	 * @return 読み込み中のカラムのインデックス
	 */
	public int getColumnIndex() {
		return columnIndex;
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
