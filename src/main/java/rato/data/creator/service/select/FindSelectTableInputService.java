package rato.data.creator.service.select;

import java.util.List;
import java.util.ResourceBundle;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.InputValue;
import rato.data.creator.dao.TableInfoDao;
import rato.data.creator.dao.impl.TableInfoDaoImpl;
import rato.data.creator.entity.TableInfo;
import rato.data.creator.service.BaseCommandLineService;

/**
 * <p>
 * データを生成するテーブル名を検索します。
 * </p>
 *
 * @author toshiya
 *
 */
public class FindSelectTableInputService extends BaseCommandLineService {

	/** テーブル情報Dao */
	private TableInfoDao tableInfoDao;

	@Override
	protected String getQuestionMessage(ResourceBundle bundle) {
		return bundle.getString("question.find.table.name");
	}

	/**
	 * チェック処理はありません。
	 */
	@Override
	protected void validateProcess(CommandLineServiceResultBo beforeResult, InputValue inputValue) {
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
			return new CommandLineServiceResultBo(beforeResult);
		}

		for (int i = 0; i < tableInfos.size(); i++) {
			TableInfo tableInfo = tableInfos.get(i);
			System.out.println(i + ":" + tableInfo.tableName);
		}

		return new CommandLineServiceResultBo(beforeResult, tableInfos);
	}

}
