package com.test.system.servlet.shutdown.impl;


import com.test.system.servlet.shutdown.ServletShutDown;
import com.test.system.spring.SpringBeanFactoryUtils;


public class QuertzShutDown implements ServletShutDown {

	/* (non-Javadoc)
	 * @see com.test.system.servlet.shutdown.ServletShutDown#shutDown()
	 */
	@Override
	public void shutDown() {
		// TODO Auto-generated method stub


		//手动关闭Quertz
		org.quartz.impl.StdScheduler stdScheduler = (org.quartz.impl.StdScheduler) SpringBeanFactoryUtils.getBean("startQuertz");
		if( stdScheduler != null ){

				stdScheduler.shutdown();
				System.out.println("..... stop .....  Quertz !!!!");

		}

	}

}
