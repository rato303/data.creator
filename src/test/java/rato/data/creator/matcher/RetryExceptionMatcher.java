package rato.data.creator.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.exception.RetryException;

public class RetryExceptionMatcher extends TypeSafeMatcher<RetryException> {

    private CommandLineServiceResultBo expectedCommandLineServiceResultBo;

    public RetryExceptionMatcher(CommandLineServiceResultBo expectedCommandLineServiceResultBo) {
        super();
        this.expectedCommandLineServiceResultBo = expectedCommandLineServiceResultBo;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("matched CommandLineServiceResultBo \"" + expectedCommandLineServiceResultBo + "\"");
    }

    @Override
    protected boolean matchesSafely(RetryException item) {
        if (item == null) {
            return false;
        }

        return item.getCommandLineServiceResultBo().equals(this.expectedCommandLineServiceResultBo);
    }

}
