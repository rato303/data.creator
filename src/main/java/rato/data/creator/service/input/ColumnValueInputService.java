package rato.data.creator.service.input;

import static rato.data.creator.bo.MessageBo.create;
import static rato.data.creator.validation.NumberCheckUtil.getDecimalLength;
import static rato.data.creator.validation.NumberCheckUtil.isDecimal;
import static rato.data.creator.validation.NumberCheckUtil.isNumber;
import static rato.data.creator.validation.StringCheckUtil.exceedLength;
import static rato.data.creator.validation.TimeStampCheckUtil.checkFormat;
import static rato.data.creator.validation.TimeStampCheckUtil.getAllowPatterns;

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
				columnInfo.dataLength, columnInfo.charColDeclLength,
				columnInfo.dataPrecision, columnInfo.dataScale,
				columnInfo.nullable);
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

		// TODO メソッド化
		switch (readingColumnInfo.dataType.getCategory()) {

		case CHAR:
			if (exceedLength(value,
					readingColumnInfo.charColDeclLength.getValue())) { // TODO
				// TODO value.length() サロゲートペアの文字列長ではない？
				throw new RetryException(
						create("error.column.input.char.exceed.length",
								value.length()), beforeResult);
			}
			// TODO 文字列の長さが不足している場合に警告する
			break;
		case VARCHAR:
			if (exceedLength(value,
					readingColumnInfo.charColDeclLength.getValue())) {
				// TODO value.length() サロゲートペアの文字列長ではない？
				throw new RetryException(
						create("error.column.input.char.exceed.length"),
						beforeResult);
			}
			break;
		case NUMBER:
			if (!isNumber(value)) {
				throw new RetryException(create(
						"error.column.input.not.number", value), beforeResult);
			}
			int decimalLength = 0;

			if (isDecimal(value)) {
				decimalLength = getDecimalLength(value);

				if (readingColumnInfo.dataScale.getValue() < decimalLength) {
					throw new RetryException(create(
							"error.column.input.decimal.length.over",
							readingColumnInfo.dataScale, value), beforeResult);
				}

				// TODO 小数に負の数が設定された場合も考慮する
			}

			int positiveNumberLength = value.length() - decimalLength;
			if (readingColumnInfo.dataPrecision.getValue() < positiveNumberLength) {
				throw new RetryException(create(
						"error.column.input.positive.number.length",
						readingColumnInfo.dataPrecision, value), beforeResult);
			}

			break;
		case DATE:
			// TODO 日付型チェック
			break;
		case TIMESTAMP:
			if (!checkFormat(value)) {
				throw new RetryException(create(
						"error.column.input.timestamp.format",
						getAllowPatterns()), beforeResult);
			}

			// TODO 最終出力フォーマット 2013-10-23 09:58:01.0
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

		// TODO 1週したら行データを保持する

		return result;
	}

}
