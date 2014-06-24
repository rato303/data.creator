package rato.data.creator.bo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
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
public class InputValueTest {

	@RunWith(JUnit4.class)
	public static class コンストラクタのテスト {

		private InputValue actual;

		@Test
		public void testInputValue引数無しで呼び出した場合値がnullであること() throws Exception {
			// SetUp

			// Exercice
			this.actual = new InputValue();

			// Verify
			assertThat(this.actual.getValue(), is(nullValue()));
		}

	}

	@RunWith(Theories.class)
	public static class 値取得のテスト {

		@DataPoints
		public static final String[] INPUT_VALUES = { "a", "1", "あ" };

		private InputValue inputValue;

		@Theory
		public void testGetValueコンストラクタに渡した引数の値がGetValueで取得できる値と同じである事(
				String inputValue) throws Exception {
			// SetUp
			String expected = inputValue;
			this.inputValue = new InputValue(inputValue);

			// Exercice
			String actual = this.inputValue.getValue();

			// Verify
			assertThat(actual, is(expected));
		}

	}

	@RunWith(Theories.class)
	public static class 値設定のテスト {

		@DataPoints
		public static final String[] INPUT_VALUES = { "a", "1", "あ" };

		private InputValue inputValue;

		@Theory
		public void testSetValue設定した値がGetValueで取得できる値と同じである事(String inputValue)
				throws Exception {
			// SetUp
			String expected = inputValue;
			this.inputValue = new InputValue();

			// Exercice
			this.inputValue.setValue(inputValue);
			String actual = this.inputValue.getValue();

			// Verify
			assertThat(actual, is(expected));
		}

	}

	@RunWith(Theories.class)
	public static class 数値型に変換して値を取得するテスト {

		@DataPoints
		public static final String[] INPUT_VALUES = {
				String.valueOf(Integer.MIN_VALUE), "-1", "0", "1",
				String.valueOf(Integer.MAX_VALUE) };

		private InputValue inputValue;

		@Theory
		public void testGetIntegerValue数値型に変換した値が取得できる事(String inputValue)
				throws Exception {
			// SetUp
			this.inputValue = new InputValue(inputValue);
			Integer expected = Integer.parseInt(inputValue);

			// Exercice
			Integer actual = this.inputValue.getIntegerValue();

			// Verify
			assertThat(actual, is(expected));
		}

	}

	@RunWith(Theories.class)
	public static class 数値以外の値を設定して数値型の値を取得するテスト {

		@DataPoints
		public static final String[] INPUT_VALUES = { "あ", "a", "あいう", "abc",
				"1a", "a1", "1a1", "a1a", "あ1", "1あ", "あ1あ", "1あ1" };

		@Rule
		public ExpectedException thrown = ExpectedException.none();

		private InputValue inputValue;

		@Theory
		public void testGetIntegerValueNumberFormatExceptionが発行される事(
				String inputValue) {
			// SetUp
			this.inputValue = new InputValue(inputValue);

			thrown.expect(NumberFormatException.class);

			// Exercice
			this.inputValue.getIntegerValue();

			// Verify
		}

	}

	@RunWith(Theories.class)
	public static class 値を大文字に変換して値を取得するテスト {

		@DataPoints
		public static final Fixture[] FIXTURES = { new Fixture("a", "A"),
				new Fixture("あ", "あ"), new Fixture("a1a", "A1A"),
				new Fixture("111", "111") };

		private InputValue inputValue;

		@Theory
		public void testGetUpperValue(Fixture fixture) throws Exception {
			// SetUp
			String expected = fixture.expected;
			this.inputValue = new InputValue(fixture.inputValue);

			// Exercice
			String actual = this.inputValue.getUpperValue();

			// Verify
			assertThat(actual, is(expected));
		}

		static class Fixture {

			private final String inputValue;

			private final String expected;

			private Fixture(String inputValue, String expected) {
				this.inputValue = inputValue;
				this.expected = expected;
			}

		}

	}

	@RunWith(Theories.class)
	public static class 値を保持している場合のテスト {

		@DataPoints
		public static final String[] INPUT_VALUES = {"", null};

		private InputValue inputValue;

		@Theory
		public void testIsEmpty値を保持している場合trueになる事(String inputValue) throws Exception {
			// SetUp
			this.inputValue = new InputValue(inputValue);

			// Exercice

			// Verify
			assertThat(this.inputValue.isEmpty(), is(true));
		}

		@Theory
		public void testIsNotEmpty値を保持している場合falseになる事(String inputValue) throws Exception {
			// SetUp
			this.inputValue = new InputValue(inputValue);

			// Exercice

			// Verify
			assertThat(this.inputValue.isNotEmpty(), is(false));
		}

	}

	@RunWith(Theories.class)
	public static class 値を保持していない場合のテスト {

		@DataPoints
		public static final String[] INPUT_VALUES = {"a", "あ", "1"};

		private InputValue inputValue;

		@Theory
		public void testIsEmpty値を保持していない場合falseになる事(String inputValue) throws Exception {
			// SetUp
			this.inputValue = new InputValue(inputValue);

			// Exercice

			// Verify
			assertThat(this.inputValue.isEmpty(), is(false));
		}

		@Theory
		public void testIsNotEmpty値を保持していない場合trueになる事(String inputValue) throws Exception {
			// SetUp
			this.inputValue = new InputValue(inputValue);

			// Exercice

			// Verify
			assertThat(this.inputValue.isNotEmpty(), is(true));
		}

	}

}
