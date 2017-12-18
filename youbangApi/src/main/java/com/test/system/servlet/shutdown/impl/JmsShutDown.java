//package com.test.system.servlet.shutdown.impl;
//
//import com.test.system.servlet.shutdown.ServletShutDown;
//import com.test.system.spring.SpringBeanFactoryUtils;
//import org.apache.activemq.pool.PooledConnectionFactory;
//
///**
//* description:
//*
//*/
//public class JmsShutDown implements ServletShutDown {
//
//	/* (non-Javadoc)
//	 * @see com.sdc.system.servlet.shutdown.ServletShutDown#shutDown()
//	 */
//	@Override
//	public void shutDown() {
//		// TODO Auto-generated method stub
//
//
//		//手动关闭activeMq连接
//		PooledConnectionFactory pooledConnectionFactory = (PooledConnectionFactory) SpringBeanFactoryUtils.getBean("pooledConnectionFactory");
//		if( pooledConnectionFactory != null ){
//
//			pooledConnectionFactory.clear();
//			pooledConnectionFactory.stop();
//			System.out.println("..... stop .....  ActiveMqPool !!!!");
//
//		}
//
//	}
//
//}
