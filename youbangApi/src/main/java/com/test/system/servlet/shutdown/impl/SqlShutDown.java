package com.test.system.servlet.shutdown.impl;


import com.test.system.servlet.shutdown.ServletShutDown;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;


public class SqlShutDown implements ServletShutDown {

	/* (non-Javadoc)
	 * @see com.sdc.system.servlet.shutdown.ServletShutDown#shutDown()
	 */
	@Override
	public void shutDown() {
		// TODO Auto-generated method stub

		//去除Mysql Orcal

		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while(drivers.hasMoreElements()){
			Driver driver = drivers.nextElement();
			try {
				DriverManager.deregisterDriver(driver);
				System.out.println("...... unregisterDriver ... " + driver.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  
		
	}

}
