package com.test.system.servlet.shutdown;




import com.test.system.servlet.shutdown.impl.QuertzShutDown;
import com.test.system.servlet.shutdown.impl.SqlShutDown;
import com.test.system.servlet.shutdown.impl.ThreadShutDown;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
* description: 线程池热部署手动关闭
*
*/
public class MyServletShutDown implements ServletContextListener{

	 
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
  
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("reload Tomcat .... waiting >>>>  manual close  to prevent memory leak !!!!!!  ---  Today ");

		 //sql
		 this.shutDownSql();
		 //Jms
//		 this.shutDownJms();
		 //Quertz
		 this.shutDownQuertz();
		 //Thread
		 this.shutDownThread();
		
		 System.out.println("reload Tomcat .... over >>>>  Its a happy day ！！！  ---  Today");
	}

	/**
	 * 关闭sql线程池
	 */
	private void shutDownSql(){
		
		new SqlShutDown().shutDown();
		
	}
	
	/**
	 * 关闭未托管线程
	 */
	private void shutDownThread(){
		
		new ThreadShutDown().shutDown();
	}
	
	/**
	 * 关闭计划任务
	 */
	private void shutDownQuertz(){

		new QuertzShutDown().shutDown();

	}
	
//	/**
//	 * 关闭JMS
//	 */
//	private void shutDownJms(){
//
//		new JmsShutDown().shutDown();
//
//	}
}
