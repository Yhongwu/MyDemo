package com.howard.serverpush.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 基于Iframe的流方式实现长轮询
 * websocket,SSE,ajax等新代替解决方案的出现，iframe基本只使用在ie上了。
 * ie上运行时比较完美，但在火狐谷歌上测试会看到请求连接的旋转标志
 */
@WebServlet("/iframepoll")
public class IframePollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IframePollServlet() {
        super();
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
//					json = new StringBuffer("{");
//		            json.append("\"num\":"+num);
//		            json.append("}");
					response.getWriter().write("<script type='text/javascript'>parent.getNum("+num+");</script>");
					response.flushBuffer();
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
