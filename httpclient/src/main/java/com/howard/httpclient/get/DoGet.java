package com.howard.httpclient.get;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
/**
 * get的简单测试
 * 2017年8月10日
 * @author hongwu
 */
public class DoGet {
	
	public void doGet() throws ClientProtocolException, IOException {
		//创建一个httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建一个httpGet对象，指定访问的url
		HttpGet httpGet = new HttpGet("http://www.jd.com");
		//执行请求
		CloseableHttpResponse response = httpClient.execute(httpGet);
		//取出响应内容
		HttpEntity entity = response.getEntity();
		//默认编码是ISO8859-1
		String responseStr = EntityUtils.toString(entity);
		System.out.println(responseStr);
		//关闭httpclient
		response.close();
		httpClient.close();
		
	}
	
	public static void main(String[] args) {
		DoGet doGet = new DoGet();
		try {
			doGet.doGet();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
