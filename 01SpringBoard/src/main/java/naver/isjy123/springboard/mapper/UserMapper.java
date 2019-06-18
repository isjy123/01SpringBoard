package naver.isjy123.springboard.mapper;

import naver.isjy123.springboard.domain.User;

public interface UserMapper {
	public String idcheck(String email);
	public String nicknamecheck(String nickname);
	public int register(User user);
	public User login(User user);
	public int update(User user);
	public int secession(String email);
}
