package rato.data.creator.rules;

import java.util.regex.Matcher;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class TestFixtureResource implements TestRule {

	/** テストフィクスチャのリソースパス */
	private String testFixtureResourcePath;

	private final String fileSeparator = System.getProperty("file.separator");

	@Override
	public Statement apply(Statement base, Description description) {
		return this.statement(base, description);
	}

	private Statement statement(final Statement base,
			final Description description) {

		Class<?> clazz = description.getTestClass();
		String classLoadCurrentPath = clazz.getClassLoader().getResource(".")
				.getPath();

		String packagePath = clazz
				.getPackage()
				.getName()
				.replaceAll("\\.", Matcher.quoteReplacement(this.fileSeparator));

		this.testFixtureResourcePath = classLoadCurrentPath + "fixture" + this.fileSeparator +  packagePath
				+ this.fileSeparator + clazz.getSimpleName()
				+ this.fileSeparator + description.getMethodName();

		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				base.evaluate();
			}
		};

	}

	public String getTestFixtureResource(String fileName) {
		String trimFileName = fileName.replaceFirst("^\\+", fileName)
				.replaceFirst("^/+", fileName);
		return this.testFixtureResourcePath + this.fileSeparator + trimFileName;
	}

}
