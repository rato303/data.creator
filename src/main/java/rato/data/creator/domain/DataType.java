package rato.data.creator.domain;

import static org.apache.commons.lang3.StringUtils.upperCase;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
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

	/** データ型のカテゴリ */
	private final DataTypeCategory category;

	/**
	 * <p>
	 * 列のデータ型の種別を表す列挙型です。
	 * </p>
	 * @author toshiya
	 *
	 */
	public enum DataTypeCategory {

		/** 固定長文字列 */
		CHAR
		/** 可変長文字列 */
		, VARCHAR
		/** 数値 */
		, NUMBER
		/** 日付型 */
		, DATE
		/** 日時型 */
		, TIMESTAMP;

	}

	/**
	 * 列のデータ型を表すドメインを生成します。
	 *
	 * @param value
	 *            列のデータ型
	 */
	public DataType(String value) {
		this.value = value;
		this.category = this.of(value);
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
		return this.isVariableLengthString(this.value);
	}

	/**
	 * <p>
	 * 可変長文字列型かどうか判定します。
	 * </p>
	 *
	 * @return 可変長文字列型の場合は「true」それ以外の場合は「false」
	 */
	private boolean isVariableLengthString(String value) {
		// TODO 共通化処理
		boolean result = false;

		String target = upperCase(value);

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
	private boolean isFixedLengthString() {
		return this.isFixedLengthString(this.value);
	}

	/**
	 * <p>
	 * 固定長文字列型かどうか判定します。
	 * </p>
	 *
	 * @return 固定長文字列型の場合は「true」それ以外の場合は「false」
	 */
	private boolean isFixedLengthString(String value) {
		boolean result = false;

		String target = upperCase(value);

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
		return this.isNumberType(this.value);
	}

	/**
	 * <p>
	 * 数値型かどうか判定します。
	 * </p>
	 *
	 * @return 数値型の場合は「true」それ以外の場合は「false」
	 */
	public boolean isNumberType(String value) {
		boolean result = false;

		String target = upperCase(value);

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
		return this.isDateType(this.value);
	}

	/**
	 * <p>
	 * 日付型かどうか判定します。
	 * </p>
	 *
	 * @return 日付型の場合は「true」それ以外の場合は「false」
	 */
	public boolean isDateType(String value) {
		boolean result = false;

		String target = upperCase(value);

		final Set<String> set = new HashSet<String>();
		set.add("DATE");
		set.add("TIMESTAMP");
//		set.add("TIMESTAMP ORACLE 9I");
//		set.add("TIMESTAMP WITH TIMEZONE");
//		set.add("TIMESTAMP WITH LOCAL TIMEZONE");
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

	private DataTypeCategory of(String value) {
		if (this.isFixedLengthString(value)) {
			return DataTypeCategory.CHAR;
		}
		if (this.isVariableLengthString(value)) {
			return DataTypeCategory.VARCHAR;
		}
		if (this.isNumberType(value)) {
			return DataTypeCategory.NUMBER;
		}
		if (this.isDateType(value)) {
			return DataTypeCategory.DATE;
		}
		throw new IllegalArgumentException();	// TODO メッセージ
	}

	/**
	 * データ型のカテゴリを取得します。
	 * @return データ型のカテゴリ
	 */
	public DataTypeCategory getCategory() {
	    return category;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object paramObject) {
		return EqualsBuilder.reflectionEquals(this, paramObject);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
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
