package rato.data.creator.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

	/** テーブル情報検索結果 */
	private List<TableInfo> tableInfos;

	/**
	 * コンストラクタ
	 */
	public CommandLineServiceResultBo() {
		this(new ConfigurationBo(), null, new ArrayList<TableInfo>(0));
	}

	/**
	 * コンストラクタ
	 *
	 * @param configurationBo
	 *            アプリケーションの設定情報
	 */
	public CommandLineServiceResultBo(ConfigurationBo configurationBo) {
		this(configurationBo, null, new ArrayList<TableInfo>(0));
	}

	/**
	 * コンストラクタ
	 *
	 * @param factory
	 *            次のコマンドライン処理をするサービスのファクトリ
	 */
	public CommandLineServiceResultBo(
			CommandLineServiceFactory<? extends CommandLineService> factory) {
		this(new ConfigurationBo(), factory, new ArrayList<TableInfo>(0));
	}

	/**
	 * コンストラクタ
	 *
	 * @param configurationBo
	 *            アプリケーションの設定情報
	 *
	 * @param factory
	 *            次のコマンドライン処理をするサービスのファクトリ
	 */
	public CommandLineServiceResultBo(ConfigurationBo configurationBo,
			CommandLineServiceFactory<? extends CommandLineService> factory,
			List<TableInfo> tableInfos) {
		super();
		this.configurationBo = configurationBo;
		this.factory = factory;
		this.tableInfos = tableInfos;
	}

	/**
	 * コンストラクタ
	 *
	 * @param configurationBo
	 *            アプリケーションの設定情報
	 *
	 * @param factory
	 *            次のコマンドライン処理をするサービスのファクトリ
	 */
	public CommandLineServiceResultBo(ConfigurationBo configurationBo,
			CommandLineServiceFactory<? extends CommandLineService> factory) {
		super();
		this.configurationBo = configurationBo;
		this.factory = factory;
		this.tableInfos = new ArrayList<TableInfo>(0);
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
	 * テーブル情報検索結果を取得します。
	 *
	 * @return テーブル情報検索結果
	 */
	public List<TableInfo> getTableInfos() {
		return tableInfos;
	}

}
