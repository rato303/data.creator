package rato.data.creator.service.select;

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
	protected String getQuestionMessage(ResourceBundle bundle) {
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

		// TODO 選択されたテーブルのFK一覧を取得
		// TODO 関連するテーブルのデータを削除

		return new CommandLineServiceResultBo(beforeResult); // TODO 戻り値を設定する
	}

}
