package com.howard.hadoop.mapreduce.test01;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * test01
 * reduce
 * Reducer<KEYIN, VALUEIN, KEYOUT, VALUEOUT>
 * KEYIN, VALUEIN：对应map的输入
 * KEYOUT, VALUEOUT：处理结果输出 这里为<单词，单词出现的次数>
 * Created by Howard Yao on 2018/11/3.
 */
public class WordCountReduce extends Reducer<Text,IntWritable,Text,IntWritable>{

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;

        // 汇总
        for(IntWritable value: values) {
            count += value.get();
        }

        // 输出总次数
        context.write(key, new IntWritable(count));
    }
}
