package com.howard.hbase.mr.demo01;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


/**
 * mr操作表
 * 将student的表数据转移到student_mr
 * student表数据：
 *  15001                column=info:age, timestamp=1552236532908, value=19
 *  15001                column=info:name, timestamp=1552236532829, value=anny
 *  15002                column=info:age, timestamp=1552236532943, value=18
 *  15002                column=info:name, timestamp=1552236532924, value=juli
 *  15003                column=info:age, timestamp=1552236532995, value=24
 *  15003                column=info:name, timestamp=1552236532967, value=wufi
 * Created by Howard Yao on 2019/3/10.
 */
public class HbaseToHbaseJob extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = this.getConf();
        //Configuration conf = HBaseConfiguration.create();
        // zookeeper集群地址
        //conf.set("hbase.zookeeper.property.clientPort", "2181");
        //conf.set("hbase.zookeeper.quorum", "192.168.1.114");
        // 192.168.1.114:600000 高可用只需配置端口号
        //conf.set("hbase.master", "600000");
        //conf.set( "fs.defaultFS" , "hdfs://192.168.1.114:8020" );
        //System. setProperty ( "HADOOP_USER_NAME" , "yhw" );
        // 组装job
        Job job = Job.getInstance(conf);

        job.setJarByClass(HbaseToHbaseJob.class);

        Scan scan = new Scan();
        scan.setCacheBlocks(false);
        scan.setCaching(500);

        TableMapReduceUtil.initTableMapperJob(
                "student", // mapper操作表名
                scan, //扫描对象
                ReadStudentMapper.class, // mapper类
                ImmutableBytesWritable.class, // mapper输出key，
                Put.class, // 输出
                job
        );

        TableMapReduceUtil.initTableReducerJob("student_mr", WriteStudentReducer.class, job);

        // mr任务数
        job.setNumReduceTasks(1);

        boolean isSucceed = job.waitForCompletion(true);
        return isSucceed ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = HBaseConfiguration.create();
        // zookeeper集群地址
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.zookeeper.quorum", "192.168.1.114");
        // 192.168.1.114:600000 高可用只需配置端口号
        conf.set("hbase.master", "600000");
        //conf.set( "fs.defaultFS" , "hdfs://192.168.1.114:8020" );
        //System.setProperty ( "HADOOP_USER_NAME" , "yhw" );
        HbaseToHbaseJob hdfsToHbaseJob = new HbaseToHbaseJob();
        int result = ToolRunner.run(conf, hdfsToHbaseJob, args);
        System.out.println(result);
    }
}
