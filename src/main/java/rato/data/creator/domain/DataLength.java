package rato.data.creator.domain;

import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.seasar.doma.Domain;

/**
 * <p>
 * データの長さを表すドメインです。
 * </p>
 *
 * @author toshiya
 *
 */
@Domain(valueType = Integer.class)
public class DataLength {

	/** ドメインの値 */
	private final Integer value;

	/**
	 * データの長さを表すドメインを生成します。
	 *
	 * @param value
	 *            データの長さ
	 */
	public DataLength(Integer value) {
		this.value = value;
	}

	/**
	 * データの長さを取得します。
	 *
	 * @return データの長さ
	 */
	public Integer getValue() {
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
