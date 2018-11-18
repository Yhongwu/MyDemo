package com.howard.hadoop.mapreduce.test09;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by Howard Yao on 2018/11/18.
 */
public class FirstIndexReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        // 累加
        for (IntWritable value : values) {
            count += value.get();
        }
        /**
         * Google--a.txt	3
         * Google--b.txt	2
         * Google--c.txt	2
         */
        context.write(key, new IntWritable(count));
    }
}
