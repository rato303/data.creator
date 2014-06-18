package rato.data.creator.domain;

import java.util.Objects;

import org.seasar.doma.Domain;

/**
 * <p>
 * 小数点以下の桁数を表すドメインです。
 * </p>
 *
 * @author toshiya
 *
 */
@Domain(valueType = Integer.class)
public class DataScale {

	/** ドメインの値 */
	private final Integer value;

	/**
	 * 小数点以下の桁数を表すドメインを生成します。
	 *
	 * @param value
	 *            小数点以下の桁数
	 */
	public DataScale(Integer value) {
		this.value = value;
	}

	/**
	 * 小数点以下の桁数を取得します。
	 *
	 * @return 小数点以下の桁数
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
