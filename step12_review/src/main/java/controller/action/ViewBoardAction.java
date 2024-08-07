package controller.action;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.guestbook.GuestBookBean;
import model.guestbook.GuestBookDAO;

public class ViewBoardAction implements Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "error.jsp";
		String strNum = request.getParameter("num");
		
		try{
			if(strNum == null || strNum.length() == 0){
				throw new Exception("입력값이 충분하지 않습니다.");
			}
			
			GuestBookBean gContent = GuestBookDAO.getContent(Integer.parseInt(strNum), true);
			
			if(gContent == null){
				throw new Exception("게시물이 존재하지 않습니다.");
			}else{
				request.setAttribute("resultContent", gContent);
				url = "read.jsp";
			}
		}catch (SQLException e) {
			request.setAttribute("errorMsg", "해당 개시글 검색시 이슈 발생, 잠시후 재요청 해 주세요");			
		}catch (Exception e) {//검색 게시글 번호가 미입력시 예외 발생 시키고 실행되는 예외 처리 문장
			request.setAttribute("errorMsg", e.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
