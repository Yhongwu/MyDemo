package com.howard.hadoop.mapreduce.test04;

import com.howard.hadoop.mapreduce.test03.FlowBean;
import com.howard.hadoop.mapreduce.test03.FlowSortMapper;
import com.howard.hadoop.mapreduce.test03.FlowSortReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 统计订单中最贵的物品
 * (辅助排序：GroupingComparator分组)
 *
 * 思路： 先根据orderID和price分区和排序 得到的根据id分成多个文件中，每个文件有多条排好序的订单，id一样。
 * 为了让reducer只输出一条最贵的订单数据 使用GroupingComparator
 *
 * mapper-partitioner分区-compareto排序-combiner合并
 * Created by Howard Yao on 2018/11/10.
 */
public class OrderDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 获取配置信息 不写 默认使用本地的mapreducer
        Configuration conf = new Configuration();
        // conf.set("mapreduce.framework.name", "yarn");
        //conf.set("yarn.resourcemanager.hostname", "192.168.1.114");
        //System. setProperty ( "HADOOP_USER_NAME" , "yhw" );
        //conf .set( "fs.defaultFS" , "hdfs://192.168.1.114:8020" );
        Job job = Job.getInstance(conf);

        // 设置加载jar位置
        job.setJarByClass(OrderDriver.class);

        // 设置mapper和reducer的class
        job.setMapperClass(OrderMapper.class);
        job.setReducerClass(OrderReducer.class);

        // 设置输出mapper的数据类型
        job.setMapOutputKeyClass(OrderBean.class);
        job.setMapOutputValueClass(NullWritable.class);

        // 设置最终输出数据类型
        job.setOutputKeyClass(OrderBean.class);
        job.setOutputValueClass(NullWritable.class);
        FileSystem fs = FileSystem. get(conf);
        // 设置输入与输出的数据路径 G:\tmp
        //FileInputFormat.setInputPaths(job, new Path(args[0]));
        //FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //FileInputFormat.setInputPaths(job, new Path(" /test/test02/wc.txt"));
        // Path path = new Path("/test/test02");
        FileInputFormat.setInputPaths(job, new Path("D:/tmp/test04/input/order.txt"));
        // 输出路径 output文件夹必须不存在
        //FileOutputFormat.setOutputPath(job, new Path("D:/tmp/test04/output"));
        FileOutputFormat.setOutputPath(job, new Path("D:/tmp/test04/output2"));

        // 注释对加与不加GroupingComparator区别
        // 关联GroupingComparator
        job.setGroupingComparatorClass(OrderGroupingComparator.class);

        // 设置分区
        job.setPartitionerClass(OrderPartitioner.class);
        // 分区数
        job.setNumReduceTasks(3);

        // 提交
        job.submit();
        boolean flag = job.waitForCompletion(true);
        System.exit(flag ? 0 : 1);

    }
}
