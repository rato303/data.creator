package rato.data.creator.service.input;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.InputValue;
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
			CommandLineServiceResultBo actual = this.service.execute(this.beforeResult, new InputValue());

			// Verify
			assertThat(actual, is(expected));
		}

		@Test
		public void testExecute入力値がある場合() throws Exception {
			// SetUp
			CommandLineServiceResultBo expected = create().addColumnIndex();

			// Exercice
			CommandLineServiceResultBo actual = this.service.execute(this.beforeResult, new InputValue("hoge"));

			// Verify
			assertThat(actual, is(SamePropertyValuesAs.samePropertyValuesAs(expected)));
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
			columnInfos.add(columnInfo);

			return CommandLineServiceResultBo.create()
					.setFactory(new ColumnValueInputServiceFactory())
					.setColumnsInfos(columnInfos);
		}

	}

}
