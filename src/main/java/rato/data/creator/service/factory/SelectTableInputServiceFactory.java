package rato.data.creator.service.factory;

import rato.data.creator.service.select.SelectTableInputService;

public class SelectTableInputServiceFactory implements
		CommandLineServiceFactory<SelectTableInputService> {

	@Override
	public SelectTableInputService create() {
		return new SelectTableInputService();
	}

}
