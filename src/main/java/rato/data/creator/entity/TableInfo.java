package rato.data.creator.entity;

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

}
