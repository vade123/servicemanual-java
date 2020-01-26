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
	
	@GetMapping("/maintenancetasks/all")
	Iterable<MaintenanceTask> all() {
		List<Sort.Order> rules = new ArrayList<>();
		rules.add(new Sort.Order(Sort.Direction.ASC, "criticality"));
		rules.add(new Sort.Order(Sort.Direction.DESC, "date"));;
		return repository.findAll(Sort.by(rules));
	}
	
	@GetMapping("/maintenancetasks/{id}")
	MaintenanceTask one(@PathVariable Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new MaintenanceTaskNotFoundException(id));
	}
	
	@PostMapping("/maintenancetasks")
	String add(@RequestParam Long deviceId,
			@RequestParam Criticality criticality,
			@RequestParam String desc) {
		FactoryDevice device = deviceRepository.findById(deviceId)
				.orElseThrow(() -> new FactoryDeviceNotFoundException(deviceId));
		
		MaintenanceTask newTask = new MaintenanceTask(device, new Date(), criticality, desc);
		repository.save(newTask);
		return "Created maintenance task for device " + device.getId();
	}
	
	@PutMapping("/maintenancetasks/{id}")
	String update(@PathVariable Long id,
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
		return "Updated maintenance task " + task.getId();
	}
	
	@DeleteMapping("/maintenancetasks/{id}")
	String delete(@PathVariable Long id) {
		if (!repository.existsById(id)) {
			throw new MaintenanceTaskNotFoundException(id);
		}
		repository.deleteById(id);
		return "Deleted maintenance task " + id;
	}
	
	@GetMapping("/maintenancetasks")
	Iterable<MaintenanceTask> findByDevice(@RequestParam Long deviceId) {
		if (!deviceRepository.existsById(deviceId)) {
			throw new FactoryDeviceNotFoundException(deviceId);
		}
		return repository.findByDeviceId(deviceId);
	}
}
