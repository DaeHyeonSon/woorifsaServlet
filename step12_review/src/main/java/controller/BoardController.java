package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

import antlr.debug.InputBufferReporter;
import controller.action.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//board?command=list
@WebServlet("/board")
public class BoardController extends HttpServlet {
	
	//board?command=write&....
	//board?command=list : 모든 게시글 보기 요청
	//board?command=view&num=2 : 해당 게시글 하나만 보기 요청 
	//board?command=updateForm : 하나의 게시글 update
	//board?command=delete&password=값 : read.jsp에서 이미 보고 있는 하나의 게시글 삭제 요청
	//board?command=update
	//board?command=view&num=" + num
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getParameter("command");//list
	//	System.out.println("받은  command : "+command);
		if(command == null) {
			command = "list";   //모든 방명록 보기
		}
		StringBuilder sb = new StringBuilder();
		
		System.out.println("1");
		BufferedReader reader = request.getReader();
		String line;
		while((line = reader.readLine()) != null) {
			System.out.println("2");
			sb.append(line);
		}
		System.out.println(sb.toString());
		
	    Enumeration<String> parameterNames = request.getParameterNames();
	    while (parameterNames.hasMoreElements()) {
	        String paramName = parameterNames.nextElement();
	        String paramValue = request.getParameter(paramName);
	        System.out.println(paramName + " : " + paramValue);
	    }
		System.out.println("3");

		ActionFactory af = ActionFactory.getInstance();
		Action action = af.getAction(command);  //list, view, updateForm, write, update
		action.execute(request, response);
	}	
}