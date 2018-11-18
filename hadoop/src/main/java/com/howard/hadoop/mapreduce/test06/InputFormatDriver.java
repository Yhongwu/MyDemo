package com.howard.hadoop.mapreduce.test06;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.IOException;

/**
 * 小文件处理
 * 自定义InputFormat
 * 无论hdfs还是mapreduce，对于小文件都有损效率，实践中，又难免面临处理大量小文件的场景，
 * 此时，就需要有相应解决方案。将多个小文件合并成一个文件SequenceFile，SequenceFile里面存储着多个文件，
 * 存储的形式为文件路径+名称为key，文件内容为value。
 *
 * 小文件的优化无非以下几种方式：
 * （1）在数据采集的时候，就将小文件或小批数据合成大文件再上传HDFS
 * （2）在业务处理之前，在HDFS上使用mapreduce程序对小文件进行合并
 * （3）在mapreduce处理时，可采用CombineTextInputFormat提高效率
 * Created by Howard Yao on 2018/11/17
 */
public class InputFormatDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[] { "D:/tmp/test06/input/", "D:/tmp/test06/output/" };

        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);
        job.setJarByClass(InputFormatDriver.class);

        // 关联自定义Inputformat
        job.setInputFormatClass(WholeFileInputformat.class);
        // 设置输出文件的SequenceFileOutputFormat
        job.setOutputFormatClass(SequenceFileOutputFormat.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(BytesWritable.class);

        job.setMapperClass(WholeMapper.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : 1);

    }
}
