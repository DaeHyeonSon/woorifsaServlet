package model.guestbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DataSourceManager;

public class GuestBookDAO {

	public static boolean writeContent(GuestBookBean vo) throws SQLException{
		Connection conn = null;	
		PreparedStatement pstmt = null;

		try {
			
			conn = DataSourceManager.getConnection();
			
			pstmt = conn.prepareStatement("INSERT INTO gbook VALUES (seq_gbook_num.nextval, ?, ?, ?, ?, ?, sysdate, 0)");
	       
			pstmt.setString(1, vo.getTitle());
	        pstmt.setString(2, vo.getAuthor());
	        pstmt.setString(3, vo.getEmail());
	        pstmt.setString(4, vo.getContent());
	        pstmt.setString(5, vo.getPassword());
	        
			if(pstmt.executeUpdate() != 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally{
			DataSourceManager.close(conn, pstmt);
		}
		
		return false;
	}
	
	public static GuestBookBean getContent(int num, boolean flag) throws SQLException{		
		Connection conn = null;	
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		GuestBookBean vo  = null;
		
		try {
			conn = DataSourceManager.getConnection();
			
			//게시글 조회수 증가 로직, 수정시에는 조회수 갱싱 안 함
			if(flag){
				pstmt = conn.prepareStatement("UPDATE gbook set readnum=readnum+1 WHERE num=?");
				pstmt.setInt(1, num);
				
				if(pstmt.executeUpdate() == 0) { 
					pstmt.close();  
					pstmt = null;   
					
					return vo; 
				}
			}
			
			pstmt = conn.prepareStatement("SELECT title,author,email,content,password, to_char(writeday,'yyyy/mm/dd hh24:mi:ss'), readnum from gbook WHERE num=?");
			pstmt.setInt(1, num);	
			rset = pstmt.executeQuery();
			
			/* replaceAll("\n","<br/>")
			 * - 문자열의 모든 \n을 <br /> 변환해주는 메소드
			 * - 필요성
			 * 	- 브라우저에선 <br> tag가 new line
			 *  - 순수 문자열의 \n 개행을 의미하는 특수 기호
			 *  - 브라우저에 new line입력할 경우 db에도 \n과 같은 형식으로 저장
			 *  - 실제 브라우저에 출력시에는 new line (\n) -> <br /> 변환해서 출력			 * 
			 */
			if(rset.next()){
				vo = new GuestBookBean(num, rset.getString(1),
					rset.getString(2), rset.getString(3), rset.getString(4).replaceAll("\n","<br>"),
					rset.getString(5), rset.getString(6), rset.getInt(7));
			}
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally{
			DataSourceManager.close(conn, pstmt, rset);
		}
		return vo;
	}
	

	public  static boolean deleteContent(int num, String password) throws SQLException{
		Connection conn = null;	
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = DataSourceManager.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM gbook WHERE num=? and password=?");
	        pstmt.setInt(1,num);
	        pstmt.setString(2,password);
	        
			if(pstmt.executeUpdate() != 0) {
				result = true;
			}
		}catch (SQLException e){
			e.printStackTrace();
			throw e;
		}finally{
			DataSourceManager.close(conn, pstmt);
		}
		
		return result;		
	}

	
	public  static boolean updateContent(GuestBookBean vo) throws SQLException{
		Connection conn = null;	
		PreparedStatement pstmt = null;
		
		try {
			conn = DataSourceManager.getConnection();
			pstmt = conn.prepareStatement("UPDATE gbook set title=?, author=?, email=?, content=? WHERE num=? AND password=?");

			pstmt.setString(1, vo.getTitle());
		    pstmt.setString(2, vo.getAuthor());
		    pstmt.setString(3, vo.getEmail());
		    pstmt.setString(4, vo.getContent());
		    pstmt.setInt(5, vo.getNum());
		    pstmt.setString(6, vo.getPassword());
		    
			int count = pstmt.executeUpdate();
			
			if(count != 0){
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally{
			DataSourceManager.close(conn, pstmt);
		}
		
		return false;
		
	}
	
	//모든 방명록 검색 후 반환
	public static ArrayList<GuestBookBean> getAllContents() throws SQLException{
		Connection conn = null;	
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<GuestBookBean> list  = null;
		
		try {
			conn = DataSourceManager.getConnection();
			
			String sql="SELECT num, title, author, email, content, password, to_char(writeday,'yyyy/mm/dd hh24:mi:ss'), readnum from gbook order by num desc";	
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<GuestBookBean>();
			
			while(rset.next()){
				list.add(new GuestBookBean(rset.getInt(1), rset.getString(2),
						rset.getString(3), rset.getString(4), rset.getString(5), 
						rset.getString(6), rset.getString(7), rset.getInt(8)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally{
			DataSourceManager.close(conn, pstmt, rset);
		}
		return list;
	}
	
}
