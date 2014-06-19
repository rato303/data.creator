package rato.data.creator.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class DataTypeTest {

	/** 固定長文字列型の入力値 */
	public static String[] VARIABLE_LENGTH_STRING_TYPE_INPUT_VALUES = {
			"VARCHAR2(2)", "NVARCHAR2(10)", "varchar2(3)", "nvarchar2(4)" };

	/** 可変長文字列型の入力値 */
	public static String[] FIXED_LENGTH_STRING_TYPE_INPUT_VALUES = { "CHAR(5)",
			"NCHAR(4)", "char(6)", "nchar(8)" };

	/** 数値型の入力値 */
	public static String[] NUMBER_TYPE_INPUT_VALUES = { "NUMBER",
			"BINARY_FLOAT(10,0)", "BINARY_DOUBLE(8,9)", "number(4)",
			"binary_float(4)", "binary_double(3)" };

	/** 日付型の入力値 */
	public static String[] DATE_TYPE_INPUT_VALUES = { "DATE",
			"TIMESTAMP ORACLE 9I", "TIMESTAMP WITH TIMEZONE",
			"TIMESTAMP WITH LOCAL TIMEZONE", "INTERVAL YEAR TO MONTH",
			"INTERVAL DAY TO SECOND", "date", "timestamp oracle 9i",
			"timestamp with timezone", "timestamp with local timezone",
			"interval year to month", "interval day to second" };

	/**
	 * <p>
	 * 文字列型の入力値を取得します。
	 * </p>
	 *
	 * @return 文字列型の入力値
	 */
	public static String[] stringTypeInputValues() {
		String[] result = {};
		result = ArrayUtils.addAll(result,
				VARIABLE_LENGTH_STRING_TYPE_INPUT_VALUES);
		result = ArrayUtils.addAll(result,
				FIXED_LENGTH_STRING_TYPE_INPUT_VALUES);
		return result;
	}

	/**
	 * <p>
	 * 全ての入力値を取得します。
	 * </p>
	 *
	 * @return 全ての入力値
	 */
	public static String[] allInputValues() {
		String[] result = {};
		result = ArrayUtils.addAll(result,
				VARIABLE_LENGTH_STRING_TYPE_INPUT_VALUES);
		result = ArrayUtils.addAll(result,
				FIXED_LENGTH_STRING_TYPE_INPUT_VALUES);
		result = ArrayUtils.addAll(result, NUMBER_TYPE_INPUT_VALUES);
		result = ArrayUtils.addAll(result, DATE_TYPE_INPUT_VALUES);
		return result;
	}

	/**
	 * <p>
	 * 全ての入力値から指定された入力値を除外して取得します。
	 * </p>
	 *
	 * @param ignoreValues
	 *            除外する入力値
	 *
	 * @return 全ての入力値から指定された入力値を除外したもの
	 */
	public static String[] allInputValues(String[] ignoreValues) {
		List<String> list = new ArrayList<String>(
				Arrays.asList(allInputValues()));
		List<String> removeList = new ArrayList<String>(
				Arrays.asList(ignoreValues));
		list.removeAll(removeList);
		return list.toArray(new String[list.size()]);
	}

	@RunWith(Theories.class)
	public static class 文字列型判定でtrueになる場合のテスト {

		@DataPoints
		public static String[] INPUT_VALUES = stringTypeInputValues();

		@Theory
		public void testIsStringType文字列型の場合trueになる事(String inputValue)
				throws Exception {
			// SetUp
			DataType dataType = new DataType(inputValue);

			// Exercice
			boolean actual = dataType.isStringType();

			// Verify
			assertThat(actual, is(true));
		}

	}

	@RunWith(Theories.class)
	public static class 文字列型判定でfalseになる場合のテスト {

		@DataPoints
		public static String[] INPUT_VALUES = allInputValues(stringTypeInputValues());

		@Theory
		public void testIsStringType文字列型以外の場合falseになる事(String inputValue)
				throws Exception {
			// SetUp
			DataType dataType = new DataType(inputValue);

			// Exercice
			boolean actual = dataType.isStringType();

			// Verify
			assertThat(actual, is(false));
		}

	}

	@RunWith(Theories.class)
	public static class 数値型でtrueになる場合のテスト {

		@DataPoints
		public static String[] INPUT_VALUES = NUMBER_TYPE_INPUT_VALUES;

		@Theory
		public void testIsNumberType数値型の場合trueになる事(String inputValue)
				throws Exception {
			// SetUp
			DataType dataType = new DataType(inputValue);

			// Exercice
			boolean actual = dataType.isNumberType();

			// Verify
			assertThat(actual, is(true));
		}

	}

	@RunWith(Theories.class)
	public static class 数値型でfalseになる場合のテスト {

		@DataPoints
		public static String[] INPUT_VALUES = allInputValues(NUMBER_TYPE_INPUT_VALUES);

		@Theory
		public void testIsNumberType数値型の場合falseになる事(String inputValue)
				throws Exception {
			// SetUp
			DataType dataType = new DataType(inputValue);

			// Exercice
			boolean actual = dataType.isNumberType();

			// Verify
			assertThat(actual, is(false));
		}

	}

	@RunWith(Theories.class)
	public static class 日付型でtrueになる場合のテスト {

		@DataPoints
		public static String[] INPUT_VALUES = DATE_TYPE_INPUT_VALUES;

		@Theory
		public void testIsDateType日付型の場合trueになる事(String inputValue)
				throws Exception {
			// SetUp
			DataType dataType = new DataType(inputValue);

			// Exercice
			boolean actual = dataType.isDateType();

			// Verify
			assertThat(actual, is(true));
		}

	}

	@RunWith(Theories.class)
	public static class 日付型でfalseになる場合のテスト {

		@DataPoints
		public static String[] INPUT_VALUES = allInputValues(DATE_TYPE_INPUT_VALUES);

		@Theory
		public void testIsDateType日付型の場合falseになる事(String inputValue)
				throws Exception {
			// SetUp
			DataType dataType = new DataType(inputValue);

			// Exercice
			boolean actual = dataType.isDateType();

			// Verify
			assertThat(actual, is(false));
		}

	}

}
