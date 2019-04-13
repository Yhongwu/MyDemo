package com.howard.hbase.mr.demo01;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by Howard Yao on 2019/3/10.
 */
public class ReadStudentMapper extends TableMapper<ImmutableBytesWritable, Put> {
    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {
        Put put = new Put(key.get());
        for (Cell cell : value.rawCells()) {
            if (Objects.equals("info", Bytes.toString(CellUtil.cloneFamily(cell)))) {
                if (Objects.equals("name", Bytes.toString(CellUtil.cloneQualifier(cell)))) {
                    put.add(cell);
                } else if (Objects.equals("age", Bytes.toString(CellUtil.cloneQualifier(cell)))) {
                    put.add(cell);
                }

            }
        }
        context.write(key, put);
    }
}
