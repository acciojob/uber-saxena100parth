package com.driver.services.impl;

import com.driver.model.Cab;
import com.driver.repository.CabRepository;
import com.driver.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Driver;
import com.driver.repository.DriverRepository;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	DriverRepository driverRepository3;

	@Autowired
	CabRepository cabRepository3;

	@Override
	public void register(String mobile, String password){
		Cab newCab =new Cab();
		newCab.setPerKmRate(10);
		newCab.setAvailable(true);

		Driver newDriver = new Driver();
		newDriver.setCab(newCab);
		newDriver.setMobile(mobile);
		newDriver.setPassword(password);

		newCab.setDriver(newDriver);

		driverRepository3.save(newDriver);




		//Save a driver in the database having given details and a cab with ratePerKm as 10 and availability as True by default.

	}

	@Override
	public void removeDriver(int driverId){

		driverRepository3.deleteById(driverId);
		// Delete driver without using deleteById function

	}

	@Override
	public void updateStatus(int driverId){

		Cab originalCab = driverRepository3.findById(driverId).get().getCab();
		originalCab.setAvailable(false);
		//Set the status of respective car to unavailable

	}
}
