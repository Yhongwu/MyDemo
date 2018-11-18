package com.howard.hadoop.mapreduce.test03;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 自定义输入或输出的bean 必须通过Writable实现序列化
 * 重写序列化和反序列化方法 注意序列化和反序列化顺序必须一致
 * 必须提供无参构造函数
 *
 * 注意实现的是WritableComparable
 * 不是Writable和Comparable两个接口  那样会出现Unable to initialize MapOutputCollector org.apache.hadoop.mapred.MapTask$MapOutputBuffer
 * Created by Howard Yao on 2018/11/10.
 */
public class FlowBean implements WritableComparable<FlowBean> {

    public FlowBean(long upFlow, long downFlow) {
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = upFlow + downFlow;
    }

    public void set(long upFlow, long downFlow) {
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = upFlow + downFlow;
    }
    public FlowBean() {
    }

    /**
     * 上行流量
     */
    private long upFlow;
    /**
     * 下行流量
     */
    private long downFlow;
    /**
     * 汇总流量
     */
    private long sumFlow;

    @Override
    public String toString() {
        /*return "FlowBean{" +
                "upFlow=" + upFlow +
                ", downFlow=" + downFlow +
                ", sumFlow=" + sumFlow +
                '}';*/
        return upFlow + "\t" + downFlow + "\t" + sumFlow;
    }

    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }

    /**
     *
     * 序列化
     * @param out
     * @throws IOException
     */
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
    }

    /**
     * 反序列化
     * @param in
     * @throws IOException
     */
    @Override
    public void readFields(DataInput in) throws IOException {
        this.upFlow = in.readLong();
        this.downFlow = in.readLong();
        this.sumFlow = in.readLong();
    }


    @Override
    public int compareTo(FlowBean o) {
        // 倒序
        return this.getSumFlow() > o.getSumFlow() ? -1 : 1;
    }
}
