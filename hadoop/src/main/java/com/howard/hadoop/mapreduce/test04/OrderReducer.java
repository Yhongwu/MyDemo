package com.howard.hadoop.mapreduce.test04;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by Howard Yao on 2018/11/17.
 */
public class OrderReducer extends Reducer<OrderBean, NullWritable, OrderBean, NullWritable>{
    @Override
    protected void reduce(OrderBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        context.write(key, NullWritable.get());
    }

}
