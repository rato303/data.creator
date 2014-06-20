package rato.data.creator.validation;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * 数値型の値チェックをするクラスです。
 * </p>
 *
 * @author toshiya
 *
 */
public final class NumberCheckUtil {

	/**
	 * <p>
	 * コンストラクタ
	 * </p>
	 * ユーティリティクラスなのでインスタンス不可
	 */
	private NumberCheckUtil() {
	}

	/**
	 * <p>
	 * 小数か判定します。
	 * </p>
	 *
	 * @param target
	 *            判定する値
	 *
	 * @return 小数の場合は「true」それ以外の場合は「false」
	 */
	public static boolean isDecimal(String target) {
		int length = StringUtils.isNotEmpty(target) ? target.length() : 0;
		int dotCount = 0;
		final int dotCodeuint = 46;
		for (int i = 0; i < length; i++) {
			int codeuint = target.codePointAt(i);
			if (dotCodeuint == codeuint) {
				dotCount++;
			}
			if (1 < dotCount) {
				return false;
			}
		}
		return dotCount == 1;
	}

	/**
	 * <p>
	 * 小数点以下の桁数を取得します。
	 * </p>
	 *
	 * @param target
	 *            小数点以下の桁数を取得する値
	 * @return 小数点以下の桁数
	 */
	public static int getDecimalLength(String target) {
		int length = StringUtils.isNotEmpty(target) ? target.length() : 0;
		int dotCount = 0;
		int decimalLength = 0;
		final int dotCodeuint = 46;
		for (int i = 0; i < length; i++) {
			if (0 < dotCount) {
				decimalLength++;
			}
			int codeuint = target.codePointAt(i);
			if (dotCodeuint == codeuint) {
				dotCount++;
			}
		}
		return decimalLength;
	}

}
