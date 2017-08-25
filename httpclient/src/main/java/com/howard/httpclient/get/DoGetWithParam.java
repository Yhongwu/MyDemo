package com.howard.httpclient.get;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
/**
 * 带参数的get的简单测试
 * 2017年8月10日
 * @author hongwu
 */
public class DoGetWithParam {
	public void doGetWithParam() throws URISyntaxException, ClientProtocolException, IOException {
		//创建一个httpclient
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建一个URI
		URIBuilder uriBuilder = new URIBuilder("http://www.baidu.com/s")
								.addParameter("wd", "中国");
		System.out.println(uriBuilder.build());
		//创建一个get对象
		HttpGet httpGet = new HttpGet(uriBuilder.build());
		//执行请求
		CloseableHttpResponse response = httpClient.execute(httpGet);
		//取出响应内容
		HttpEntity entity = response.getEntity();
		String responseStr = EntityUtils.toString(entity);
//		String responseStr = EntityUtils.toString(entity, "utf-8");
		System.out.println(responseStr);
		//关闭httpclient
		response.close();
		httpClient.close();
		
	}
	
	public static void main(String[] args) {
		DoGetWithParam doGet = new DoGetWithParam();
		try {
			doGet.doGetWithParam();
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
