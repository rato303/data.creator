package rato.data.creator.service.input;

import static java.text.MessageFormat.format;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.beans.SamePropertyValuesAs;
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
import rato.data.creator.domain.DataLength;
import rato.data.creator.domain.DataType;
import rato.data.creator.domain.Nullable;
import rato.data.creator.entity.ColumnInfo;
import rato.data.creator.exception.RetryException;
import rato.data.creator.service.factory.ColumnValueInputServiceFactory;

@RunWith(Enclosed.class)
public class ColumnValueInputServiceTest {

	@RunWith(JUnit4.class)
	public static class 入力対象のカラムが必須入力の場合のテスト {

		private ColumnValueInputService service;

		private CommandLineServiceResultBo beforeResult;

		@Rule
		public ExpectedException thrown = ExpectedException.none();

		@Before
		public void setUp() {
			this.service = new ColumnValueInputService();
			this.beforeResult = create();
		}

		@Test
		public void testValidateProcess未入力の場合継続可能例外が発行される事() throws Exception {
			// SetUp
			this.thrown.expect(RetryException.class);
			this.thrown.expectMessage("必須入力のカラムです。\n値を入力してください。");

			// Exercice
			this.service.validateProcess(this.beforeResult, new InputValue());

			// Verify
		}

		@Test
		public void testExecute未入力の場合再実行用の結果が取得できる事() throws Exception {
			// SetUp
			CommandLineServiceResultBo expected = this.beforeResult;

			// Exercice
			CommandLineServiceResultBo actual = this.service.execute(
					this.beforeResult, new InputValue());

			// Verify
			assertThat(actual, is(expected));
		}

		@Test
		public void testExecute入力値がある場合読み込み中のカラムのインデックスが1つ増加している事()
				throws Exception {
			// SetUp
			CommandLineServiceResultBo expected = create().addColumnIndex();

			// Exercice
			CommandLineServiceResultBo actual = this.service.execute(
					this.beforeResult, new InputValue("a"));

			// Verify
			assertThat(actual,
					is(SamePropertyValuesAs.samePropertyValuesAs(expected)));
		}

	}

	@RunWith(Theories.class)
	public static class 入力対象のカラムが文字列型で入力文字列が許容範囲外の場合のテスト {

		@DataPoints
		public static final Fixture[] FIXTURES = { new Fixture("abcd", 4),
				new Fixture("あいうえ", 4) };

		@Rule
		public ExpectedException thrown = ExpectedException.none();

		private ColumnValueInputService service;

		private CommandLineServiceResultBo beforeResult;

		@Before
		public void setUp() {
			this.service = new ColumnValueInputService();
			this.beforeResult = create();
		}

		@Theory
		public void testValidateProcess継続可能例外が発行される事(Fixture fixture)
				throws Exception {
			// SetUp
			thrown.expect(RetryException.class);
			thrown.expectMessage(format("文字列の長さを超えています。\n入力された長さ{0}",
					fixture.codePointLength));

			// Exercice
			this.service.validateProcess(this.beforeResult, new InputValue(
					fixture.inputValue));

			// Verify
		}

		@Theory
		public void testExecute再実行用の結果が取得できる事(Fixture fixture) throws Exception {
			// SetUp
			CommandLineServiceResultBo expected = this.beforeResult;

			// Exercice
			CommandLineServiceResultBo actual = this.service.execute(
					this.beforeResult, new InputValue(fixture.inputValue));

			// Verify
			assertThat(actual, is(expected));
		}

		static class Fixture {

			private final String inputValue;

			private final int codePointLength;

			public Fixture(String inputValue, int codePointLength) {
				this.inputValue = inputValue;
				this.codePointLength = codePointLength;
			}

			public String getInputValue() {
				return this.inputValue;
			}

			public int getCodePointLength() {
				return this.codePointLength;
			}

		}

	}

	/**
	 * <p>
	 * テスト前の事前条件を作成します。
	 * </p>
	 *
	 * @return 事前条件
	 */
	public static CommandLineServiceResultBo create() {
		List<ColumnInfo> columnInfos = new ArrayList<ColumnInfo>(1);

		ColumnInfo columnInfo;
		columnInfo = new ColumnInfo();
		columnInfo.nullable = Nullable.N;
		columnInfo.dataType = new DataType("NCHAR");
		columnInfo.dataLength = new DataLength(3);
		columnInfos.add(columnInfo);

		return CommandLineServiceResultBo.create()
				.setFactory(new ColumnValueInputServiceFactory())
				.setColumnsInfos(columnInfos);
	}

}
