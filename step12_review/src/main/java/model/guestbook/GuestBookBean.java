/* class 구조
 * 1. 데이터를 표현하는 용도
 * 2. 멤버 변수/기본 생성자/멤버 변수 초기화 생성자/public한 setXxx/getXxx() / toString() 
 * 3. Java bean / Value Object Pattern / Data Transfer Object 동일
 * 4. 클래스명 협약
 * 		- 도메인Bean.java / 도메인VO.java / 도메인DTO.java/ 도메인.java
 */

package model.guestbook;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor    //생성자가 필요에 의해서 개발 해야 할 경우 기본도 필수로 추가 
@AllArgsConstructor   //select * 
@Getter
@Setter
public class GuestBookBean implements Serializable {
	private int num; // 글 번호, 시퀀스
	private String title; // 글 제목
	private String author; // 글 작성자
	private String email; // 글 작성자 전자메일
	private String content; // 글 내용
	private String password; // 글 비밀번호
	private String writeday; // 글 작성일
	private int readnum; // 글 조회수

	public GuestBookBean(int num) {
		this.num = num;
	}

	public GuestBookBean(int num, String title, String author, String email, String content) {
		this.num = num;
		this.title = title;
		this.author = author;
		this.email = email;
		this.content = content;
	}

	public GuestBookBean(String title, String author, String email, String content, String password) {
		this.title = title;
		this.author = author;
		this.email = email;
		this.content = content;
		this.password = password;
	}
	
	public GuestBookBean(int num, String title, String author, String email, String content, String password) {
		this.num = num;
		this.title = title;
		this.author = author;
		this.email = email;
		this.content = content;
		this.password = password;
	}

}