package rato.data.creator.exception;

import java.util.ResourceBundle;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.service.CommandLineService;
import rato.data.creator.service.factory.CommandLineServiceFactory;

/**
 * <p>
 * {@link CommandLineService}内で入力例外が発生した場合にアプリケーションを継続する際にthrowされる例外です。
 * </p>
 *
 * @author toshiya
 */
public class RetryException extends RuntimeException {

	/** シリアルID */
	private static final long serialVersionUID = -7087940234374752034L;

	/** 入力例外発生時に出力するメッセージ */
	private String message;

	/** 入力例外発生後に呼び出される{@link CommandLineService}を生成するファクトリ */
	private CommandLineServiceResultBo commandLineServiceResultBo;

	/**
	 * {@link RetryException}を生成します。
	 *
	 * @param messageKey
	 *            入力例外発生時に出力するメッセージのメッセージキー
	 *
	 * @param factory
	 *            入力例外発生後に呼び出される{@link CommandLineService}を生成するファクトリ
	 *
	 */
	public RetryException(String messageKey,
			CommandLineServiceFactory<?> factory) {
		this(messageKey, "message", factory);
	}

	/**
	 * {@link RetryException}を生成します。
	 *
	 * @param messageKey
	 *            入力例外発生時に出力するメッセージのメッセージキー
	 *
	 * @param paramString
	 *            {@link ResourceBundle#getBundle(String)}に設定する値
	 *
	 * @param factory
	 *            入力例外発生後に呼び出される{@link CommandLineService}を生成するファクトリ
	 *
	 */
	public RetryException(String messageKey, String paramString,
			CommandLineServiceFactory<?> factory) {
		super();
		this.commandLineServiceResultBo = new CommandLineServiceResultBo(
				new CommandLineServiceResultBo(), factory);
		this.message = ResourceBundle.getBundle(paramString).getString(
				messageKey);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return this.message;
	}

	/**
	 * 入力例外発生後に呼び出される{@link CommandLineService}を生成するファクトリを取得します。
	 *
	 * @return 入力例外発生後に呼び出される{@link CommandLineService}を生成するファクトリ
	 */
	public CommandLineServiceResultBo getCommandLineServiceResultBo() {
		return commandLineServiceResultBo;
	}

}
