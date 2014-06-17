package rato.data.creator.service.factory;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import rato.data.creator.service.setting.DistDirectoryPathInputService;

/**
 * {@link DistDirectoryPathInputService}のファクトリです。
 * @author toshiya
 *
 */
public class DistDirectoryPathInputServiceFactory implements
		CommandLineServiceFactory<DistDirectoryPathInputService> {

	/**
	 * コンストラクタ
	 */
	public DistDirectoryPathInputServiceFactory() {
		super();
	}

	@Override
	public DistDirectoryPathInputService create() {
		return new DistDirectoryPathInputService();
	}

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object paramObject) {
        return EqualsBuilder.reflectionEquals(this, paramObject);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
