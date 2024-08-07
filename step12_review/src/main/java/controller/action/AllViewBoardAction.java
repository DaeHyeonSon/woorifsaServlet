package controller.action;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.guestbook.GuestBookDAO;

public class AllViewBoardAction implements Action {
 
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "error.jsp";
		
		try {
			request.setAttribute("list", GuestBookDAO.getAllContents());
			url = "list.jsp";
		} catch (SQLException e) {
			request.setAttribute("errorMsg", "모든 방명록 검색시 문제 발생, 잠시후 재 시도 하세요");
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
	
}
