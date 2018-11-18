package com.howard.hadoop.mapreduce.test09;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 输入为第一次输出的结果
 * Google--a.txt	3
 * 即key为行数 value为Google--a.txt	3
 * Created by Howard Yao on 2018/11/18.
 */
public class SecondIndexMapper extends Mapper<LongWritable, Text, Text, Text> {
    Text k = new Text();
    Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split("--");

        k.set(fields[0]);
        v.set(fields[1]);

        context.write(k, v);

        // 最终输出到reducer的时候 同个key会归一起
    }
}
