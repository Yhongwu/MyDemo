package com.howard.serverpush.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ajax长轮询
 * 在服务端保持连接而不是立即返回获取数据结果
 * 这里是使用死循环来模拟保持住连接
 */
public class AjaxPullServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AjaxPullServlet() {
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Random rand = new Random();
		StringBuffer json = null;
		while(true) {
			try {
				Thread.sleep(500); //模拟业务处理耗时
				int num = rand.nextInt(100); //获取随机数
				if (num < 30 && num > 0) { //小于30的才认为是获得了需要的数据
					json = new StringBuffer("{");
		            json.append("\"num\":"+num);
		            json.append("}");
					response.getWriter().print(json.toString());
					break; 
				}else {
					Thread.sleep(500);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
