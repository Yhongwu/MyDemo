package com.howard.hadoop.mapreduce.test09;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * Created by Howard Yao on 2018/11/18.
 */
public class FirstIndexMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    Text k = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 获取切片名
        FileSplit inputSplit = (FileSplit) context.getInputSplit();
        String name = inputSplit.getPath().getName();

        String line = value.toString();

        String[] words = line.split(" ");

        for (String word : words) {
            k.set(word + "--" + name);
            context.write(k, new IntWritable(1));
        }
    }
}
