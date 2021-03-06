package rato.data.creator.service.select;

import java.util.List;
import java.util.ResourceBundle;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.InputValue;
import rato.data.creator.dao.TableInfoDao;
import rato.data.creator.dao.impl.TableInfoDaoImpl;
import rato.data.creator.entity.TableInfo;
import rato.data.creator.service.cli.base.BaseCommandLineService;
import rato.data.creator.service.factory.SelectTableInputServiceFactory;

/**
 * <p>
 * テーブルを検索するサービスです。
 * </p>
 *
 * @author toshiya
 *
 */
public class FindTableInputService extends BaseCommandLineService {

	/** テーブル情報Dao */
	private TableInfoDao tableInfoDao;

	@Override
	protected String getQuestionMessage(CommandLineServiceResultBo beforeResult,ResourceBundle bundle) {
		return bundle.getString("question.find.table.name");
	}

	/**
	 * チェック処理はありません。
	 */
	@Override
	protected void validateProcess(CommandLineServiceResultBo beforeResult,
			InputValue inputValue) {
	}

	@Override
	protected CommandLineServiceResultBo mainProcess(
			CommandLineServiceResultBo beforeResult, InputValue inputValue) {
		this.tableInfoDao = new TableInfoDaoImpl(beforeResult
				.getDataBaseConfig().getDataSource());

		List<TableInfo> tableInfos = this.tableInfoDao
				.findByTableInfo(inputValue.getUpperValue());

		if (tableInfos.size() == 0) {
			// TODO 入力した名称のテーブルがありませんでした。入力しなおしてください。
			return CommandLineServiceResultBo.create(beforeResult);
		}

		for (int i = 0; i < tableInfos.size(); i++) {
			TableInfo tableInfo = tableInfos.get(i);
			super.logger.info("{}:{}", i, tableInfo.tableName);
		}

		return CommandLineServiceResultBo.create(beforeResult)
				.setFactory(new SelectTableInputServiceFactory())
				.setTableInfos(tableInfos);
	}

}
