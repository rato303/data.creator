package rato.data.creator.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.text.StrBuilder;

/**
 * <p>
 * 日時型の値チェックをするクラスです。
 * </p>
 *
 * @author toshiya
 *
 */
public final class TimeStampCheckUtil {

	/** 日時型変換で許容するパターン */
	public static final String[] ALLOW_PATTERNS = { "yyyyMMddHHmmssS",
			"yyyyMdHmsS", "yyyy-MM-dd HH:mm:ss.S", "yyyy-M-d H:m:s.S",
			"yyyy/MM/dd HH:mm:ss.S", "yyyy/M/d H:m:s.S" };

	/**
	 * <p>
	 * コンストラクタ
	 * </p>
	 * ユーティリティクラスなのでインスタンス不可
	 */
	private TimeStampCheckUtil() {
	}

	public static final String getAllowPatterns() {
		StrBuilder str = new StrBuilder(100);
		for (String allowPattern : ALLOW_PATTERNS) {
			str.appendln(allowPattern);
		}
		return str.toString();
	}

	/**
	 * <p>
	 * 与えられた値が日時型に変換できるか判定します。
	 * </p>
	 *
	 * @param target
	 *            日時型に変換する値
	 * @return 日時型に変換できる場合は「true」それ以外の場合は「false」
	 */
	public static boolean checkFormat(String target) {
		SimpleDateFormat sdf;

		for (String allowPattern : ALLOW_PATTERNS) {
			sdf = new SimpleDateFormat(allowPattern);
			try {
				sdf.parse(target);
				return true;
			} catch (ParseException e) {
				continue;
			}
		}

		return false;
	}

}
