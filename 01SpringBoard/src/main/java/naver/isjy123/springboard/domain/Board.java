package naver.isjy123.springboard.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Board {
	private int bno;
	private String title;
	private String content;
	private int readcnt;
	//원래 자료형은 Date 지만 사용을 편리하도록 String으로 변환
	private Date regdate;
	private Date updatedate;
	//출력을 위한 변수
	private String dispdate;
	private String ip;
	private String email;
	private String nickname;
	private int replycnt;
}
