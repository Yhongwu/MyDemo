package com.howard.hadoop.mapreduce.test02;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by Howard Yao on 2018/11/10.
 */
public class FlowReducer extends Reducer<Text, FlowBean, Text, FlowBean> {
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        long sum_up = 0;
        long sum_down = 0;

        for (FlowBean flowBean: values) {
            sum_down += flowBean.getDownFlow();
            sum_up += flowBean.getUpFlow();
        }

        context.write(key, new FlowBean(sum_up, sum_down));
    }
}
