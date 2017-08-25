package com.howard.httpclient.post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
/**
 * 模拟post的简单测试
 * 可用于ssm等发布的服务 
 * 2017年8月10日
 * @author hongwu
 */
public class DoPost {
	public void doPost() throws ClientProtocolException, IOException {
		//创建一个httpclient
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//创建一个post请求
		HttpPost post = new HttpPost("http://gc.ditu.aliyun.com/geocoding"); //http://www.baidu.com
		//模拟表单
		List<NameValuePair> params = new ArrayList<>();
//		params.add(new BasicNameValuePair("name", "123"));
//		params.add(new BasicNameValuePair("password", "123"));
		params.add(new BasicNameValuePair("s", "广州市"));
		//把表单放入httpPost对象
		UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params);
		post.setEntity(urlEncodedFormEntity);
		//执行请求
		CloseableHttpResponse response = httpclient.execute(post);
		String responseStr = EntityUtils.toString(response.getEntity());
		System.out.println(responseStr);
		//关闭httpclient
		response.close();
		httpclient.close();
	}
	
	
	public static void main(String[] args) {
		DoPost post = new DoPost();
		try {
			post.doPost();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
