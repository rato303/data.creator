package rato.data.creator.bo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * <p>
 * メッセージ情報です。
 * </p>
 *
 * @author toshiya
 *
 */
public class MessageBo implements Serializable {

	/** メッセージキー */
	private String messageKey;

	/** メッセージ引数 */
	private Object[] messageArgs;

	/**
	 * <p>
	 * コンストラクタ
	 * </p>
	 *
	 * @param messageKey
	 *            メッセージキー
	 * @param messageArgs
	 *            メッセージ引数
	 */
	private MessageBo(String messageKey, Object... messageArgs) {
		super();
		this.messageKey = messageKey;
		this.messageArgs = messageArgs;
	}

	/**
	 * <p>
	 * メッセージ情報を生成します。
	 * </p>
	 *
	 * @param messageKey
	 *            メッセージキー
	 * @return メッセージ情報
	 */
	public static MessageBo create(String messageKey) {
		Object[] messageArgs = null;
		return create(messageKey, messageArgs);
	}

	/**
	 * <p>
	 * メッセージ引数付きメッセージ情報を生成します。
	 * </p>
	 *
	 * @param messageKey
	 *            メッセージキー
	 * @param messageArgs
	 *            メッセージ引数
	 * @return メッセージ引数付きメッセージ情報
	 */
	public static MessageBo create(String messageKey, Object... messageArgs) {
		return new MessageBo(messageKey, messageArgs);
	}

	/**
	 * <p>
	 * メッセージ引数を保有しているか判定します。
	 * </p>
	 *
	 * @return メッセージ引数を保有している場合は「true」保有していない場合は「false」
	 */
	public boolean isNotNullMessageArgs() {
		return this.messageArgs != null;
	}

	/**
	 * メッセージキーを取得します。
	 *
	 * @return メッセージキー
	 */
	public String getMessageKey() {
		return messageKey;
	}

	/**
	 * メッセージ引数を取得します。
	 *
	 * @return メッセージ引数
	 */
	public Object[] getMessageArgs() {
		return messageArgs;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object paramObject) {
		return EqualsBuilder.reflectionEquals(this, paramObject);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
