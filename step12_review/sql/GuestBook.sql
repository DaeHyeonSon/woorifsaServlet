-- 방명록 

/*
	private int num;					// 글 번호
	private String title;				// 글 제목
	private String author;				// 글 작성자
	private String email;				// 글 작성자 전자메일
	private String content;				// 글 내용
	private String password;			// 글 비밀번호
	private String writeday;			// 글 작성일
	private int readnum;				// 글 조회수
 */

drop table gbook;
drop sequence seq_gbook_num;

create table gbook (
	num number primary key,
	title varchar2(50) not null,
	author varchar2(50) not null,  
	email varchar2(50) not null,
	content long,
	password varchar2(20) not null,
	writeday date not null,
	readnum number
);

-- 1씩 자동 증가하게 하는 table의 컬럼 도우미 객체
-- 게시글 번호, insert 개수 1씩 증가시에도 사용..
-- INSERT INTO gbook VALUES(seq_gbook.nextval,?,?,?,?,?,sysdate,0)
CREATE SEQUENCE seq_gbook_num START WITH 1 INCREMENT BY 1; 

commit;

























