package com.howard.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

/**
 * Created by Howard Yao on 2018/12/8.
 */
public class ToLowerCase extends UDF{
    public Text evaluate(Text str){
        if(str == null) {
            return null;
        }
        if(str != null && str.toString().length() <= 0) {
            return null;
        }
        return new Text(str.toString().toLowerCase());
    }

    public static void main(String[] args) {
        System.out.println(new ToLowerCase().evaluate(new Text(args[0])));
    }
}
