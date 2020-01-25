package com.etteplanmore.servicemanual.maintenancetask;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class MaintenanceTaskController {
	
	@Autowired
	private MaintenanceTaskRepository repository;
	
	@GetMapping("/maintenancetasks")
	Iterable<MaintenanceTask> all() {
		return repository.findAll();
	}
	
	@GetMapping("/maintenancetasks/{id}")
	MaintenanceTask one(@PathVariable Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new MaintenanceTaskNotFoundException(id));
	}
}
