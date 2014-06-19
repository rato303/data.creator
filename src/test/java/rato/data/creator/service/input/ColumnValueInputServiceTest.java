package rato.data.creator.service.input;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.InputValue;
import rato.data.creator.domain.ColumnName;
import rato.data.creator.domain.DataLength;
import rato.data.creator.domain.Nullable;
import rato.data.creator.entity.ColumnInfo;
import rato.data.creator.exception.RetryException;
import rato.data.creator.service.factory.ColumnValueInputServiceFactory;

@RunWith(Enclosed.class)
public class ColumnValueInputServiceTest {

	@RunWith(JUnit4.class)
	public static class 入力対象のカラムが文字列型で入力値が不正な場合のテスト {

		private ColumnValueInputService service;

		private CommandLineServiceResultBo beforeResult;

		@Rule
		public ExpectedException thrown = ExpectedException.none();

		@Before
		public void setUp() {
			this.service = new ColumnValueInputService();

			List<ColumnInfo> columnInfos = new ArrayList<ColumnInfo>(1);

			ColumnInfo columnInfo;
			columnInfo = new ColumnInfo();
			columnInfo.columnName = new ColumnName("TEST_COL");
			columnInfo.dataLength = new DataLength(10);
			columnInfo.dataPrecision = null;
			columnInfo.dataScale = null;
			columnInfo.nullable = Nullable.N;
			columnInfos.add(columnInfo);

			this.beforeResult = CommandLineServiceResultBo.create()
					.setFactory(new ColumnValueInputServiceFactory())
					.setColumnsInfos(columnInfos);
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

	}

}
