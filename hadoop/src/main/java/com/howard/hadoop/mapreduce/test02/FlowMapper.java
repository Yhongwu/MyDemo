package com.howard.hadoop.mapreduce.test02;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * test02：流量统计
 * Created by Howard Yao on 2018/11/10.
 */
public class FlowMapper extends Mapper<LongWritable, Text, Text, FlowBean>{
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

        context.write(new Text(phoneNum), flowBean);
    }
}
