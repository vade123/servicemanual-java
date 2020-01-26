package com.etteplanmore.servicemanual.maintenancetask;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.etteplanmore.servicemanual.maintenancetask.MaintenanceTask;

public interface MaintenanceTaskRepository extends JpaRepository<MaintenanceTask, Long>  {
	
	@Query("FROM MaintenanceTask t where t.device.id = :deviceId ORDER BY t.criticality ASC, t.date DESC")
	Iterable<MaintenanceTask> findByDeviceId(@Param("deviceId") Long deviceId);
}
