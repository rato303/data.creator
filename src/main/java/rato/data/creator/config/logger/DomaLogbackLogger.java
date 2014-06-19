package rato.data.creator.config.logger;

import java.sql.SQLException;

import org.seasar.doma.jdbc.JdbcLogger;
import org.seasar.doma.jdbc.Sql;
import org.seasar.doma.jdbc.SqlExecutionSkipCause;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DomaLogbackLogger implements JdbcLogger {

	@Override
	public void logDaoMethodEntering(String callerClassName,
			String callerMethodName, Object... args) {
		Logger logger = LoggerFactory.getLogger(callerClassName);
		logger.debug("START {}#{}", callerClassName, callerMethodName);
	}

	@Override
	public void logDaoMethodExiting(String callerClassName,
			String callerMethodName, Object result) {
		Logger logger = LoggerFactory.getLogger(callerClassName);
		logger.debug("END {}#{}", callerClassName, callerMethodName);
	}

	@Override
	public void logDaoMethodThrowing(String callerClassName,
			String callerMethodName, RuntimeException e) {
		Logger logger = LoggerFactory.getLogger(callerClassName);
		logger.debug("END {}#{} RuntimeException:{}", callerClassName,
				callerMethodName, e);
	}

	@Override
	public void logSqlExecutionSkipping(String callerClassName,
			String callerMethodName, SqlExecutionSkipCause cause) {
		Logger logger = LoggerFactory.getLogger(callerClassName);
		logger.debug("SKIPPED({}) {}#{}", cause.name(), callerClassName,
				callerMethodName);
	}

	@Override
	public void logSql(String callerClassName, String callerMethodName,
			Sql<?> sql) {
		Logger logger = LoggerFactory.getLogger(callerClassName);
		logger.debug("SQL log. sqlFilePath=[{}],\n{}", sql.getSqlFilePath(),
				sql.getFormattedSql());
	}

	@Override
	public void logLocalTransactionBegun(String callerClassName,
			String callerMethodName, String transactionId) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void logLocalTransactionEnded(String callerClassName,
			String callerMethodName, String transactionId) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void logLocalTransactionCommitted(String callerClassName,
			String callerMethodName, String transactionId) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void logLocalTransactionSavepointCreated(String callerClassName,
			String callerMethodName, String transactionId, String savepointName) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void logLocalTransactionSavepointReleased(String callerClassName,
			String callerMethodName, String transactionId, String savepointName) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void logLocalTransactionRolledback(String callerClassName,
			String callerMethodName, String transactionId) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void logLocalTransactionSavepointRolledback(String callerClassName,
			String callerMethodName, String transactionId, String savepointName) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void logLocalTransactionRollbackFailure(String callerClassName,
			String callerMethodName, String transactionId, SQLException e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void logAutoCommitEnablingFailure(String callerClassName,
			String callerMethodName, SQLException e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void logTransactionIsolationSettingFailuer(String callerClassName,
			String callerMethodName, int transactionIsolationLevel,
			SQLException e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void logConnectionClosingFailure(String callerClassName,
			String callerMethodName, SQLException e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void logStatementClosingFailure(String callerClassName,
			String callerMethodName, SQLException e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void logResultSetClosingFailure(String callerClassName,
			String callerMethodName, SQLException e) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
