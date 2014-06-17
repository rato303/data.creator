package rato.data.creator.dao;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rato.data.creator.config.DataBaseConfig;
import rato.data.creator.dao.impl.TableInfoDaoImpl;
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
		List<TableInfo> actualList = this.tableInfoDao.selectByTableInfo("T_");
		for (TableInfo tableInfo : actualList) {
			System.out.println(tableInfo.tableName);
		}
		// TODO テスト用のddlを作成
	}

}
