package rato.data.creator.validation;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class StringCheckUtilTest {

	@RunWith(Theories.class)
	public static class ExceedLength文字列の長さが許容範囲内の場合のテスト {

		@DataPoints
		public static final String[] INPUT_VALUES = {"a", "あ", "ab", "あい", "abc", "あいう"};

		@Theory
		public void testExceedLength文字列の長さが許容範囲内の場合falseになる事(String value) throws Exception {
			// SetUp
			boolean expected = false;

			// Exercice
			boolean actual = StringCheckUtil.exceedLength(value, 3);

			// Verify
			assertThat(actual, is(expected));
		}

	}

}
