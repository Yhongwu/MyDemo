package com.howard.hadoop.mapreduce.test06;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * Created by Howard Yao on 2018/11/17.
 */
public class WholeRecordReader extends RecordReader<NullWritable, BytesWritable> {

    private FileSplit split;
    private Configuration configuration;

    private BytesWritable value = new BytesWritable();
    private boolean processed = false;

    /**
     * 初始化
     * @param split
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        // 获取传递过来的数据
        this.split = (FileSplit) split;
        configuration = context.getConfiguration();

    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if (! processed) {
            // 具体处理
            // 将文件整体读取
            FileSystem fs = FileSystem.get(configuration);
            Path path = split.getPath();
            FSDataInputStream fis = null;
            try {
                // 获取到切片的输入流
                fis = fs.open(path);

                // 读取数据
                byte[] buf = new byte[(int) split.getLength()];
                IOUtils.readFully(fis, buf, 0, buf.length);

                // 设置输出
                value.set(buf, 0, buf.length);
                // 标志位 是否读完
            }finally {
                IOUtils.closeStream(fis);
            }
            processed = true;

        }

        return false;
    }

    @Override
    public NullWritable getCurrentKey() throws IOException, InterruptedException {
        return NullWritable.get();
    }

    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}
