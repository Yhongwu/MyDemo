package com.howard.hadoop.mapreduce.test09;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by Howard Yao on 2018/11/18.
 */
public class SecondIndexReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();

        // <Google,	<c.txt   2>,<b.txt   2>,<a.txt   3>> 转为 Google	c.txt-->2	b.txt-->2	a.txt-->3
        for (Text value : values) {
            sb.append(value.toString().replace("\t","-->") + "\t");
        }

        context.write(key, new Text(sb.toString()));
    }
}
