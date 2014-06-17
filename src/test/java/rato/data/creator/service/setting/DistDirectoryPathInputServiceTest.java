package rato.data.creator.service.setting;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rato.data.creator.bo.CommandLineServiceResultBo;
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

	private CommandLineServiceResultBo beforeCommandLineServiceResultBo;

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

	/**
	 * 事前処理
	 */
	@Before
	public void setUp() {
		this.service = new DistDirectoryPathInputService();
		this.beforeCommandLineServiceResultBo = new CommandLineServiceResultBo(
				new CommandLineServiceResultBo(),
				new DistDirectoryPathInputServiceFactory());
	}

	@Test
	public void 入力値が未入力の場合ファクトリクラスがDistDirectoryPathInputServiceFactoryである事() {
		// SetUp
		InputValue inputValue = new InputValue();

		// Exercise
		CommandLineServiceResultBo actual = this.service.mainProcess(
				this.beforeCommandLineServiceResultBo, inputValue);

		// Verify
		assertThat(
				actual.getFactory() instanceof DistDirectoryPathInputServiceFactory,
				is(true));
	}

	@Test
	public void 入力値のディレクトリが存在しない場合入力値のディレクトリが作成され設定情報に入力値が設定されている事() {
		// SetUp
		String excepted = getCurrentProjectPath(NOT_EXISTS_PATH);
		InputValue inputValue = new InputValue(excepted);

		// Exercise
		CommandLineServiceResultBo actual = this.service.mainProcess(
				this.beforeCommandLineServiceResultBo, inputValue);

		// Verify
		assertThat(new File(excepted).exists(), is(true));
		assertThat(actual.getConfigurationBo().getDistDirectoryPath(),
				is(excepted));
	}

	@Test
	public void 入力値のディレクトリが存在する場合入力値のディレクトリが存在し設定情報に入力値が設定されている事() {
		// SetUp
		String expected = getCurrentProjectPath(EXISTS_PATH);
		InputValue inputValue = new InputValue(expected);

		// Exercise
		CommandLineServiceResultBo actual = this.service.mainProcess(
				this.beforeCommandLineServiceResultBo, inputValue);

		// Verify
		assertThat(new File(expected).exists(), is(true));
		assertThat(actual.getConfigurationBo().getDistDirectoryPath(),
				is(expected));
	}

}
