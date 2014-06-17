package rato.data.creator.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

/**
 * <p>
 * カラム情報
 * </p>
 *
 * @author toshiya
 *
 */
@Entity
public class ColumnInfo {

	/** カラム名 */
	@Column(name = "COLUMN_NAME")
	public String columnName;

	/** データ型 */
	@Column(name = "DATA_TYPE")
	public String dataType;

	/** データ長 */
	@Column(name = "DATA_LENGTH")
	public String dataLength;

	/** 精度 */
	@Column(name = "DATA_PRECISION")
	public String dataPrecision;

	/** 小数点以下の桁数 */
	@Column(name = "DATA_SCALE")
	public String dataScale;

	/** 列にNULLを指定できるかどうか */
	@Column(name = "NULLABLE")
	public String nullable;

}
