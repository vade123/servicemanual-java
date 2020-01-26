package com.etteplanmore.servicemanual.factorydevice;

import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

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
    
    @GetMapping("/init")
    String init() {
    	final List<FactoryDevice> devices = Arrays.asList(
                new FactoryDevice("Device X", 2001, "type 10"),
                new FactoryDevice("Device Y", 2012, "type 3"),
                new FactoryDevice("Device Z", 1985, "type 1"),
                new FactoryDevice("Device 0", 2004, "type 19"),
                new FactoryDevice("Device 1", 1987, "type 12"),
                new FactoryDevice("Device 2", 1982, "type 11"),
                new FactoryDevice("Device 3", 1995, "type 5"),
                new FactoryDevice("Device 4", 1990, "type 2"),
                new FactoryDevice("Device 5", 1982, "type 1"),
                new FactoryDevice("Device T", 2001, "type 10")
            );
    	repository.saveAll(devices);
    	return "Some factory devices added";
    }
}