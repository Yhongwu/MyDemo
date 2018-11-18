package com.howard.hadoop.mapreduce.test05;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by Howard Yao on 2018/11/17.
 */
public class TableBean implements Writable{
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 产品id
     */
    private String pId;
    /**
     * 产品数量
     */
    private int amount;
    /**
     * 产品名称
     */
    private String pname;
    /**
     * 表的标记
     */
    private String flag;

    @Override
    public String toString() {
        return orderId + "\t" + pId + "\t" + amount + "\t" ;
    }

    public TableBean(String orderId, String pId, int amount, String pname, String flag) {
        this.orderId = orderId;
        this.pId = pId;
        this.amount = amount;
        this.pname = pname;
        this.flag = flag;
    }

    public TableBean() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(orderId);
        out.writeUTF(pId);
        out.writeInt(amount);
        out.writeUTF(pname);
        out.writeUTF(flag);

    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.orderId = in.readUTF();
        this.pId = in.readUTF();
        this.amount = in.readInt();
        this.pname = in.readUTF();
        this.flag = in.readUTF();
    }
}
