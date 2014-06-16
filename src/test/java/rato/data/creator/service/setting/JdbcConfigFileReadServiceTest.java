package rato.data.creator.service.setting;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.InputValue;
import rato.data.creator.exception.RetryException;
import rato.data.creator.matcher.RetryExceptionMatcher;
import rato.data.creator.service.factory.JdbcConfigFileReadServiceFactory;

public class JdbcConfigFileReadServiceTest {

    private JdbcConfigFileReadService service;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void test設定ファイルの入力値が未入力の場合() {
        this.thrown.expect(RetryException.class);
        this.thrown.expectMessage("データベース接続定義ファイルのファイルパスは必須です。");
        this.thrown.expect(new RetryExceptionMatcher(new CommandLineServiceResultBo(new JdbcConfigFileReadServiceFactory())));

        this.service = new JdbcConfigFileReadService();
        this.service.validateProcess(new InputValue(""));
    }

    @Test
    public void test設定ファイルが存在しない場合() throws IOException {
        File testDir = folder.newFolder("unknown");
        File testConfFile = new File(testDir.getAbsolutePath() + System.getProperty("file.separator") + "jdbc.properties");

        this.thrown.expect(RetryException.class);
        this.thrown.expectMessage("データベース接続定義ファイルが見つかりませんでした。");
        this.thrown.expect(new RetryExceptionMatcher(new CommandLineServiceResultBo(new JdbcConfigFileReadServiceFactory())));

        this.service = new JdbcConfigFileReadService();
        this.service.validateProcess(new InputValue(testConfFile.getAbsolutePath()));
    }

    @Test
    public void test設定ファイルにJDBCDriverClassNameが設定されていない場合() {
        fail("まだ実装されていません");
    }

    @Test
    public void test設定ファイルにスキーマ名が設定されていない場合() {
        fail("まだ実装されていません");
    }

    @Test
    public void test設定ファイルにユーザー名が設定されていない場合() {
        fail("まだ実装されていません");
    }

    @Test
    public void test設定ファイルにパスワードが設定されていない場合() {
        fail("まだ実装されていません");
    }

    @Test
    public void test設定ファイルに記述されているJDBCDriverClassNameが読み込めない場合() {
        fail("まだ実装されていません");
    }

    @Test
    public void test設定ファイルに記述されている情報でデータベースに接続できない場合() {
        fail("まだ実装されていません");
    }

    @Test
    public void test設定ファイルが正しく記述されていた場合() {
        fail("まだ実装されていません");
    }

}
