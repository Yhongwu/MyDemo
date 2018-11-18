package com.howard.hadoop.mapreduce.test01;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by Howard Yao on 2018/11/17.
 */
public class WordCountCombiner extends Reducer<Text, IntWritable,  Text, IntWritable>{
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        // <a,1> <a,1>   输出为<a,2>
        // 计算累加和
         int count = 0;
         for (IntWritable value: values) {
             count += value.get();
         }

         context.write(key, new IntWritable(count));
    }
}
