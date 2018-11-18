package com.howard.hadoop.mapreduce.test01;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * test01：统计文本中的单词数
 * map
 * Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT>
 * KEYIN: 输入键值对的key 这里指行号
 * VALUEIN：输入键值对中的value 这里是每行的数据
 * KEYOUT：输出的键值对的key
 * VALUEOUT：输出的键值对中的value
 * Created by Howard Yao on 2018/11/3.
 */
public class WordCountMap extends Mapper<LongWritable,Text,Text,IntWritable>{
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        // mapreduce中的数据类型与java中的数据类型对应 只是做了序列化操作

        // 将文本转为string
        String line = value.toString();

        // 按空格切分单词
        String[] words = line.split(" ");

        // 输出为<单词，1>
        for (String word : words) {
            context.write(new Text(word), new IntWritable(1));
        }
    }
}
