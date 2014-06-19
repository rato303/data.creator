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
@Domain(valueType = String.class, factoryMethod = "of")
public enum Nullable {

	/** NULLを許可 */
	Y("Y")
	/** NULLを不許可 */
	, N("N");

	/** ドメインの値 */
	private final String value;

	/**
	 * 列にNULLを指定できるかどうかを表すドメインを生成します。
	 *
	 * @param value
	 *            列にNULLを指定できるかどうか
	 */
	private Nullable(String value) {
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

	/**
	 * <p>
	 * 必須入力か判定します。
	 * </p>
	 *
	 * @return 必須入力の場合は「true」それ以外の場合は「false」
	 */
	public boolean isRequired() {
		return N.getValue().equals(this.value);
	}

	/**
	 * <p>
	 * 渡された文字列から{@link Nullable}の値を生成します。
	 * </p>
	 *
	 * @param value
	 *            {@link Nullable}に変換する文字列
	 * @return 変換できた{@link Nullable}のインスタンス
	 * @throws IllegalArgumentException
	 */
	public static Nullable of(String value) throws IllegalArgumentException {
		Nullable[] arrays = Nullable.values();
		for (Nullable nullable : arrays) {
			if (nullable.getValue().equals(value)) {
				return nullable;
			}
		}
		throw new IllegalArgumentException(); // TODO メッセージの設定
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
