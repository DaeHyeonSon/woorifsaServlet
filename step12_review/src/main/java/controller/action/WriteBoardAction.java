package controller.action;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.guestbook.GuestBookBean;
import model.guestbook.GuestBookDAO;



public class WriteBoardAction implements Action{

/*
	private int num; // 글 번호, 시퀀스
		private String title; // 글 제목
		private String author; // 글 작성자
		private String email; // 글 작성자 전자메일
		private String content; // 글 내용
		private String password; // 글 비밀번호
	private String writeday; // 글 작성일
	private int readnum; // 글 조회수 
 */
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String author = request.getParameter("author");				
		String email = request.getParameter("email");				
		String content = request.getParameter("content");				
		String password = request.getParameter("password");
		
		try{
			if(title == null || title.trim().length() == 0 ||
					author == null || author.trim().length() == 0 ||
					email == null || email.trim().length() == 0 ||
					content == null || content.trim().length() == 0 ||
					password == null || password.trim().length() == 0 ){
				throw new Exception("입력값이 충분하지 않습니다.");
			}
			
			System.out.println("---------------");
			System.out.println(content);
			System.out.println("---------------");
			
			boolean result = GuestBookDAO.writeContent(new GuestBookBean(title, author, email, content, password));
		
			if(result){
				response.sendRedirect("board?command=list");
			}else{
				throw new Exception("입력값이 충분하지 않습니다.");
			}
		}catch (Exception e) {  //SQLException과 Exception 모두 처리
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}
