package com.howard.hadoop.mapreduce.test06;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * Created by Howard Yao on 2018/11/17.
 */
public class WholeMapper extends Mapper<NullWritable, BytesWritable, Text, BytesWritable> {
    // 输出的text为每个小文件的路径

    Text k = new Text();
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // 获取切片路径
        FileSplit inputSplit = (FileSplit)context.getInputSplit();
        Path path = inputSplit.getPath();

        k.set(path.toString());
    }

    @Override
    protected void map(NullWritable key, BytesWritable value, Context context) throws IOException, InterruptedException {
        context.write(k, value);
    }
}
