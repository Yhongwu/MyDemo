package com.howard.quartz.hello;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class JobRunnerExample {
	public void run() throws Exception{
		//通过SchedulerFactory工厂类获取Scheduler 
		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		
//		Date runTime = DateBuilder.evenMinuteDate(new Date());
		//JobDetail：用于描叙Job实现类及其他的一些静态信息，构建一个作业实例 
		//quartz2.x是用JobBuilder静态方法来new出jobdetail实例的
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
				           .withIdentity("job1", "group1") //设定名称和组 可后期用于根据名称或组获取信息等
				           .build();
		
		//Trigger：描述作业出发的规则等
		//quartz2.x是用TriggerBuilder静态方法来new出trigger实例的
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "group1") //同一个scheduler里的trigger或jobDetail不能有相同的名（jobDetail和trigger可以相同 ）
				.build();
		
		//向scheduler添加jobDetail和trigger
		scheduler.scheduleJob(jobDetail, trigger);
		//启动
		scheduler.start();
		//主线程睡眠 作业调度是另起一个线程的 主线程睡眠不影响执行 但主线程停止就会影响
		Thread.sleep(65L * 1000L);
		
		//停止作业调度 默认作业调度一直运行下去的
		scheduler.shutdown(true);
	}
	
	public static void main(String[] args) throws Exception {
		JobRunnerExample example1 = new JobRunnerExample();
		example1.run();
	}
}
