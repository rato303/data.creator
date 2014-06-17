package rato.data.creator.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;

import rato.data.creator.config.DataBaseConfig;
import rato.data.creator.entity.TableInfo;

@Dao(config = DataBaseConfig.class)
public interface TableInfoDao {

	@Select
	List<TableInfo> selectByTableInfo(String tableName);

}
