package rato.data.creator.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;

/**
 * <p>
 * テーブル情報
 * </p>
 *
 * @author toshiya
 *
 */
@Entity
public class TableInfo {

	/** テーブル名 */
	@Column(name = "TABLE_NAME")
	public String tableName;

	/** テーブルコメント */
	@Column(name = "COMMENTS")
	public String comments;

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
