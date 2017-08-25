package com.howard.quartz.hello;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleTriggerExample {
	public void RunJob1()throws Exception{
		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		
		//job1：只执行一次
		
		//开始时间   第一个参数为在某个时间之后 第二个为多久后
		//nextGivenSecondDate（Date date, int secondBase)：在date之后的xx秒启动
		//nextGivenMinteDate（Date date, int secondBase)：在date之后的xx分启动
		Date startTime = DateBuilder.nextGivenSecondDate(null, 15);
		
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();
		
		SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(startTime).build();
		
		Date time = scheduler.scheduleJob(jobDetail, trigger);
		
		System.out.println(jobDetail.getKey() + " 启动时间： " +time + " 重复次数： " + trigger.getRepeatCount()+ "执行间隔： "
	             + trigger.getRepeatInterval() / 1000 + " seconds");
		
		//默认启动job之后就不会关闭作业了。即使是只执行一次 如果需要 可以下面sleep线程一段时间（等待job执行完成后）手动关闭
//		Thread.sleep(65L * 1000L);
//		scheduler.shutdown(true);
	}
	
	public void RunJob2()throws Exception{
		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		
		//job2 执行10次 间隔5秒  5秒后开始
//		Logger log = LoggerFactory.getLogger(HelloJob.class);
//		开始时间   第一个参数为在某个时间之后 第二个为多久后
//		nextGivenSecondDate（Date date, int secondBase)：在date之后的xx秒启动
//		nextGivenMinteDate（Date date, int secondBase)：在date之后的xx分启动
		Date startTime = DateBuilder.nextGivenSecondDate(null, 15);
		
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();
		
		SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").
						startAt(startTime)
						.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						             .withIntervalInSeconds(5)
						              .withRepeatCount(10))
						.build();
		
		Date time = scheduler.scheduleJob(jobDetail, trigger);
		
		System.out.println(jobDetail.getKey() + " 启动时间： " +time + " 重复次数： " + trigger.getRepeatCount()+ "执行间隔： "
	             + trigger.getRepeatInterval() / 1000 + " seconds");
	}
	
	public void RunJob3()throws Exception{
		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		
		//job3：执行10次 间隔5秒 5秒后开始
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("job3", "group1").build();
		
		SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
						.startAt(DateBuilder.futureDate(5, IntervalUnit.SECOND)) //5秒后开始job
						.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						             .withIntervalInSeconds(5)
						              .withRepeatCount(10))
						.build();
		
		Date time = scheduler.scheduleJob(jobDetail, trigger);
		
		System.out.println(jobDetail.getKey() + " 启动时间： " +time + " 重复次数： " + trigger.getRepeatCount()+ "执行间隔： "
	             + trigger.getRepeatInterval() / 1000 + " seconds");
		scheduler.start();
	}
	
	public void RunJob4()throws Exception{
		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		
		//job4：执行无数次  间隔2秒 5秒后开始
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("job3", "group1").build();
		
		SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
						.startAt(DateBuilder.futureDate(5, IntervalUnit.SECOND)) //5秒后开始job
						.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						             .withIntervalInSeconds(2)
						              .repeatForever())  //永久执行
						.build();
		
		Date time = scheduler.scheduleJob(jobDetail, trigger);
		
		System.out.println(jobDetail.getKey() + " 启动时间： " +time + " 重复次数： " + trigger.getRepeatCount()+ "执行间隔： "
	             + trigger.getRepeatInterval() / 1000 + " seconds");
		scheduler.start();
	}
	
	public void run() throws Exception{
		
		
	}
	
	public static void main(String[] args) throws Exception {
		SimpleTriggerExample example1 = new SimpleTriggerExample();
//		example1.RunJob1();
//		example1.RunJob2();
//		example1.RunJob3();
		example1.RunJob4();
	}
}
