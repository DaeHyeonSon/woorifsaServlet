package controller.action;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.guestbook.GuestBookBean;
import model.guestbook.GuestBookDAO;

//read.jsp에서 수정버튼 클릭시 실행되는 로직
/* update 요청시 새롭게 게시글 고유 번호 받아서 db 다시 select 
 * 
 */
public class UpdateFormBoardAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "error.jsp";
		//<input type="hidden" name="num" value="${requestScope.resultContent.num}">
		String strNum = request.getParameter("num");
		//System.out.println("UpdateFormBoardAction execute실행  num : "+strNum);
		try{
			// || 두 조건중 하나만 true인 경우 true
			if(strNum == null || strNum.trim().length() == 0){
				throw new Exception("입력값이 충분하지 않습니다.");
			}
			
			/* 하나의 게시글 검색 시점은 두가지
			 * 1. 단순 검색
			 * 	- select
			 * 	- 조회수 업데이트 필수 (+1)
			 * 2. 검색이 아닌 수정
			 * 	- select 
			 * 	- 조회수 업데이트 거부 
			 */
			
			// 반환값은 null or GuestBookBean 객체
			GuestBookBean gContent = GuestBookDAO.getContent(Integer.parseInt(strNum), false);
			
			if(gContent == null){
				throw new Exception("해당 게시물이 존재하지 않아 수정 불가입니다.");
			}else{
				System.out.println("UpdateFormBoardAction Content.getContent().replaceAll : "+gContent.getContent().replaceAll("<br/>", "\n"));
				gContent.setContent(gContent.getContent().replaceAll("<br/>", "\n"));//???
				
				request.setAttribute("resultContent", gContent);
				url = "update.jsp";
			}
		}catch (SQLException e) {
			request.setAttribute("errorMsg", e.getMessage());
		}catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
