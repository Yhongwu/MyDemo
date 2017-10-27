package com.howard.test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Test5 {
	public static void main(String[] args) {
		CopyFile cf = new CopyFile("G:\\beifen\\upload\\test\\2009.mdb", 
	                "G:\\beifen\\upload\\test2\\2009.mdb", 3);
		cf.copeFile();
	}

}
class CopyFile {
	private String src;  //源文件
    private String dst;   //目标文件
    private int threadSize;  //开启多少个线程去复制文件
    
    public CopyFile(String src, String dst, int threadSize) {
        super();
        this.src = src;
        this.dst = dst;
        this.threadSize = threadSize;
    }
    
   //拷贝文件的方法
    public void copeFile(){
        File file = new File(src);  //源文件的File对象
        long fSize = file.length(); //得到要复制文件的大小（字节数）
        long block = fSize % threadSize == 0 ? fSize/threadSize : 
            fSize/threadSize + 1;  //保存买每个线程要下载的文件块的大小
        for(int threadId = 0;threadId < threadSize; threadId ++){
            new CopyThread(fSize, block, threadId).start();
        }


    }
    
	class CopyThread extends Thread {
		 private long fSize;  //要复制的文件的大小
	     private long block;  //每个线程要下载的文件块的大小
	     private int threadId;  //线程的Id，从0开始
	     private int buffSize = 1024*1024; //设置一个初始的缓冲区的大小
	     
	     public CopyThread(long fSize, long block, int threadId) {
	         super();
	         this.fSize = fSize;
	         this.block = block;
	         this.threadId = threadId;
	     }
	     
	     @Override
	    public void run() {
	    	 try {
	             //随机访问目标文件
	             RandomAccessFile reader = new RandomAccessFile(src, "r");
	             RandomAccessFile writer = new RandomAccessFile(dst, "rw");
	             //每个线程要下载的起始位置
	             long startPosition=block*threadId; 
	             //确定每个线程结束的下载位置
	             long endPosition = startPosition+block > fSize ? fSize:
	                 startPosition + block;
	             reader.seek(startPosition);
	             writer.seek(endPosition);
	             byte [] buff = new byte[buffSize];//设置一个缓冲区
	             while(startPosition<endPosition){
	                 int len =0;
	                 if (startPosition+buffSize<endPosition) {
	                     len=reader.read(buff);//读满缓冲区
	                 }else{
	                     //把剩余的填不满缓冲区的数据写入到缓冲区当中
	                     len = reader.read(buff, 0, (int)(endPosition-startPosition));
	                 }
	                 startPosition+=len; //起始位置读一次就发生一次变化
	                 writer.write(buff, 0, len);//把缓冲区当中的数据写入到指定文件的指定区域
	                 System.out.println("线程"+(threadId+1)+"下载了"+len+"字节");
	             }
	             reader.close();
	             writer.close();
	             System.out.println("线程"+(threadId+1)+"下载完毕");

	         } catch (IOException e) {
	             // TODO Auto-generated catch block
	             e.printStackTrace();
	         }
	    }
	}

}