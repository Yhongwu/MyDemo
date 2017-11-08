package com.howard.serverpush.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * ajax轮询
 * 页面不断发起连接，每次不管有没有获取到正确的或者需要的数据，都立即返回结果
 * 
 */
@WebServlet("/ajaxpoll")
public class AjaxPollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxPollServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Random rand = new Random();
		int num = rand.nextInt(100); 
		StringBuffer json = null;
		
		if (num < 30 && num > 0) {
			json = new StringBuffer("{");
	        json.append("\"num\":"+num);
	        json.append("}");
			response.getWriter().print(json.toString());
		}else {
			json = new StringBuffer("{");
	        json.append("\"num\":"+null);
	        json.append("}");
			response.getWriter().print(json.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
