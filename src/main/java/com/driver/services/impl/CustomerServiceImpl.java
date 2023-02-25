package com.driver.services.impl;

import com.driver.model.TripBooking;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;
import com.driver.model.TripStatus;

import java.util.*;

import static com.driver.model.TripStatus.*;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		customerRepository2.save(customer);
		//Save the customer in database
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		customerRepository2.deleteById(customerId);
		// Delete customer without using deleteById function

	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		List<Driver> allDrivers = driverRepository2.findAll();
		Collections.sort(allDrivers, Comparator.comparingInt(Driver::getDriverId));
		boolean free = false;
		Driver D1;
		for(Driver D : allDrivers){

			if(D.getCab().isAvailable()){
				free = true;
				D1 = D;
				break;
			}
		}
		if(free==false) throw new Exception("No cab available!");

		TripBooking newTripBooking = new TripBooking();
		newTripBooking.setFromLocation(fromLocation);
		newTripBooking.setToLocation(toLocation);
		newTripBooking.setDistanceInKm(distanceInKm);
		newTripBooking.setStatus(CONFIRMED);


		 customerRepository2.getOne(customerId).getTripBookingList().add(newTripBooking) ;

		 return  newTripBooking;


		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query

	}

	@Override
	public void cancelTrip(Integer tripId){
		TripBooking t4 =tripBookingRepository2.getOne(tripId);
		t4.setStatus(CANCELED);

		//Cancel the trip having given trip Id and update TripBooking attributes accordingly

	}

	@Override
	public void completeTrip(Integer tripId){
		TripBooking t5 =tripBookingRepository2.getOne(tripId);
		t5.setStatus(COMPLETED);
		//Complete the trip having given trip Id and update TripBooking attributes accordingly

	}
}
