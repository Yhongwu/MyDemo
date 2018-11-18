package com.howard.hadoop.mapreduce.test04;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * Created by Howard Yao on 2018/11/17.
 */
public class OrderGroupingComparator extends WritableComparator{
    /**
     * 空参构造
     */
    public OrderGroupingComparator() {
        super(OrderBean.class, true);
    }

    // 重写

    /**
     * 根据订单id比较 判断是否一组
     * @param a
     * @param b
     * @return
     */
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        OrderBean aBean = (OrderBean)a;
        OrderBean bBean = (OrderBean)b;

        // 根据订单id比较 判断是否一组
        return aBean.getOrderId().compareTo(bBean.getOrderId());
    }
}
