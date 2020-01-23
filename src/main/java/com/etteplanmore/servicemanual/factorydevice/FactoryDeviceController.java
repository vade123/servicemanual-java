package com.etteplanmore.servicemanual.factorydevice;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class FactoryDeviceController {
	
	@Autowired
    private FactoryDeviceRepository repository;

    @GetMapping("/factorydevices")
    Iterable<FactoryDevice> all() {
        return repository.findAll();
    }

    @GetMapping("/factorydevices/{id}")
    FactoryDevice one(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new FactoryDeviceNotFoundException(id));
    }
}