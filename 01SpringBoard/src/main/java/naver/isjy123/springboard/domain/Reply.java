package naver.isjy123.springboard.domain;

import lombok.Data;

//MyBatis에서 DTO 클래스를 만들 때는 이름에 주의
//매핑 구문을 별도로 작성하지않으면 컬럼이름이 프로퍼티와 같아야 함
//이름 틀렸을 때 프로퍼티가 없다는 예외가 발생하게 됨
@Data
public class Reply {
	private int rno;
	private String replytext;
	//Date 타입은 연산을 할 때는 편리하지만 출력을 할 때는 불편햇 String으로
	private String regdate;
	//출력할 날짜 포맷을 저장할 프로퍼티
	private String datadisp;
	private String email;
	private String nickname;
	private int bno;
}
