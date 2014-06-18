package rato.data.creator.service.select;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.InputValue;
import rato.data.creator.entity.TableInfo;
import rato.data.creator.exception.RetryException;
import rato.data.creator.service.factory.SelectTableInputServiceFactory;

@RunWith(Enclosed.class)
public class SelectTableInputServiceTest {

	@RunWith(JUnit4.class)
	public static class 入力値が未入力場合のテスト {

		private SelectTableInputService selectTableInputService;

		private CommandLineServiceResultBo beforeResult;

		@Rule
		public ExpectedException thrown = ExpectedException.none();

		@Before
		public void setUp() {
			this.selectTableInputService = new SelectTableInputService();
			this.beforeResult = setUpBeforeResult();
		}

		@Test
		public void testValidateProcess継続可能例外が発行される事() {
			// SetUp
			thrown.expect(RetryException.class);
			thrown.expectMessage("データを作成するテーブルのインデックス番号を入力してください。");

			InputValue inputValue = new InputValue();

			// Exercise
			this.selectTableInputService.validateProcess(this.beforeResult,
					inputValue);

			// Verify
		}

		@Test
		public void testExecute再実行用の結果が取得できる事() {
			// SetUp
			CommandLineServiceResultBo expected = this.beforeResult;

			InputValue inputValue = new InputValue();

			// Exercice
			CommandLineServiceResultBo actual = this.selectTableInputService
					.execute(this.beforeResult, inputValue);

			// Verify
			assertThat(actual, is(expected));
		}

	}

	@RunWith(Theories.class)
	public static class 入力値が正の整数以外のパラメーター化テスト {

		private SelectTableInputService selectTableInputService;

		private CommandLineServiceResultBo beforeResult;

		@Rule
		public ExpectedException thrown = ExpectedException.none();

		@DataPoints
		public static String[] INPUT_VALUES = { "a", "あ", "-1" };

		@Before
		public void setUp() {
			this.selectTableInputService = new SelectTableInputService();
			this.beforeResult = setUpBeforeResult();
		}

		@Theory
		public void testValidateProcess継続可能例外が発行される事(String inputValue) {
			// SetUp
			thrown.expect(RetryException.class);
			thrown.expectMessage("インデックス番号には(正の)整数を入力してください。");

			// Exercice
			this.selectTableInputService.validateProcess(this.beforeResult,
					new InputValue(inputValue));

			// Verify
		}

		@Theory
		public void testExecute再実行用の結果が取得できる事(String inputValue)
				throws Exception {
			// SetUp
			CommandLineServiceResultBo expected = this.beforeResult;

			// Exercice
			CommandLineServiceResultBo actual = this.selectTableInputService
					.execute(this.beforeResult, new InputValue(inputValue));

			// Verify
			assertThat(actual, is(expected));
		}

	}

	@RunWith(Theories.class)
	public static class テーブルのインデックスを超えた場合のテスト {

		private SelectTableInputService selectTableInputService;

		private CommandLineServiceResultBo beforeResult;

		@Rule
		public ExpectedException thrown = ExpectedException.none();

		@DataPoints
		public static String[] INPUT_VALUES = { "3", "4" };

		@Before
		public void setUp() {
			this.selectTableInputService = new SelectTableInputService();
			this.beforeResult = setUpBeforeResult();
		}

		@Theory
		public void testValidateProcess継続可能例外が発行される事(String inputValue)
				throws Exception {
			// SetUp
			thrown.expect(RetryException.class);
			// TODO メッセージ引数を与えられるように実装したら修正する
			thrown.expectMessage("インデックス番号の最大値を超えています。{0}以下の数字を入力してください。");

			// Exercice
			this.selectTableInputService.validateProcess(beforeResult,
					new InputValue(inputValue));

			// Verify
		}

		@Theory
		public void testExecute再実行用の結果が取得できる事(String inputValue) throws Exception {
			// SetUp
			CommandLineServiceResultBo expected = this.beforeResult;

			// Exercice
			CommandLineServiceResultBo actual = this.selectTableInputService
					.execute(this.beforeResult, new InputValue(inputValue));

			// Verify
			assertThat(actual, is(expected));
		}

	}

	/**
	 * <p>
	 * テスト用の共通事前パラメーターの事前処理結果を生成します。
	 * </p>
	 *
	 * @return テスト用の共通事前パラメーターの事前処理結果
	 */
	private static CommandLineServiceResultBo setUpBeforeResult() {
		List<TableInfo> tableInfos = new ArrayList<TableInfo>(3);
		tableInfos.add(new TableInfo());
		tableInfos.add(new TableInfo());
		tableInfos.add(new TableInfo());

		CommandLineServiceResultBo beforeResult = new CommandLineServiceResultBo();
		beforeResult = new CommandLineServiceResultBo(beforeResult,
				new SelectTableInputServiceFactory());
		beforeResult = new CommandLineServiceResultBo(beforeResult, tableInfos);

		return beforeResult;
	}

}
