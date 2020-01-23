package com.etteplanmore.servicemanual.factorydevice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etteplanmore.servicemanual.factorydevice.FactoryDevice;


public interface FactoryDeviceRepository extends JpaRepository<FactoryDevice, Long> {
    
}