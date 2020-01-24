package com.etteplanmore.servicemanual.maintenancetask;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etteplanmore.servicemanual.maintenancetask.MaintenanceTask;

public interface MaintenanceTaskRepository extends JpaRepository<MaintenanceTask, Long>  {

}
