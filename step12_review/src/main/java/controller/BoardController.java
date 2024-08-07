package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import controller.action.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

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
		
		
		
		String title = request.getParameter("title"); // 글 제목
		String author = request.getParameter("author"); // 글 작성자
		String email = request.getParameter("email"); // 글 작성자 전자메일
		String content = request.getParameter("content");
		String password = request.getParameter("password"); // 글 비밀번호
		
		if(title != null) {
			System.out.println("title: " + title);
			System.out.println("author: " + author);
			System.out.println("email: " + email);
			System.out.println("content: " + content);
			System.out.println("password: " + password);
			System.out.println();
		}
		

		ActionFactory af = ActionFactory.getInstance();
		Action action = af.getAction(command);  //list, view, updateForm, write, update
		action.execute(request, response);
	}	
}