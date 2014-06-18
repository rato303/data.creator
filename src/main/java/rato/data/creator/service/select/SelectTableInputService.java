package rato.data.creator.service.select;

import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang3.math.NumberUtils;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.InputValue;
import rato.data.creator.dao.TableInfoDao;
import rato.data.creator.dao.impl.TableInfoDaoImpl;
import rato.data.creator.entity.ColumnInfo;
import rato.data.creator.entity.TableInfo;
import rato.data.creator.exception.RetryException;
import rato.data.creator.service.BaseCommandLineService;
import rato.data.creator.service.factory.ColumnValueInputServiceFactory;

/**
 * <p>
 * データ生成をするテーブルを選択するサービスです。
 * </p>
 *
 * @author toshiya
 *
 */
public class SelectTableInputService extends BaseCommandLineService {

	private TableInfoDao tableInfoDao;

	@Override
	protected String getQuestionMessage(
			CommandLineServiceResultBo beforeResult, ResourceBundle bundle) {
		return bundle.getString("question.table.index");
	}

	@Override
	protected void validateProcess(CommandLineServiceResultBo beforeResult,
			InputValue inputValue) {

		if (inputValue.isEmpty()) {
			throw new RetryException("error.table.index.empty", beforeResult);
		}

		if (!NumberUtils.isDigits(inputValue.getValue())) {
			throw new RetryException("error.table.index.not.integer",
					beforeResult);
		}

		// TODO TableInfos用のArrayListを作成してgetMaxIndexを実装する
		int maxIndex = beforeResult.getTableInfos().size() - 1;

		if (maxIndex < inputValue.getIntegerValue()) {
			// MessageBoクラスを作成(メッセージキー、メッセージ引数)
			throw new RetryException("error.table.index.out.of.range",
					beforeResult);
		}

	}

	@Override
	protected CommandLineServiceResultBo mainProcess(
			CommandLineServiceResultBo beforeResult, InputValue inputValue) {

		this.tableInfoDao = new TableInfoDaoImpl(beforeResult
				.getDataBaseConfig().getDataSource());

		TableInfo chosenTable = beforeResult.getTableInfos().get(
				inputValue.getIntegerValue());

		List<ColumnInfo> columnInfos = this.tableInfoDao
				.selectByColmnInfo(chosenTable.tableName);

		this.deleteChildTableData(chosenTable.tableName);

		return CommandLineServiceResultBo.create(beforeResult)
				.setFactory(new ColumnValueInputServiceFactory())
				.setColumnsInfos(columnInfos);
	}

	/**
	 * <p>
	 * 指定されたテーブルのFK関係にあるテーブルのデータを削除します。
	 * </p>
	 *
	 * @param tableName
	 *            テーブル名
	 */
	private void deleteChildTableData(String tableName) {
		List<TableInfo> deleteTargets = this.tableInfoDao
				.findByFkTables(tableName);

		// TODO メッセージプロパティ化
		System.out.println(MessageFormat.format("{0}とFK関係にあるテーブルのデータを削除します。",
				tableName));

		for (TableInfo tableInfo : deleteTargets) {
			System.out.println(MessageFormat.format("{0}のデータを削除します。",
					tableInfo.tableName));
		}

	}

}
