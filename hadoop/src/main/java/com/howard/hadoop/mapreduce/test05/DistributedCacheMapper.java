package com.howard.hadoop.mapreduce.test05;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 利用先加载的缓存文件先进行处理 实现缓存 减轻reduce阶段压力 甚至reduce不需要做任何处理了
 * Created by Howard Yao on 2018/11/17.
 */
public class DistributedCacheMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    /**
     * 利用map实现缓存
     */
    Map<String, String> pdMap = new HashMap<>();
    /**
     * 加载缓存
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // 获取缓存的文件
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:/tmp/test05/input/pd.txt")));

        String line;
        while(StringUtils.isNotEmpty(line = reader.readLine())){
            // 切割
            String[] fields = line.split("\t");
            System.out.println(fields[0]);
            System.out.println(fields[0].length());
            // 缓存数据到集合
            pdMap.put(fields[0], fields[1]);
        }

        // 关闭流
        reader.close();

    }
    Text k = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 获取一行
        String line = value.toString();

        // 截取
        String[] fields = line.split("\t");

        // 获取订单id
        String orderId = fields[1];

        // 获取商品名称
        String pdName = pdMap.get(orderId);

        // 拼接
        k.set(line + "\t"+ pdName);

        // 写出  基本完成了reduce的处理过程
        context.write(k, NullWritable.get());

    }
}
