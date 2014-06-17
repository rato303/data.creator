package rato.data.creator.service.setting;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.ConfigurationBo;
import rato.data.creator.bo.InputValue;
import rato.data.creator.service.factory.DistDirectoryPathInputServiceFactory;

/**
 * {@link DistDirectoryPathInputService}のテストクラスです。
 *
 * @author toshiya
 *
 */
public class DistDirectoryPathInputServiceTest {

	// TemporaryFolderを使用するようにする

	private DistDirectoryPathInputService service;

	private ConfigurationBo configurationBo;

	private static final String EXISTS_PATH = "exists_test_dir";

	private static final String NOT_EXISTS_PATH = "not_exists_test_dir";

	/**
	 * テスト用のディレクトリを作成します。
	 */
	@BeforeClass
	public static void beforeClass() {
		File existsPath = new File(getCurrentProjectPath(EXISTS_PATH));
		existsPath.mkdirs();
		File notExistsPath = new File(getCurrentProjectPath(NOT_EXISTS_PATH));
		notExistsPath.delete();
	}

	@AfterClass
	public static void afterClass() {
		File existsPath = new File(getCurrentProjectPath(EXISTS_PATH));
		existsPath.delete();
		File notExistsPath = new File(getCurrentProjectPath(NOT_EXISTS_PATH));
		notExistsPath.delete();
	}

	private static String getCurrentProjectPath(String target) {
		String currentProjectPath = new File(".").getAbsoluteFile().getParent();
		return currentProjectPath + System.getProperty("file.separator")
				+ target;
	}

	@Before
	public void setUp() {
		this.service = new DistDirectoryPathInputService();
	}

	/**
	 * <p>
	 * 入力値が未入力の場合のテスト
	 * </p>
	 * <p>
	 * 期待値
	 * </p>
	 * <ul>
	 * <li>{@link DistDirectoryPathInputServiceFactory}のファクトリが取得できること</li>
	 * </ul>
	 *
	 */
	@Test
	public void testConfigurationMainProcess_inputEmpty() {
		// SetUp
		InputValue inputValue = new InputValue();
		CommandLineServiceResultBo beforeCommandLineServiceResultBo = new CommandLineServiceResultBo(
				new CommandLineServiceResultBo(),
				new DistDirectoryPathInputServiceFactory());

		// Exercise
		CommandLineServiceResultBo actual = this.service.mainProcess(
				beforeCommandLineServiceResultBo, inputValue);

		// Verify
		assertThat(
				actual.getFactory() instanceof DistDirectoryPathInputServiceFactory,
				is(true));
	}

	/**
	 * <p>
	 * 入力値のディレクトリが存在しない場合のテスト
	 * </p>
	 * <p>
	 * 期待値
	 * </p>
	 * <ul>
	 * <li>入力値のディレクトリが作成されること</li>
	 * <li>アプリケーション設定情報に入力値が設定されること</li>
	 * </ul>
	 */
	@Test
	public void testConfigurationMainProcess_notExistDistDirectory() {
		this.service = new DistDirectoryPathInputService();

		String excepted = getCurrentProjectPath(NOT_EXISTS_PATH);

		InputValue inputValue = new InputValue(excepted);
		this.service.mainProcess(new CommandLineServiceResultBo(), inputValue);

		assertThat(new File(excepted).exists(), is(true));
		assertThat(configurationBo.getDistDirectoryPath(), is(excepted));
	}

	/**
	 * <p>
	 * 入力値のディレクトリが存在する場合のテスト
	 * </p>
	 * <p>
	 * 期待値
	 * </p>
	 * <ul>
	 * <li>入力値のディレクトリが存在していること</li>
	 * <li>アプリケーション設定情報に入力値が設定されること</li>
	 * </ul>
	 */
	@Test
	public void testConfigurationMainProcess_existDistDirectory() {
		this.service = new DistDirectoryPathInputService();

		String expected = getCurrentProjectPath(EXISTS_PATH);

		InputValue inputValue = new InputValue(expected);
		this.service.mainProcess(new CommandLineServiceResultBo(), inputValue);

		assertThat(new File(expected).exists(), is(true));
		assertThat(configurationBo.getDistDirectoryPath(), is(expected));
	}

}
