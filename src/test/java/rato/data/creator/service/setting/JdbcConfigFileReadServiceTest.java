package rato.data.creator.service.setting;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.Properties;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.InputValue;
import rato.data.creator.exception.RetryException;
import rato.data.creator.rules.TestFixtureResource;
import rato.data.creator.service.factory.DistDirectoryPathInputServiceFactory;

@RunWith(Enclosed.class)
public class JdbcConfigFileReadServiceTest {

	@RunWith(JUnit4.class)
	public static class データベース接続定義ファイルが存在しない場合のテスト {

		private JdbcConfigFileReadService service;

		private CommandLineServiceResultBo beforeResult;

		@Rule
		public ExpectedException thrown = ExpectedException.none();

		@Rule
		public TemporaryFolder folder = new TemporaryFolder();

		@Before
		public void setUp() {
			this.service = new JdbcConfigFileReadService();
			this.beforeResult = CommandLineServiceResultBo.create();
		}

		@Test
		public void testValidateProcess継続可能例外が発行される事() throws Exception {
			// SetUp
			this.thrown.expect(RetryException.class);
			this.thrown.expectMessage("データベース接続定義ファイルが見つかりませんでした。");

			File testDir = folder.newFolder("unknown");
			File testConfFile = new File(testDir.getAbsolutePath()
					+ System.getProperty("file.separator") + "jdbc.properties");

			// Exercice
			this.service.validateProcess(this.beforeResult, new InputValue(
					testConfFile.getAbsolutePath()));

			// Verify
		}

	}

	@RunWith(JUnit4.class)
	public static class データベース接続定義ファイルの記述が誤っている場合のテスト {

		private JdbcConfigFileReadService service;

		private CommandLineServiceResultBo beforeResult;

		private InputValue inputValue;

		@Rule
		public ExpectedException thrown = ExpectedException.none();

		@Rule
		public TestFixtureResource testFixtureResource = new TestFixtureResource();

		@Before
		public void setUp() {
			this.service = new JdbcConfigFileReadService();
			this.beforeResult = CommandLineServiceResultBo.create();
			this.thrown.expect(RetryException.class);
			this.inputValue = new InputValue(
					this.testFixtureResource
							.getTestFixtureResource("jdbc.properties"));
		}

		@Test
		public void testValidateProcessJdbcDriverClassNameが設定されていない場合継続可能例外が発行される事()
				throws Exception {
			// SetUp
			this.thrown
					.expectMessage("指定されたデータベース接続定義ファイルにはjdbc.driver.classが設定されていません。");

			// Exercice
			// this.service.validateProcess(
			// this.beforeResult,
			// new InputValue(this.testFixtureResource
			// .getTestFixtureResource("jdbc.properties")));
			this.service.validateProcess(this.beforeResult, this.inputValue);

			// Verify
		}

		@Test
		@Ignore
		public void testExecuteJdbcDriverClassNameが設定されていない場合再実行用の結果が取得できる事()
				throws Exception {
			// SetUp

			// Exercice

			// Verify
			fail("まだ実装されていません");
		}

		@Test
		public void testValidateProcessJdbcDriverClassNameが記述されていない場合継続可能例外が発行される事() {
			// SetUp
			this.thrown
					.expectMessage("指定されたデータベース接続定義ファイルにはjdbc.driver.classが設定されていません。");

			// Exercice
			this.service.validateProcess(this.beforeResult, this.inputValue);

			// Verify
		}

		@Test
		@Ignore
		public void testExecuteJdbcDriverClassNameが記述されていない場合再実行用の結果が取得できる事()
				throws Exception {
			// SetUp

			// Exercice

			// Verify
			fail("まだ実装されていません");
		}

		@Test
		public void testValidateProcessJdbcUrlが設定されていない場合継続可能例外が発行される事() {
			// SetUp
			this.thrown
					.expectMessage("指定されたデータベース接続定義ファイルにはjdbc.urlが設定されていません。");

			// Exercice
			this.service.validateProcess(this.beforeResult, this.inputValue);

			// Verify
		}

		@Test
		@Ignore
		public void testExecuteJdbcUrlが設定されていない場合再実行用の結果が取得できる事()
				throws Exception {
			// SetUp

			// Exercice

			// Verify
			fail("まだ実装されていません");
		}

		@Test
		public void testValidateProcessJdbcUrlが記述されていない場合継続可能例外が発行される事() {
			// SetUp
			this.thrown
					.expectMessage("指定されたデータベース接続定義ファイルにはjdbc.urlが設定されていません。");

			// Exercice
			this.service.validateProcess(this.beforeResult, this.inputValue);

			// Verify
		}

		@Test
		@Ignore
		public void testExecuteJdbcUrlが記述されていない場合再実行用の結果が取得できる事()
				throws Exception {
			// SetUp

			// Exercice

			// Verify
			fail("まだ実装されていません");
		}

		@Test
		public void testValidateProcessスキーマ名が設定されていない場合継続可能例外が発行される事() {
			// SetUp
			this.thrown
					.expectMessage("指定されたデータベース接続定義ファイルにはjdbc.schemaが設定されていません。");

			// Exercice
			this.service.validateProcess(this.beforeResult, this.inputValue);

			// Verify
		}

		@Test
		@Ignore
		public void testExecuteスキーマ名が設定されていない場合再実行用の結果が取得できる事()
				throws Exception {
			// SetUp

			// Exercice

			// Verify
			fail("まだ実装されていません");
		}

		@Test
		public void testValidateProcessスキーマ名が記述されていない場合継続可能例外が発行される事() {
			// SetUp
			this.thrown
					.expectMessage("指定されたデータベース接続定義ファイルにはjdbc.schemaが設定されていません。");

			// Exercice
			this.service.validateProcess(this.beforeResult, this.inputValue);

			// Verify
		}

		@Test
		@Ignore
		public void testExecuteスキーマ名が記述されていない場合再実行用の結果が取得できる事()
				throws Exception {
			// SetUp

			// Exercice

			// Verify
			fail("まだ実装されていません");
		}

		@Test
		public void testValidateProcessユーザー名が設定されていない場合継続可能例外が発行される事() {
			// SetUp
			this.thrown
					.expectMessage("指定されたデータベース接続定義ファイルにはjdbc.userが設定されていません。");

			// Exercice
			this.service.validateProcess(this.beforeResult, this.inputValue);

			// Verify
		}

		@Test
		@Ignore
		public void testExecuteユーザー名が設定されていない場合再実行用の結果が取得できる事()
				throws Exception {
			// SetUp

			// Exercice

			// Verify
			fail("まだ実装されていません");
		}

		@Test
		public void testValidateProcessユーザー名が記述されていない場合継続可能例外が発行される事() {
			// SetUp
			this.thrown
					.expectMessage("指定されたデータベース接続定義ファイルにはjdbc.userが設定されていません。");

			// Exercice
			this.service.validateProcess(this.beforeResult, this.inputValue);

			// Verify
		}

		@Test
		@Ignore
		public void testExecuteユーザー名が記述されていない場合再実行用の結果が取得できる事()
				throws Exception {
			// SetUp

			// Exercice

			// Verify
			fail("まだ実装されていません");
		}

		@Test
		public void testValidateProcessパスワードが設定されていない場合継続可能例外が発行される事() {
			// SetUp
			this.thrown
					.expectMessage("指定されたデータベース接続定義ファイルにはjdbc.passwordが設定されていません。");

			// Exercice
			this.service.validateProcess(this.beforeResult, this.inputValue);

			// Verify
		}

		@Test
		@Ignore
		public void testExecuteパスワードが設定されていない場合再実行用の結果が取得できる事()
				throws Exception {
			// SetUp

			// Exercice

			// Verify
			fail("まだ実装されていません");
		}

		@Test
		public void testValidateProcessパスワードが記述されていない場合継続可能例外が発行される事() {
			// SetUp
			this.thrown
					.expectMessage("指定されたデータベース接続定義ファイルにはjdbc.passwordが設定されていません。");

			// Exercice
			this.service.validateProcess(this.beforeResult, this.inputValue);

			// Verify
		}

		@Test
		@Ignore
		public void testExecuteパスワードが記述されていない場合再実行用の結果が取得できる事()
				throws Exception {
			// SetUp

			// Exercice

			// Verify
			fail("まだ実装されていません");
		}

		@Test
		@Ignore
		public void 設定ファイルに記述されているJDBCDriverClassNameが読み込めない場合()
				throws Exception {
			// SetUp

			// Exercice

			// Verify
			fail("まだ実装されていません");
		}

		@Test
		@Ignore
		public void 設定ファイルに記述されている情報でデータベースに接続できない場合() throws Exception {
			// SetUp

			// Exercice

			// Verify
			fail("まだ実装されていません");

		}

	}

	@RunWith(JUnit4.class)
	public static class データベース接続定義ファイルが正しく記述されていた場合のテスト {

		private JdbcConfigFileReadService service;

		private CommandLineServiceResultBo beforeResult;

		private InputValue inputValue;

		@Rule
		public TestFixtureResource testFixtureResource = new TestFixtureResource();

		@Before
		public void setUp() {
			this.service = new JdbcConfigFileReadService();
			this.beforeResult = CommandLineServiceResultBo.create();
			this.inputValue = new InputValue(
					this.testFixtureResource
							.getTestFixtureResource("jdbc.properties"));
		}

		@Test
		@Ignore
		public void testExecute設定ファイルが正しく記述されていた場合設定情報を格納した結果が取得できる事()
				throws Exception {
			// SetUp
			Properties properties = new Properties();
			properties.setProperty("jdbc.driver.class",
					"oracle.jdbc.driver.OracleDriver");
			properties.setProperty("jdbc.url",
					"jdbc:oracle:thin:@172.20.95.45:1522:orcl");
			properties.setProperty("jdbc.schema", "ZKT005");
			properties.setProperty("jdbc.user", "ZKT005");
			properties.setProperty("jdbc.password", "ZKT005");

			// Exercice

			// Verify
			CommandLineServiceResultBo expected = CommandLineServiceResultBo
					.create(this.beforeResult).setFactory(
							new DistDirectoryPathInputServiceFactory());

			CommandLineServiceResultBo actual = this.service.mainProcess(
					this.beforeResult, this.inputValue);

			// TODO DataSourceのequalsメソッドが標準の為エラーになる
			assertThat(actual, is(expected));
		}

	}

}
