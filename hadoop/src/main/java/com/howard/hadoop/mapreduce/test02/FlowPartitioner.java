package com.howard.hadoop.mapreduce.test02;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.Objects;

/**
 * 分区
 * Created by Howard Yao on 2018/11/10.
 */
public class FlowPartitioner extends Partitioner<Text, FlowBean> {
    @Override
    public int getPartition(Text text, FlowBean flowBean, int numPartitions) {
        // 根据电话号码前三位截取
        String substring = text.toString().substring(0, 3);

        int partition = 4;
        if (Objects.equals("188", substring)) {
            partition = 0;
        } else if (Objects.equals("159", substring)) {
            partition = 1;
        } else if (Objects.equals("136", substring)) {
            partition = 2;
        } else if (Objects.equals("132", substring)) {
            partition = 3;
        }
        return partition;
    }
}
