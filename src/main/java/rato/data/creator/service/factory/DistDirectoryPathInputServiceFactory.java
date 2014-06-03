package rato.data.creator.service.factory;

import rato.data.creator.service.DistDirectoryPathInputService;

public class DistDirectoryPathInputServiceFactory implements CommandLineServiceFactory<DistDirectoryPathInputService> {

    @Override
    public DistDirectoryPathInputService create() {
        return new DistDirectoryPathInputService();
    }

}
