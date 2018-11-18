package com.howard.hadoop.mapreduce.test04;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by Howard Yao on 2018/11/17.
 */
public class OrderMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable> {

    OrderBean orderBean = new OrderBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();

        String[] fields = line.split("\t");

        // 封装bean
        orderBean.setOrderId(fields[0]);
        orderBean.setPrice(Double.parseDouble(fields[2]));

        context.write(orderBean, NullWritable.get());
    }
}
