package com.howard.hadoop.mapreduce.test03;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * mapper输出以flowbean对象作为key输出 根据flowbean的排序方法对总流量进行排序
 * Created by Howard Yao on 2018/11/10.
 */
public class FlowSortMapper extends Mapper<LongWritable, Text, FlowBean, Text>{
    FlowBean flowBean = new FlowBean();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 获取一行数据
        String line = value.toString();

        // 截取字符串
        String[] fields = line.split("\t");

        // 封装flowbean对象
        String phoneNum = fields[0];
        long upFlow = Long.valueOf(fields[1]);
        long downFlow = Long.valueOf(fields[2]);
        flowBean.set(upFlow, downFlow);

        context.write(flowBean , new Text(phoneNum));
    }
}
