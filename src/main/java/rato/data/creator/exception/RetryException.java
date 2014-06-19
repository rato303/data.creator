package rato.data.creator.exception;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import rato.data.creator.bo.CommandLineServiceResultBo;
import rato.data.creator.bo.MessageBo;
import rato.data.creator.service.CommandLineService;

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

	/** メッセージ情報 */
	private MessageBo messageBo;

	/**
	 * <p>
	 * 継続可能例外を生成します。
	 * </p>
	 *
	 * @param messageBo
	 *            メッセージ情報
	 * @param beforeResult
	 *            継続可能処理結果
	 */
	public RetryException(MessageBo messageBo,
			CommandLineServiceResultBo beforeResult) {
		this(messageBo, "message", beforeResult);
	}

	/**
	 * <p>
	 * 継続可能例外を生成します。
	 * </p>
	 *
	 * @param messageBo
	 *            メッセージ情報
	 * @param messagePrefix
	 *            メッセージ取得先プロパティファイルの接頭辞
	 * @param beforeResult
	 *            継続可能処理結果
	 */
	public RetryException(MessageBo messageBo, String messagePrefix,
			CommandLineServiceResultBo beforeResult) {
		super();
		this.messageBo = messageBo;
		this.commandLineServiceResultBo = beforeResult;

		String messageValue = ResourceBundle.getBundle(messagePrefix)
				.getString(this.messageBo.getMessageKey());

		if (this.messageBo.isNotNullMessageArgs()) {
			this.message = MessageFormat.format(messageValue,
					messageBo.getMessageArgs());
		} else {
			this.message = messageValue;
		}
	}

//	/**
//	 * {@link RetryException}を生成します。
//	 *
//	 * @param messageKey
//	 *            入力例外発生時に出力するメッセージのメッセージキー
//	 *
//	 * @param beforeResult
//	 *            入力例外発生後に呼び出される{@link CommandLineService}を生成するファクトリ
//	 *
//	 */
//	public RetryException(String messageKey,
//			CommandLineServiceResultBo beforeResult) {
//		this(messageKey, "message", beforeResult);
//	}
//
//	/**
//	 * {@link RetryException}を生成します。
//	 *
//	 * @param messageKey
//	 *            入力例外発生時に出力するメッセージのメッセージキー
//	 *
//	 * @param paramString
//	 *            {@link ResourceBundle#getBundle(String)}に設定する値
//	 *
//	 * @param beforeResult
//	 *            入力例外発生後に呼び出される{@link CommandLineService}を生成するファクトリ
//	 *
//	 */
//	public RetryException(String messageKey, String paramString,
//			CommandLineServiceResultBo beforeResult) {
//		super();
//		this.commandLineServiceResultBo = beforeResult;
//		this.message = ResourceBundle.getBundle(paramString).getString(
//				messageKey);
//	}

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
