package rato.data.creator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.text.StrBuilder;

/**
 * リソースに関するユーティリティクラスです。
 *
 * @author toshiya
 *
 */
public final class ResourceUtil {

	/** システムのファイル区切り文字 */
	public static final String FILE_SEPARATOR = System
			.getProperty("file.separator");

	/**
	 * <p>
	 * コンストラクタ
	 * </p>
	 * ユーティリティクラスなのでインスタンス不可
	 */
	private ResourceUtil() {
	}

	/**
	 * <p>
	 * クラスローダーから取得した実行パスを取得します。
	 * </p>
	 *
	 * @return 実行パス
	 */
	public static String getCurrentFilePath() {
		return getCurrentFilePath(ResourceUtil.class);
	}

	/**
	 * <p>
	 * クラスローダーから取得した実行パスを取得します。
	 * </p>
	 *
	 * @param clazz クラスローダーを取得するクラス
	 *
	 * @return 実行パス
	 */
	public static String getCurrentFilePath(Class<?> clazz) {
		return clazz.getResource("/").getPath();
	}

	/**
	 * <p>
	 * クラスローダーから取得した実行パス配下のパスを取得します。
	 * </p>
	 *
	 * @param clazz クラスローダーを取得するクラス
	 *
	 * @param pathArr パスの配列
	 *
	 * @return パスの配列を全てシステムのファイル区切り文字で連結したパス
	 */
	public static String getFilePath(Class<?> clazz, String... pathArr) {
		StrBuilder result = new StrBuilder();
		result.append(getCurrentFilePath(clazz));
		for (String path : pathArr) {
			result.append(FILE_SEPARATOR);
			result.append(path);
		}
		return result.toString();
	}

	/**
	 * <p>
	 * クラスローダーから取得した実行パス配下のパスを取得します。
	 * </p>
	 * @param pathArr パスの配列
	 *
	 * @return パスの配列を全てシステムのファイル区切り文字で連結したパス
	 */
	public static String getFilePath(String... pathArr) {
		return getCurrentFilePath(ResourceUtil.class);
	}

	/**
	 * <p>
	 * Propertiesファイルを読み込みます。
	 * </p>
	 *
	 * @param filePath
	 *            読み込むファイルのフルパス
	 *
	 * @return 読み込んだファイルの{@link Properties}のインスタンス
	 */
	public static Properties propertiesFileLoad(String filePath) {
		return propertiesFileLoad(new File(filePath));
	}

	/**
	 * <p>
	 * Propertiesファイルを読み込みます。
	 * </p>
	 *
	 * @param readFile
	 *            読み込むファイルの{@link File}インスタンス
	 *
	 * @return 読み込んだファイルの{@link Properties}のインスタンス
	 */
	public static Properties propertiesFileLoad(File readFile) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(readFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

}
