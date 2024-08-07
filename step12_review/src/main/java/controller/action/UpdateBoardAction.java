package controller.action;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.guestbook.GuestBookBean;
import model.guestbook.GuestBookDAO;

public class UpdateBoardAction implements Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String strNum = request.getParameter("num");
		String title = request.getParameter("title");
		String author = request.getParameter("author");				
		String email = request.getParameter("email");				
		String content = request.getParameter("content");
		String password = request.getParameter("password");
		
		try{
			if(strNum == null || strNum.trim().length() == 0 ||
					title == null || title.trim().length() == 0 ||
					author == null || author.trim().length() == 0 ||
					email == null || email.trim().length() == 0 ||
					content == null || content.trim().length() == 0 ||
					password == null || password.trim().length() == 0 ){
				throw new Exception("입력값이 충분하지 않습니다.");
			}
			
			int num = Integer.parseInt(strNum);
			//수정
			boolean result = GuestBookDAO.updateContent(new GuestBookBean(num, title, author, email, content, password));
			
			//수정 결과
			if(result){//수정 성공 -> 수정된 게시글만의 상세 page
				response.sendRedirect("board?command=view&num=" + num);
			}else{
				throw new Exception("게시물이 존재하지 않거나, 비밀번호가 올바르지 않습니다.");
			}
		}catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}
