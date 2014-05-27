package rato.data.creator.bo;

/**
 * 入力情報です。
 *
 * @author toshiya
 *
 */
public class Arg {

	/** 入力情報 */
	private String value;

	/**
	 * コンストラクタ
	 */
	public Arg() {
		super();
		this.value = null;
	}

	/**
	 * コンストラクタ
	 *
	 * @param arg 入力情報
	 */
	public Arg(String arg) {
		this.value = arg;
	}

	/**
	 * 入力情報を取得します。
	 * @return 入力情報
	 */
	public String getValue() {
	    return value;
	}

	/**
	 * 入力情報を設定します。
	 * @param arg 入力情報
	 */
	public void setValue(String arg) {
	    this.value = arg;
	}

	/**
	 * 入力情報をクリアします。
	 */
	public void clear() {
		this.value = null;
	}

	/**
	 * 入力情報が保持されていないか判定します。
	 *
	 * @return 入力情報が保持されていない場合は「true」入力情報が保持されている場合は「false」
	 */
	public boolean isEmpty() {
		return this.value == null;
	}

	/**
	 * 入力情報が保持されているか判定します。
	 *
	 * @return 入力情報が保持されている場合は「true」入力情報が保持されていない場合は「false」
	 */
	public boolean isNotEmpty() {
		return !this.isEmpty();
	}

}
