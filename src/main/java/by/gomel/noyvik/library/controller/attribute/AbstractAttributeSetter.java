package by.gomel.noyvik.library.controller.attribute;

import by.gomel.noyvik.library.service.provider.ProviderService;

public abstract class AbstractAttributeSetter implements AttributeSetter {

    ProviderService PROVIDER_SERVICE = ProviderService.getInstance();
}
