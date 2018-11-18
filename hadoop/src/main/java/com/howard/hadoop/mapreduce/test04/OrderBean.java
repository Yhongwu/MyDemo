package com.howard.hadoop.mapreduce.test04;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by Howard Yao on 2018/11/17.
 */
public class OrderBean implements WritableComparable<OrderBean>{
    private String orderId;
    private double price;

    @Override
    public String toString() {
        return  orderId + "\t" + price ;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public int compareTo(OrderBean o) {
        // 先按订单id排序 升序
        int result = this.orderId.compareTo(o.getOrderId());
        if (result == 0) {
            // 相等再按价格倒序
            result = price > o.getPrice() ? -1 : 1;
        }
        return result;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(orderId);
        out.writeDouble(price);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.orderId = in.readUTF();
        this.price = in.readDouble();
    }
}
