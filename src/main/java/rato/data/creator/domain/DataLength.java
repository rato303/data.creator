package rato.data.creator.domain;

import java.util.Objects;

import org.seasar.doma.Domain;

/**
 * <p>
 * 項目長を表すドメインです。
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
	 * 項目長を表すドメインを生成します。
	 *
	 * @param value
	 *            項目長
	 */
	public DataLength(Integer value) {
		this.value = value;
	}

	/**
	 * 項目長を取得します。
	 *
	 * @return 項目長
	 */
	public Integer getValue() {
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
