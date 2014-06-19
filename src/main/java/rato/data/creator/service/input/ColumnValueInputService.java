package rato.data.creator.service.input;

import static rato.data.creator.bo.MessageBo.create;
import static rato.data.creator.validation.StringCheckUtil.exceedLength;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.InputValue;
import rato.data.creator.entity.ColumnInfo;
import rato.data.creator.exception.RetryException;
import rato.data.creator.service.BaseCommandLineService;
import rato.data.creator.service.factory.ColumnValueInputServiceFactory;

public class ColumnValueInputService extends BaseCommandLineService {

	@Override
	protected String getQuestionMessage(
			CommandLineServiceResultBo beforeResult, ResourceBundle bundle) {
		ColumnInfo columnInfo = beforeResult.getReadingColumnInfo();
		return MessageFormat.format(
				bundle.getString("question.column.value.input"),
				columnInfo.columnName, columnInfo.dataType,
				columnInfo.dataLength, columnInfo.dataPrecision,
				columnInfo.dataScale, columnInfo.nullable);
	}

	@Override
	protected void validateProcess(CommandLineServiceResultBo beforeResult,
			InputValue inputValue) {

		ColumnInfo readingColumnInfo = beforeResult.getReadingColumnInfo();

		// TODO メソッド化？
		if (readingColumnInfo.nullable.isRequired()) {
			if (inputValue.isEmpty()) {
				throw new RetryException(create("error.column.input.required"),
						beforeResult);
			}
		}

		String value = inputValue.getValue();
		Integer dataLength = readingColumnInfo.dataLength.getValue();

		// TODO メソッド化
		switch (readingColumnInfo.dataType.getCategory()) {

		case CHAR:
			if (exceedLength(value, dataLength)) {
				// TODO value.length() サロゲートペアの文字列長ではない？
				throw new RetryException(
						create("error.column.input.char.exceed.length", value.length()),
						beforeResult);
			}
			// TODO 文字列の長さが不足している場合に警告する
			break;
		case VARCHAR:
			if (exceedLength(value, dataLength)) {
				// TODO value.length() サロゲートペアの文字列長ではない？
				throw new RetryException(
						create("error.column.input.char.exceed.length"),
						beforeResult);
			}
			break;
		case NUMBER:
			// TODO 文字種チェック
			// TODO 数値型の精度チェック
			// TODO 数値型の小数点以下のチェック
			break;
		case DATE:
			// TODO 日付型チェック
			break;
		case TIMESTAMP:
			// TODO 日時型チェック
			break;
		}

	}

	@Override
	protected CommandLineServiceResultBo mainProcess(
			CommandLineServiceResultBo beforeResult, InputValue inputValue) {
		// TODO 自動生成されたメソッド・スタブ

		CommandLineServiceResultBo result = CommandLineServiceResultBo
				.create(beforeResult)
				.setFactory(new ColumnValueInputServiceFactory())
				.addColumnIndex();

		return result;
	}

}
