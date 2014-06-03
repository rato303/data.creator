package rato.data.creator.service.factory;

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

}
