package com.howard.hadoop.mapreduce.test07;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

/**
 * 自定义RecordWriter
 *
 * 过滤日志
 * Created by Howard Yao on 2018/11/18.
 */
public class FileterRecordWriter extends RecordWriter<Text, NullWritable> {
    String googleOutPathString = "D:/tmp/test07/output1/google.log";
    String otherOutPathString = "D:/tmp/test07/output1/other.log";
    FSDataOutputStream googleOut = null;
    FSDataOutputStream otherOut = null;

    public FileterRecordWriter(TaskAttemptContext job) {
        FileSystem fs;
        try {
            // 获取文件系统
            fs = FileSystem.get(job.getConfiguration());

            // 创建输出文件路径
            Path googlePath = new Path(googleOutPathString);
            Path otherPath = new Path(otherOutPathString);

            // 创建输出流
            googleOut = fs.create(googlePath);
            otherOut = fs.create(otherPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 具体处理逻辑
     * 这里根据文件内容关键字将结果输出到不同文件
     * @param key
     * @param value
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void write(Text key, NullWritable value) throws IOException, InterruptedException {
        if (key.toString().contains("google")) {
            googleOut.write(key.toString().getBytes());
        } else {
            otherOut.write(key.toString().getBytes());
        }
    }

    /**
     * 关闭操作
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        // 关闭资源
        if (googleOut != null) {
            googleOut.close();
        }
        if (otherOut != null) {
            otherOut.close();
        }
    }
}
