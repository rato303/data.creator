package rato.data.creator.domain;

import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.seasar.doma.Domain;

/**
 * <p>
 * 列名を表すドメインです。
 * </p>
 *
 * @author toshiya
 *
 */
@Domain(valueType = String.class)
public class ColumnName {

	/** ドメインの値 */
	private final String value;

	/**
	 * 列名を表すドメインを生成します。
	 *
	 * @param value
	 *            列名
	 */
	public ColumnName(String value) {
		this.value = value;
	}

	/**
	 * 列名を取得します。
	 *
	 * @return 列名
	 */
	public String getValue() {
		return this.value;
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
