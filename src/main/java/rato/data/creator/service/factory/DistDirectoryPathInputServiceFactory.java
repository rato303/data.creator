package rato.data.creator.service.factory;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import rato.data.creator.bo.ConfigurationBo;
import rato.data.creator.service.setting.DistDirectoryPathInputService;

/**
 * {@link DistDirectoryPathInputService}のファクトリです。
 * @author toshiya
 *
 */
public class DistDirectoryPathInputServiceFactory implements
		CommandLineServiceFactory<DistDirectoryPathInputService> {

	/** アプリケーションの設定情報 */
	private ConfigurationBo configurationBo;

	/**
	 * コンストラクタ
	 */
	public DistDirectoryPathInputServiceFactory(ConfigurationBo configurationBo) {
		super();
		this.configurationBo = configurationBo;
	}

	@Override
	public DistDirectoryPathInputService create() {
		return new DistDirectoryPathInputService(this.configurationBo);
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
