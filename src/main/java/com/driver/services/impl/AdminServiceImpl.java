package com.driver.services.impl;

import java.util.List;

import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Admin;
import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.AdminRepository;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository1;

	@Autowired
	DriverRepository driverRepository1;

	@Autowired
	CustomerRepository customerRepository1;

	@Override
	public void adminRegister(Admin admin) {
		adminRepository1.save(admin);
		//Save the admin in the database
	}

	@Override
	public Admin updatePassword(Integer adminId, String password) {
		/*
		Student originalStudent = studentRepositories.findById(newStudent.getId()).get();
        originalStudent.setMobNo(newStudent.getMobNo());
        studentRepositories.save(originalStudent); */

		Admin originalAdmin = adminRepository1.findById(adminId).get();
		originalAdmin.setPassword(password);
		adminRepository1.save(originalAdmin);
		return originalAdmin;
		//Update the password of admin with given id

	}

	@Override
	public void deleteAdmin(int adminId){
		Admin A = adminRepository1.findById(adminId).get();
		adminRepository1.delete(A);

		// Delete admin without using deleteById function

	}

	@Override
	public List<Driver> getListOfDrivers() {
		return  driverRepository1.findAll();
		//Find the list of all drivers

	}

	@Override
	public List<Customer> getListOfCustomers() {
		return customerRepository1.findAll();
		//Find the list of all customers

	}

}
