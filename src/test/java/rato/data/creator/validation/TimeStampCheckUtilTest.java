package rato.data.creator.validation;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class TimeStampCheckUtilTest {

	@RunWith(Theories.class)
	public static class CheckFormat日付型に変換できる場合のテスト {

		@DataPoints
		public static final String[] INPUT_VALUES = { "201201010000000",
				"201212312359599", "2012110000", "2012-01-01 00:00:00.0",
				"2012-1-1 0:0:0.0", "2012-12-31 23:59:59.9",
				"2012/01/01 00:00:00.0", "2012/12/31 23:59:59.9",
				"2012/1/1 0:0:0.0" };

		@Theory
		public void trueになる事(String inputValue) throws Exception {
			// SetUp
			boolean expected = true;

			// Exercice
			boolean actual = TimeStampCheckUtil.checkFormat(inputValue);

			// Verify
			assertThat(actual, is(expected));
		}

	}

	@RunWith(Theories.class)
	public static class CheckFormat日付型に変換できない場合のテスト {

		@DataPoints
		public static final String[] INPUT_VALUES = { "2012010100000001",
				"201212312360599", "20121231235959", "20121231", "あ", "a" };

		@Theory
		public void falseになる事(String inputValue) {
			// SetUp
			boolean expected = false;

			// Exercice
			boolean actual = TimeStampCheckUtil.checkFormat(inputValue);

			// Verify
			assertThat(actual, is(expected));
		}

	}

}
