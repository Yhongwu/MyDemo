package com.howard.quartz.hello;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job{
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println(context.getJobDetail().getKey()+" "+ new Date());
	}

}
