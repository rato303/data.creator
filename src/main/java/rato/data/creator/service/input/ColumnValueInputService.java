package rato.data.creator.service.input;

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

		if (readingColumnInfo.nullable.isRequired()) {
			if (inputValue.isEmpty()) {
				throw new RetryException("error.column.input.required", beforeResult);
			}
		}

		// TODO 自動生成されたメソッド・スタブ

		// TODO 入力値の検証

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
