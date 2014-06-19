package rato.data.creator.validation;

/**
 * <p>
 * 文字列型の値チェックをするクラスです。
 * </p>
 *
 * @author toshiya
 *
 */
public final class StringCheckUtil {

	/**
	 * <p>
	 * コンストラクタ
	 * </p>
	 * ユーティリティクラスなのでインスタンス不可
	 */
	private StringCheckUtil() {
	}

	/**
	 * <p>
	 * 文字列の長さが超えているか判定します。
	 * </p>
	 *
	 * @param target 長さをチェックする文字列
	 *
	 * @param maxLength 許容する長さ
	 *
	 * @return 文字列の長さが許容範囲外の場合は「true」許容範囲内の場合は「false」
	 */
	public static boolean exceedLength(String target, int maxLength) {
		int targetLength = target.length();

		for (int i = 0; i < targetLength; i++) {
			int codeuint = target.codePointAt(i);

			if (0xffff < codeuint) {
				i++;
			}

			if (targetLength < i) {
				return true;
			}
		}

		return false;
	}

}
