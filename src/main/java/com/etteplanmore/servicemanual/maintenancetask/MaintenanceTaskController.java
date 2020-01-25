package com.etteplanmore.servicemanual.maintenancetask;

import org.springframework.web.bind.annotation.RestController;

import com.etteplanmore.servicemanual.factorydevice.FactoryDevice;
import com.etteplanmore.servicemanual.factorydevice.FactoryDeviceNotFoundException;
import com.etteplanmore.servicemanual.factorydevice.FactoryDeviceRepository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MaintenanceTaskController {
	
	@Autowired
	private MaintenanceTaskRepository repository;
	
	@Autowired
	private FactoryDeviceRepository deviceRepository;
	
	@GetMapping("/maintenancetasks")
	Iterable<MaintenanceTask> all() {
		return repository.findAll();
	}
	
	@GetMapping("/maintenancetasks/{id}")
	MaintenanceTask one(@PathVariable Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new MaintenanceTaskNotFoundException(id));
	}
	
	@PostMapping("/maintenancetasks")
	String addNewMaintenanceTask(@RequestParam Long deviceId, @RequestParam Criticality criticality, @RequestParam String desc) {
		FactoryDevice device = deviceRepository.findById(deviceId).orElseThrow(() -> new FactoryDeviceNotFoundException(deviceId));
		
		MaintenanceTask newTask = new MaintenanceTask(device, new Date(), criticality, desc);
		repository.save(newTask);
		return "Created";
		
	}
	
}
