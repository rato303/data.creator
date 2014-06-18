package rato.data.creator.domain;

import java.util.Objects;

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
