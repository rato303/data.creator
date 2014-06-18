package rato.data.creator.service.factory;

import rato.data.creator.service.input.ColumnValueInputService;

public class ColumnValueInputServiceFactory implements
		CommandLineServiceFactory<ColumnValueInputService> {

	@Override
	public ColumnValueInputService create() {
		return new ColumnValueInputService();
	}

}
