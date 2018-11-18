package com.howard.hadoop.mapreduce.test04;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * map之后reduce之前
 * 默认使用HashPartitioner分区
 *
 * 按照orderId分区
 * Created by Howard Yao on 2018/11/17.
 */
public class OrderPartitioner extends Partitioner<OrderBean, NullWritable>{

    @Override
    public int getPartition(OrderBean orderBean, NullWritable nullWritable, int numPartitions) {
        // HashPartitioner 默认实现：return (key.hashCode() & Integer.MAX_VALUE) % numReduceTasks;

        // 按照orderId分区
        return (orderBean.getOrderId().hashCode() & Integer.MAX_VALUE) % numPartitions;
    }
}
