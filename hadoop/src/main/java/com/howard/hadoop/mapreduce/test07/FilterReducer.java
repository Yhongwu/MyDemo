package com.howard.hadoop.mapreduce.test07;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


/**
 * 加上换行
 * Created by Howard Yao on 2018/11/18.
 */
public class FilterReducer extends Reducer<Text, NullWritable, Text, NullWritable> {

    @Override
    protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        String k = key.toString();
        k = k + "\r\n";
        context.write(new Text(k), NullWritable.get());
    }
}
