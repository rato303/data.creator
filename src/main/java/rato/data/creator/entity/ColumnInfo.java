package rato.data.creator.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;

import rato.data.creator.domain.CharColDeclLength;
import rato.data.creator.domain.ColumnName;
import rato.data.creator.domain.DataLength;
import rato.data.creator.domain.DataPrecision;
import rato.data.creator.domain.DataScale;
import rato.data.creator.domain.DataType;
import rato.data.creator.domain.Nullable;

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
	public ColumnName columnName;

	/** データ型 */
	@Column(name = "DATA_TYPE")
	public DataType dataType;

	/** データ長 */
	@Column(name = "DATA_LENGTH")
	public DataLength dataLength;

	@Column(name = "CHAR_COL_DECL_LENGTH")
	public CharColDeclLength charColDeclLength;

	/** 精度 */
	@Column(name = "DATA_PRECISION")
	public DataPrecision dataPrecision;

	/** 小数点以下の桁数 */
	@Column(name = "DATA_SCALE")
	public DataScale dataScale;

	/** 列にNULLを指定できるかどうか */
	@Column(name = "NULLABLE")
	public Nullable nullable;

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
