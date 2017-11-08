package com.howard.serverpush.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SSE(Server-Sent Events)实现服务器推送
 * IE不兼容
 */
@WebServlet("/ssepoll")
public class SseServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType( "text/event-stream;charset=UTF-8" );
        response.setHeader( "Cache-Control", "no-cache" );
        response.setHeader( "Connection", "keep-alive" );
        PrintWriter out = response.getWriter();
        Random rand = new Random();
        while(true) {
			try {
				Thread.sleep(500); //模拟业务处理耗时
				int num = rand.nextInt(100); //获取随机数
				if (num < 30 && num > 0) { //小于30的才认为是获得了需要的数据
		            out.print( "data:" + num + "\n\n" );
		            out.flush();
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
