package rato.data.creator.domain;

import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.seasar.doma.Domain;

/**
 * <p>
 * 精度を表すドメインです。
 * </p>
 *
 * @author toshiya
 *
 */
@Domain(valueType = Integer.class)
public class DataPrecision {

	/** ドメインの値 */
	private final Integer value;

	/**
	 * 精度を表すドメインを生成します。
	 *
	 * @param value
	 *            精度
	 */
	public DataPrecision(Integer value) {
		this.value = value;
	}

	/**
	 * 精度を取得します。
	 *
	 * @return 精度
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
