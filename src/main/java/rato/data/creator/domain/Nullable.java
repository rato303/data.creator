package rato.data.creator.domain;

import java.util.Objects;

import org.seasar.doma.Domain;

/**
 * <p>
 * 列にNULLを指定できるかどうかを表すドメインです。
 * </p>
 *
 * @author toshiya
 *
 */
@Domain(valueType = String.class)
public class Nullable {

	/** ドメインの値 */
	private final String value;

	/**
	 * 列にNULLを指定できるかどうかを表すドメインを生成します。
	 *
	 * @param value
	 *            列にNULLを指定できるかどうか
	 */
	public Nullable(String value) {
		this.value = value;
	}

	/**
	 * 列にNULLを指定できるかどうかを取得します。
	 *
	 * @return 列にNULLを指定できるかどうか
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
