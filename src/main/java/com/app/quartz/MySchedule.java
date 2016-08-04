package com.app.quartz;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MySchedule extends QuartzJobBean{

	 public void printSomething(){
	    //内容就是打印一句话
	    System.out.println("this is a test schedule at " + new Date());
	  }

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		this.printSomething();
	}
	
}
