package com.howard.hbase.base;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * hbase基本操作
 * 版本0.98 待升级
 * Created by Howard Yao on 2019/3/10.
 */
public class HbaseTest {
    /**
     * 创建Hadoop以及HBased管理配置对象
     */
    public static Configuration conf;
    static{
        //使用HBaseConfiguration的单例方法实例化
        conf = HBaseConfiguration.create();
        // zookeeper集群地址
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.zookeeper.quorum", "192.168.1.114,192.168.1.115,192.168.1.116");

        // 192.168.1.114:600000 高可用只需配置端口号
        conf.set("hbase.master", "600000");
    }

    /**
     * 表是否存在
     * @param tableName
     * @return
     * @throws IOException
     */
    public static boolean isTableExist(String tableName) throws IOException {
        HBaseAdmin hBaseAdmin = new HBaseAdmin(conf);
        return hBaseAdmin.tableExists(tableName);
    }

    /**
     * 创建表
     * @param tableName
     * @param columnFamily 多个列族
     * @throws IOException
     */
    public static void createTable(String tableName, String ... columnFamily) throws IOException {
        HBaseAdmin hBaseAdmin = new HBaseAdmin(conf);
        if (isTableExist(tableName)) {
            // 判断表是否存在
            System.out.println("表" + tableName + "已存在");
        } else {
            // 创建表属性对象,表名需要转字节
            HTableDescriptor descriptor = new HTableDescriptor(TableName.valueOf(tableName));

            // 创建多个列族
            for (String cf : columnFamily) {
                descriptor.addFamily(new HColumnDescriptor(cf));
            }

            // 根据对表的配置，创建表
            hBaseAdmin.createTable(descriptor);
            System.out.println("表" + tableName + "创建成功");
        }
    }

    /**
     * 删除表
     * @param tableName
     * @throws IOException
     */
    public static void dropTable(String tableName) throws IOException {
        HBaseAdmin hBaseAdmin = new HBaseAdmin(conf);
        if (isTableExist(tableName)) {
            hBaseAdmin.disableTable(tableName);
            hBaseAdmin.deleteTable(tableName);
            System.out.println("表" + tableName + "删除成功！");
        } else {
            System.out.println("表" + tableName + "不存在！");
        }
    }

    /**
     * 插入数据
     * @param tableName
     * @param rowKey
     * @param columnFamily
     * @param column
     * @param value
     * @throws IOException
     */
    public static void addRowData(String tableName, String rowKey, String columnFamily, String column, String value) throws IOException {
        // 创建表对象
        HTable hTable = new HTable(conf, tableName);

        // 插入数据
        Put put = new Put(rowKey.getBytes());
        // 列族：列：值
        put.add(columnFamily.getBytes(), column.getBytes(), value.getBytes());

        hTable.put(put);
        hTable.close();
        System.out.println("insert success!");
    }

    /**
     * 删除多行数据
     * @param tableName
     * @param rows
     * @throws IOException
     */
    public static void deleteMultiRow(String tableName, String... rows) throws IOException {
        HTable hTable = new HTable(conf, tableName);

        List<Delete> deleteList = new ArrayList<>();
        for (String row : rows) {
            Delete delete = new Delete(row.getBytes());
            deleteList.add(delete);
        }
        hTable.delete(deleteList);
        hTable.close();

    }

    /**
     * 获取数据
     * @param tableName
     * @throws IOException
     */
    public static void getAllRows(String tableName) throws IOException {
        HTable hTable = new HTable(conf, tableName);

        // 扫描对象 扫描region
        Scan scan = new Scan();

        ResultScanner resultScanner = hTable.getScanner(scan);

        for (Result result : resultScanner) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                // rowKey
                System.out.println(Bytes.toString(CellUtil.cloneRow(cell)));
                System.out.println(Bytes.toString(CellUtil.cloneFamily(cell)));
                System.out.println(Bytes.toString(CellUtil.cloneQualifier(cell)));
                System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // createTable("person", "info", "base","job","school");
        //System.out.println(isTableExist("person"));

        //addRowData("person", "1001", "info", "name", "tom");
        //addRowData("person", "1001", "info", "sex", "mike");
		//addRowData("person", "1001", "info", "age", "25");
		//addRowData("person", "1001", "job", "deptNo", "1234");

        // getAllRows("person");

        // deleteMultiRow("person", "1001");

        // dropTable("person");

        createTable("student_mr", "info", "name","age");
        //System.out.println(isTableExist("person"));

        //addRowData("student", "15001", "info", "name", "anny");
        //addRowData("student", "15001", "info", "age", "19");
        //addRowData("student", "15002", "info", "name", "juli");
        //addRowData("student", "15002", "info", "age", "18");
        //addRowData("student", "15003", "info", "name", "wufi");
        //addRowData("student", "15003", "info", "age", "24");


    }
}
