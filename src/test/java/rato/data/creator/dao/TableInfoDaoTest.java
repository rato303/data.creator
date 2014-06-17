package rato.data.creator.dao;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rato.data.creator.config.DataBaseConfig;
import rato.data.creator.dao.impl.TableInfoDaoImpl;
import rato.data.creator.entity.ColumnInfo;
import rato.data.creator.entity.TableInfo;
import rato.data.creator.util.ResourceUtil;

public class TableInfoDaoTest {

	private static DataBaseConfig dataBaseConfig;

	private TableInfoDao tableInfoDao;

	@BeforeClass
	public static void beforeClass() {
		dataBaseConfig = new DataBaseConfig(
				ResourceUtil.propertiesFileLoad(ResourceUtil.getFilePath(
						TableInfoDaoTest.class, "jdbc.properties")));
	}

	@Before
	public void before() {
		this.tableInfoDao = new TableInfoDaoImpl(dataBaseConfig.getDataSource());
		dataBaseConfig.getLocalTransaction().begin();
	}

	@After
	public void after() {
		dataBaseConfig.getLocalTransaction().rollback();
	}

	@Test
	public void testSelectByTableInfo() {
		List<TableInfo> actualList = this.tableInfoDao.findByTableInfo("T_");
		for (TableInfo tableInfo : actualList) {
			System.out.println(tableInfo.tableName);
		}
		// TODO テスト用のddlを作成
		// TODO dbunitの組み込み
		// TODO アサーション用のメソッド作成
	}

	@Test
	public void testFindByTableInfo() {
		List<ColumnInfo> actualList = this.tableInfoDao.selectByColmnInfo("T_TOCHI_TOKI");
		for (ColumnInfo columnInfo : actualList) {
			System.out.println(columnInfo.columnName);
		}
		// TODO テスト用のddlを作成
	}

}
