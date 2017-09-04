package com.howard.demo.multithreading.exercise03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
/**
 * 欢聚yy 2017校招a卷编程题目
 * 用户列表数据 :List<Long>uidList, size=10000
 * 消费查询远程接口： long  queryByUid(long uid)
 * 请根据用户信息及远程接口，写一段程序统计用户的平均消费。
 * 要求：以性能最优实现 
 * 
 * 这里由于调用远程接口 所以可能会比较慢
 * 可以使用继承Callable实现的线程，当真正需要时才返回执行结果，可提高响应速度
 * 2017年9月3日
 * @author hongwu
 */
public class MutilThreadTask {
	public void staUserConsum() {
		List<Long> uidList = new ArrayList<>();
		uidList.add(10000l);
		uidList.add(10001l);
		uidList.add(10002l);
		uidList.add(10003l);
		uidList.add(10004l);
		uidList.add(10005l);
		//...模拟 增加10000个用户
		new FutureTaskExample().testExceteMutilTask(uidList);
	}
	
	class MyCallable implements Callable<Long> {
		private long uid;
		
		public MyCallable(long uid) {
			super();
			this.uid = uid;
		}

		@Override
		public Long call() throws Exception {
			return queryByUid(uid);
		}
		
		public long queryByUid(long uid) {
			long result = 10000l;
			//todo
			//耗时操作 从远程获取用户消费数据
			return result;
		}
	}
	
	class FutureTaskExample {
		public void testExceteMutilTask(List<Long> uidList) {
			//线程池
			ExecutorService executor = Executors.newFixedThreadPool(10);
			List<FutureTask<Long>> futureTasks = new ArrayList<>();
			for (Long uid : uidList) {
				MyCallable callable = new MyCallable(uid);
				FutureTask<Long> futureTask = new FutureTask<>(callable);
				executor.execute(futureTask);
				//并没有马上获取线程执行结果
				futureTasks.add(futureTask);
			}
			long totalConsum = 0l;
			for (FutureTask<Long> task : futureTasks) {
				Long consum = null;
				try {
					consum = task.get();
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (consum != null) {
					totalConsum += consum;
				}
			}
			//关闭线程池
			executor.shutdown();
			long avgConsum = totalConsum / futureTasks.size();
			System.out.println("FutureTask avgConsum=" +
								avgConsum+",totalConsum="+totalConsum); 
			
		}
	}
	
	public static void main(String[] args) {
		 new MutilThreadTask().staUserConsum();
	}
}
