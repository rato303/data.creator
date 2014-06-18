package rato.data.creator.bo;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * 入力値情報です。
 * </p>
 *
 * @author toshiya
 *
 */
public class InputValue implements Serializable {

	/** シリアルID */
	private static final long serialVersionUID = -505260395564380005L;

	/** 入力値 */
	private String value;

	/**
	 * <p>
	 * コンストラクタ
	 * </p>
	 */
	public InputValue() {
		super();
		this.value = null;
	}

	/**
	 * <p>
	 * コンストラクタ
	 * </p>
	 *
	 * @param arg
	 *            入力情報
	 */
	public InputValue(String arg) {
		this.value = arg;
	}

	/**
	 * <p>
	 * 入力値を取得します。
	 * </p>
	 *
	 * @return 入力値
	 */
	public String getValue() {
		return value;
	}

	/**
	 * <p>
	 * 入力値を{@link Integer}型で取得します。
	 * </p>
	 *
	 * @return
	 * @throws NumberFormatException
	 */
	public Integer getIntegerValue() throws NumberFormatException {
		return Integer.parseInt(this.value);
	}

	/**
	 * <p>
	 * 入力値を大文字に変換した値を取得します。
	 * </p>
	 *
	 * @return 入力値を大文字に変換した値
	 */
	public String getUpperValue() {
		if (this.isEmpty()) {
			return this.value;
		}
		return this.value.toUpperCase();
	}

	/**
	 * <p>
	 * 入力値を設定します。
	 * </p>
	 *
	 * @param arg
	 *            入力値
	 */
	public void setValue(String arg) {
		this.value = arg;
	}

	/**
	 * <p>
	 * 入力値をクリアします。
	 * </p>
	 */
	public void clear() {
		this.value = null;
	}

	/**
	 * <p>
	 * 入力値が未入力か判定します。
	 * </p>
	 *
	 * @return 未入力の場合は「true」入力状態の場合は「false」
	 */
	public boolean isEmpty() {
		return StringUtils.isEmpty(this.value);
	}

	/**
	 * <p>
	 * 入力値が入力状態か判定します。
	 * </p>
	 *
	 * @return 入力状態の場合は「true」未入力の場合は「false」
	 */
	public boolean isNotEmpty() {
		return !this.isEmpty();
	}

}
