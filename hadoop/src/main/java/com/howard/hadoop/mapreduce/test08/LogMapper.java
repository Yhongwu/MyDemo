package com.howard.hadoop.mapreduce.test08;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by Howard Yao on 2018/11/18.
 */
public class LogMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    Text k = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();

        if (!parseLog(line, context)) {
            return;
        }
        k.set(line);

        context.write(k, NullWritable.get());
    }

    /**
     * 解析日志 根据条件返回true或false
     * @param line
     * @param context
     * @return
     */
    public boolean parseLog(String line, Context context) {
        String[] fields = line.split(" ");

        // 日志长度大于11的返回true
        if (fields.length > 11) {
            // 系统计数器
            context.getCounter("map", "true").increment(1);
            return true;
        } else {
            context.getCounter("map", "false").increment(1);
            return false;
        }
    }
}
