package com.howard.hadoop.mapreduce.test03;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 最终输出类型不变
 * Created by Howard Yao on 2018/11/10.
 */
public class FlowSortReducer extends Reducer<FlowBean,Text, Text, FlowBean> {

    @Override
    protected void reduce(FlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Text next = values.iterator().next();
        context.write(next, key);
    }
}
