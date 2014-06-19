package rato.data.creator.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(Enclosed.class)
public class NullableTest {

	@RunWith(Theories.class)
	public static class 定義内の値を設定した場合のテスト {

		@DataPoints
		public static Fixture[] FIXTURES = { new Fixture("Y", Nullable.Y),
				new Fixture("N", Nullable.N) };

		@Theory
		public void testOf定義内の値を設定した場合インスタンスが生成される事(Fixture fixture) throws Exception {
			// SetUp
			String inputValue = fixture.getInputValue();
			Nullable expected = fixture.getExpected();

			// Exercice
			Nullable actual = Nullable.of(inputValue);

			// Verify
			assertThat(actual, is(expected));
		}

		static class Fixture {

			private final String inputValue;

			private final Nullable expected;

			public Fixture(String inputValue, Nullable expected) {
				this.inputValue = inputValue;
				this.expected = expected;
			}

			public String getInputValue() {
				return this.inputValue;
			}

			public Nullable getExpected() {
				return this.expected;
			}

		}

	}

	@RunWith(Theories.class)
	public static class 定義外の値を設定した場合のテスト {

		@Rule
		public ExpectedException thrown = ExpectedException.none();

		@DataPoints
		public static String[] INPUT_VALUES = { "X", "Z", "M", "O", "-1", "0",
				"1" };

		@Theory
		public void testOf_定義外の値を設定した場合例外が発行される事(String value) throws Exception {
			// SetUp
			thrown.expect(IllegalArgumentException.class);

			// Exercice
			Nullable.of(value);

			// Verify
		}

	}

	@RunWith(JUnit4.class)
	public static class 必須入力判定のテスト {

		@Test
		public void testIsRequiredNull許可の場合falseになる事() throws Exception {
			// SetUp

			// Exercice

			// Verify
			assertThat(Nullable.Y.isRequired(), is(false));
		}

		@Test
		public void testIsRequiredNull不許可の場合trueになる事() throws Exception {
			// SetUp

			// Exercice

			// Verify
			assertThat(Nullable.N.isRequired(), is(true));
		}

	}

}
