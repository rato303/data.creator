package rato.data.creator.bo;

/**
 * 入力値情報です。
 *
 * @author toshiya
 *
 */
public class InputValue {

    /** 入力値 */
    private String value;

    /**
     * コンストラクタ
     */
    public InputValue() {
        super();
        this.value = null;
    }

    /**
     * コンストラクタ
     *
     * @param arg 入力情報
     */
    public InputValue(String arg) {
        this.value = arg;
    }

    /**
     * 入力値を取得します。
     * @return 入力値
     */
    public String getValue() {
        return value;
    }

    /**
     * 入力値を設定します。
     * @param arg 入力値
     */
    public void setValue(String arg) {
        this.value = arg;
    }

    /**
     * 入力値をクリアします。
     */
    public void clear() {
        this.value = null;
    }

    /**
     * 入力値が未入力か判定します。
     *
     * @return 未入力の場合は「true」入力状態の場合は「false」
     */
    public boolean isEmpty() {
        return this.value == null;
    }

    /**
     * 入力値が入力状態か判定します。
     *
     * @return 入力状態の場合は「true」未入力の場合は「false」
     */
    public boolean isNotEmpty() {
        return !this.isEmpty();
    }

}
