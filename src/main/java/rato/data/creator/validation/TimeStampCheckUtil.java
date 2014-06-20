package rato.data.creator.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

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
	private static final Set<String> ALLOW_PATTERNS;

	static {
		ALLOW_PATTERNS = new HashSet<String>(3);
		ALLOW_PATTERNS.add("yyyyMMddHHmmssS");
		ALLOW_PATTERNS.add("yyyyMdHmsS");
		ALLOW_PATTERNS.add("yyyy-MM-dd HH:mm:ss.S");
		ALLOW_PATTERNS.add("yyyy-M-d H:m:s.S");
		ALLOW_PATTERNS.add("yyyy/MM/dd HH:mm:ss.S");
		ALLOW_PATTERNS.add("yyyy/M/d H:m:s.S");
	}

	/**
	 * <p>
	 * コンストラクタ
	 * </p>
	 * ユーティリティクラスなのでインスタンス不可
	 */
	private TimeStampCheckUtil() {
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
