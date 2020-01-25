package com.etteplanmore.servicemanual.maintenancetask;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.util.Date;

import com.etteplanmore.servicemanual.factorydevice.FactoryDevice;

@Entity
public class MaintenanceTask {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "deviceId")
	private FactoryDevice device;
	private Date date;
	private Criticality criticality;
	private String description;
	private Boolean compeleted;
	
	protected MaintenanceTask() {} 
	
	public MaintenanceTask(FactoryDevice device, Date date, Criticality criticality, String desc) {
		this.device = device;
		this.date = date;
		this.criticality = criticality;
		this.description = desc;
		this.compeleted = false;
	}
	public Long getId() {
		return this.id;
	}
	public Long getDeviceId() {
		return this.device.getId();
	}
	public Date getDate() {
		return this.date;
	}
	public Criticality getCriticality() {
		return this.criticality;
	}
	public String getDescription() {
		return this.description;
	}
	public Boolean isCompeleted() {
		return this.compeleted;
	}
	public void setCompeleted(Boolean value) {
		this.compeleted = value;
	}
	public void setDescription(String desc) {
		this.description = desc;
	}
	public void setCriticality(Criticality value) {
		this.criticality = value;
	}
};
