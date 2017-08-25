package com.howard.quartz.hello;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;

public class CronTriggerExample {
	public void runJob1() throws SchedulerException, InterruptedException{
		//job1:使用cron表达式设定时间 每3秒执行一次
//		Logger log = LoggerFactory.getLogger(CronTriggerExample.class);
		
		SchedulerFactory factory = new StdSchedulerFactory();
	    Scheduler scheduler = factory.getScheduler();
	    
	    JobDetail job = JobBuilder.newJob(HelloJob.class)
	    		.withIdentity("job_1", "group_1")
	    		.build();
	    
	    CronTrigger trigger = TriggerBuilder.newTrigger()
	    .withIdentity("trigger_1", "group_1")
	    .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?"))
	    .build();
	    
	    Date time = scheduler.scheduleJob(job, trigger);
	    
	    System.out.println(job.getKey() + " 启动时间： " +time +  "使用的Cron表达式： "
	             + trigger.getCronExpression() + " seconds");
//	    log.info(job.getKey() + " 启动时间： " +time +  "使用的Cron表达式： "
//	             + trigger.getCronExpression() + " seconds");
	    
	    AnnualCalendar holidays = new AnnualCalendar();
	    
	    Calendar laborDay = new GregorianCalendar();

	    laborDay.add(Calendar.MONTH,5);//java.util.Calendar;
	    laborDay.add(Calendar.DATE,1);
	    
	    holidays.setDayExcluded(laborDay, true); //排除的日期，如果设置为false则为包含
	    
	    Calendar nationalDay = new GregorianCalendar();

	    nationalDay.add(Calendar.MONTH,10);
	    nationalDay.add(Calendar.DATE,1);

	    holidays.setDayExcluded(nationalDay, true);
	    
	    
	    
	    scheduler.addCalendar("holidays", holidays, false, false);
	    
	    
	    scheduler.start();
	    //job调度是另起一个线程的 主线程睡眠并不影响 但主线程结束支线程也会结束
	    Thread.sleep(10000);
	    scheduler.shutdown(true);
	    
	    SchedulerMetaData metaData = scheduler.getMetaData();
	    System.out.println("执行了： " + metaData.getNumberOfJobsExecuted() + " jobs.");
	}
	/**
	 * 排除指定的日期
	 * @throws SchedulerException
	 * @throws InterruptedException
	 */
	public void runJob2() throws SchedulerException, InterruptedException{
		//job2:使用cron表达式设定时间 每3秒执行一次 排除指定的日期
//		Logger log = LoggerFactory.getLogger(CronTriggerExample.class);
		
		SchedulerFactory factory = new StdSchedulerFactory();
	    Scheduler scheduler = factory.getScheduler();
	    
	    //每年执行的日历 相应的还有MonthlyCalendar等
	    AnnualCalendar holidays = new AnnualCalendar();
	    //劳动节
	    Calendar laborDay = new GregorianCalendar();
	    laborDay.add(Calendar.MONTH,5);//java.util.Calendar;
	    laborDay.add(Calendar.DATE,1);
	    
	    holidays.setDayExcluded(laborDay, true); //排除的日期，如果设置为false则为包含
	    
//	    Calendar nationalDay = new GregorianCalendar();
//
//	    nationalDay.add(Calendar.MONTH,10);
//	    nationalDay.add(Calendar.DATE,1);
//	    holidays.setDayExcluded(nationalDay, true);

	    scheduler.addCalendar("holidays", holidays, false, false);
	    
	    JobDetail job = JobBuilder.newJob(HelloJob.class)
	    		.withIdentity("job_1", "group_1")
	    		.build();
	    
	    CronTrigger trigger = TriggerBuilder.newTrigger()
	    .withIdentity("trigger_1", "group_1")
	    .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?"))
	    .modifiedByCalendar("holidays")//告诉trigger按这个日历规则执行
	    .build();
	    
	    Date time = scheduler.scheduleJob(job, trigger);
	    
	    System.out.println(job.getKey() + " 启动时间： " +time +  "使用的Cron表达式： "
	             + trigger.getCronExpression() + " seconds");
	    
	    scheduler.start();
	    //job调度是另起一个线程的 主线程睡眠并不影响 但主线程结束支线程也会结束
	    Thread.sleep(10000);
	    scheduler.shutdown(true);
	    
	    SchedulerMetaData metaData = scheduler.getMetaData();
	    System.out.println("执行了： " + metaData.getNumberOfJobsExecuted() + " jobs.");
	}
	
	public static void main(String[] args) throws SchedulerException, InterruptedException {
		
		CronTriggerExample example = new CronTriggerExample();
//		example.runJob1();
		example.runJob2();
	}
}
