package rato.data.creator.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Select;

import rato.data.creator.config.DataBaseConfig;
import rato.data.creator.entity.ColumnInfo;
import rato.data.creator.entity.TableInfo;

@Dao(config = DataBaseConfig.class)
public interface TableInfoDao {

	/**
	 * <p>
	 * テーブル名を部分一致で検索します。
	 * </p>
	 *
	 * @param tableName
	 *            テーブル名(前方一致)
	 *
	 * @return 条件に合致するレコード
	 */
	@Select
	List<TableInfo> findByTableInfo(String tableName);

	/**
	 * <p>
	 * 指定されたテーブルのカラム情報を取得します。
	 * </p>
	 *
	 * @param tableName
	 *            テーブル名(完全一致)
	 *
	 * @return 条件に合致するレコード
	 */
	@Select
	List<ColumnInfo> selectByColmnInfo(String tableName);

	/**
	 * <p>
	 * 指定されたテーブルのFK(末端まで)関係にあるテーブル一覧を取得します。
	 * </p>
	 *
	 * @param tableName
	 *            テーブル名(完全一致)
	 *
	 * @return 条件に合致するレコード
	 */
	@Select
	List<TableInfo> findByFkTables(String tableName);

	/**
	 * <p>
	 * 指定されたテーブルのデータを削除します。
	 * </p>
	 *
	 * @param tableName
	 *            テーブル名
	 *
	 * @return 削除したレコード数
	 */
	@Delete(sqlFile = true)
	int delete(String tableName);

}
