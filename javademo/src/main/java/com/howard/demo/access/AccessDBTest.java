package com.howard.demo.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
/**
 * 测试jdk1.7连接access数据库
 * E:\\upload\\test\\test1.mdb为自己目录下的数据库文件
 * @author Howard
 * 2017年3月2日
 */
public class AccessDBTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Map<Integer, Object[]> map = new HashMap<>();
		String[] column;
//		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//		String url="jdbc:odbc:Driver={MicroSoft Access Driver (*.mdb)};DBQ=E:\\upload\\test\\test1.mdb";  
		//jdk1.7开始必须自己下载Access_JDBC30驱动
		Class.forName("com.hxtt.sql.access.AccessDriver");
//		Connection con = DriverManager.getConnection("jdbc:Access:///d:/MYDB.accdb");
		String url = "jdbc:Access:///E:\\upload\\test\\2017.mdb";
		String user = "";
		String password = "";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		con = DriverManager.getConnection(url, user, password);  
        st = con.createStatement();
        
        rs = st.executeQuery("select * from TabBLOG where 日期时间  >= 1701010000");
        ResultSetMetaData metaDate = rs.getMetaData();     
        int number = metaDate.getColumnCount();     
        column = new String[number];     
        for (int j = 0;j < column.length; j++){     
            column[j] = metaDate.getColumnName(j + 1);     
            System.out.print(column[j]+"\t");  
        }     
        System.out.println();  
        int a = 0;  
      //输出数据  
        while(rs.next()){  
            a++;  
            Object[] rss = new Object[number];  
            for(int i = 0;i < rss.length; i++){  
                rss[i] = rs.getString(i + 1);    
                System.out.print(rss[i]+"\t");  
            }  
            System.out.println();  
            map.put(a, rss) ;                 
        }  
		
	}
}
