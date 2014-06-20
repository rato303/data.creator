package rato.data.creator.validation;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class NumberCheckUtilTest {

	@RunWith(Theories.class)
	public static class IsDecimal小数の場合のテスト {

		@DataPoints
		public static final String[] INPUT_VALUES = { "1.0", "10.0", "10.1",
				"10.01", "10.10", "100.0", "0.100", "0.0", "0.00" };

		@Theory
		public void trueになる事(String inputValue) throws Exception {
			// SetUp
			boolean expected = true;

			// Exercice
			boolean actual = NumberCheckUtil.isDecimal(inputValue);

			// Verify
			assertThat(actual, is(expected));
		}
	}

	@RunWith(Theories.class)
	public static class IsDecimal小数以外の場合のテスト {

		@DataPoints
		public static final String[] INPUT_VALUES = { "a", "1a", "1あ", "あ1",
				"a1", "1.1.1", "1,000", "ux001" };

		@Theory
		public void falseになる事(String inputValue) throws Exception {
			// SetUp
			boolean expected = false;

			// Exercice
			boolean actual = NumberCheckUtil.isDecimal(inputValue);

			// Verify
			assertThat(actual, is(expected));
		}

	}

}
