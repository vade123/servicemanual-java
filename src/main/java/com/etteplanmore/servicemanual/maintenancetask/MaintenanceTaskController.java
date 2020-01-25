package com.etteplanmore.servicemanual.maintenancetask;

import org.springframework.web.bind.annotation.RestController;

import com.etteplanmore.servicemanual.factorydevice.FactoryDevice;
import com.etteplanmore.servicemanual.factorydevice.FactoryDeviceNotFoundException;
import com.etteplanmore.servicemanual.factorydevice.FactoryDeviceRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MaintenanceTaskController {
	
	@Autowired
	private MaintenanceTaskRepository repository;
	
	@Autowired
	private FactoryDeviceRepository deviceRepository;
	
	private List<Sort.Order> getSortingRules() {
		List<Sort.Order> rules = new ArrayList<>();
		rules.add(new Sort.Order(Sort.Direction.ASC, "criticality"));
		rules.add(new Sort.Order(Sort.Direction.DESC, "date"));
		return rules;
	}
	
	@GetMapping("/maintenancetasks")
	Iterable<MaintenanceTask> all() {
		List<Sort.Order> rules = getSortingRules();
		return repository.findAll(Sort.by(rules));
	}
	
	@GetMapping("/maintenancetasks/{id}")
	MaintenanceTask one(@PathVariable Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new MaintenanceTaskNotFoundException(id));
	}
	
	@PostMapping("/maintenancetasks")
	String addNewMaintenanceTask(@RequestParam Long deviceId, @RequestParam Criticality criticality, @RequestParam String desc) {
		FactoryDevice device = deviceRepository.findById(deviceId)
				.orElseThrow(() -> new FactoryDeviceNotFoundException(deviceId));
		
		MaintenanceTask newTask = new MaintenanceTask(device, new Date(), criticality, desc);
		repository.save(newTask);
		return "Created";
	}
	
	@PutMapping("/maintenancetasks/{id}")
	String updateMaintenanceTask(@PathVariable Long id,
			@RequestParam(required=false) Criticality criticality,
			@RequestParam(required=false) String desc,
			@RequestParam(required=false) Boolean completed) {
		
		MaintenanceTask task = repository.findById(id)
				.orElseThrow(() -> new MaintenanceTaskNotFoundException(id));
		
		if (criticality != null) {
			task.setCriticality(criticality);
		}
		if (desc != null) {
			task.setDescription(desc);
		}
		if (completed != null) {
			task.setCompeleted(completed);
		}
		repository.save(task);
		
		return "Updated";
	}
	
	@DeleteMapping("/maintenancetasks/{id}")
	String deleteMaintenanceTask(@PathVariable Long id) {
		if (!repository.existsById(id)) {
			throw new MaintenanceTaskNotFoundException(id);
		}
		repository.deleteById(id);
		return "Deleted";
	}
}
