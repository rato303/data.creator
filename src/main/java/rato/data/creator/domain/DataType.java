package rato.data.creator.domain;

import static org.apache.commons.lang3.StringUtils.upperCase;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.seasar.doma.Domain;

/**
 * <p>
 * 列のデータ型を表すドメインです。
 * </p>
 *
 * @author toshiya
 *
 */
@Domain(valueType = String.class)
public class DataType {

	/** ドメインの値 */
	private final String value;

	/**
	 * 列のデータ型を表すドメインを生成します。
	 *
	 * @param value
	 *            列のデータ型
	 */
	public DataType(String value) {
		this.value = value;
	}

	/**
	 * 列のデータ型を取得します。
	 *
	 * @return 列のデータ型
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * <p>
	 * 可変長文字列型かどうか判定します。
	 * </p>
	 *
	 * @return 可変長文字列型の場合は「true」それ以外の場合は「false」
	 */
	public boolean isVariableLengthString() {
		// TODO 共通化処理
		boolean result = false;

		String target = upperCase(this.value);

		final Set<String> set = new HashSet<String>();
		set.add("VARCHAR2");
		set.add("NVARCHAR2");

		for (String string : set) {
			if (StringUtils.startsWith(target, string)) {
				result = true;
				break;
			}
		}

		return result;
	}

	/**
	 * <p>
	 * 固定長文字列型かどうか判定します。
	 * </p>
	 *
	 * @return 固定長文字列型の場合は「true」それ以外の場合は「false」
	 */
	public boolean isFixedLengthString() {
		boolean result = false;

		String target = upperCase(this.value);

		final Set<String> set = new HashSet<String>();
		set.add("CHAR");
		set.add("NCHAR");
		for (String string : set) {
			if (StringUtils.startsWith(target, string)) {
				result = true;
				break;
			}
		}

		return result;
	}

	/**
	 * <p>
	 * 文字列型かどうか判定します。
	 * </p>
	 *
	 * @return 文字列型の場合は「true」それ以外の場合は「false」
	 */
	public boolean isStringType() {
		return this.isFixedLengthString() || this.isVariableLengthString();
	}

	/**
	 * <p>
	 * 数値型かどうか判定します。
	 * </p>
	 *
	 * @return 数値型の場合は「true」それ以外の場合は「false」
	 */
	public boolean isNumberType() {
		boolean result = false;

		String target = upperCase(this.value);

		final Set<String> set = new HashSet<String>();
		set.add("NUMBER");
		set.add("BINARY_FLOAT");
		set.add("BINARY_DOUBLE");

		for (String string : set) {
			if (StringUtils.startsWith(target, string)) {
				result = true;
				break;
			}
		}

		return result;
	}

	/**
	 * <p>
	 * 日付型かどうか判定します。
	 * </p>
	 *
	 * @return 日付型の場合は「true」それ以外の場合は「false」
	 */
	public boolean isDateType() {
		boolean result = false;

		String target = upperCase(this.value);

		final Set<String> set = new HashSet<String>();
		set.add("DATE");
		set.add("TIMESTAMP ORACLE 9I");
		set.add("TIMESTAMP WITH TIMEZONE");
		set.add("TIMESTAMP WITH LOCAL TIMEZONE");
		set.add("INTERVAL YEAR TO MONTH");
		set.add("INTERVAL DAY TO SECOND");

		for (String string : set) {
			if (StringUtils.startsWith(target, string)) {
				result = true;
				break;
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Objects.toString(this.value);
	}

}
