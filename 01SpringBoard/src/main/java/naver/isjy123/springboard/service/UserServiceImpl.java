package naver.isjy123.springboard.service;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import naver.isjy123.springboard.dao.BoardDao;
import naver.isjy123.springboard.dao.UserDAO;
import naver.isjy123.springboard.domain.Board;
import naver.isjy123.springboard.domain.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private BoardDao boardDao;
	
	
	@Override
	public String emailcheck(String email) {
		
		return userDao.emailcheck(email);
	}



	@Override
	public String nicknamecheck(String nickname) {
		
		return userDao.nicknamecheck(nickname);
	}

	@Override
	public int register(MultipartHttpServletRequest request) {
		int result = 0;
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		String nickname = request.getParameter("nickname");
		MultipartFile image = request.getFile("image");
		String uploadPath = request.getServletContext().getRealPath("/userimage");
		// 파일 이름 만들기
		UUID uid = UUID.randomUUID();
		String filename = image.getOriginalFilename();
		User user = new User();
		try {
			if (filename.length() > 0) {
				filename = uid + "_" + filename;
				// 저장된 파일 경로 만들기
				String filepath = uploadPath + "\\" + filename;
				// 파일 업로드
				File file = new File(filepath);
				image.transferTo(file);
			} else {
				filename = "default.png";
			}
			user.setImage(filename);
			user.setEmail(email);
			user.setPw(BCrypt.hashpw(pw, BCrypt.gensalt(10)));
			user.setNickname(nickname);
			System.out.println(user);
			result = userDao.register(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	public User login(HttpServletRequest request) {
		User user = null;
		try {
			request.setCharacterEncoding("utf-8");
			String email = request.getParameter("email");
			String pw = request.getParameter("pw");
			//로그인 관련된 DAO 메소드 호출
			user = userDao.login(email);
			//email에 해당되는 데이터가 있다면
			if(user != null) {
				//암호화된 비밀번호를 비교
				if(BCrypt.checkpw(pw, user.getPw())) {
					user.setPw(null);
				}else {
					user = null;
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return user;
	}



	@Override
	public String convertAddress(String param) {
		String address = "";
		//파라미터 읽기
		//param의 구성 - latitude : longitude
		String [] coords = param.split(":");
		String latitude = coords[0];
		String longitude = coords[1];
		//데이터 다운로드 받을 URL 생성
		String addr = "\"https://dapi.kakao.com/v2/local/geo/coord2address.json?x=" + longitude +"&y="+latitude+"&input_coord=WGS84";
		
		//다운로드 받은 문자열을 저장할 객체
		StringBuilder sb = new StringBuilder();
		try {
			//다운로드 받을 주소 URL 객체로 생성
			//파라미터에 한글이 있으면 한글을 인코딩 해야함
			//URLEncder.encode(문자열, 인코딩 방식)
			URL url = new URL(addr);
			//url을 이용해 HttpURLConnection을 생성
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			//옵션 설정
			con.setConnectTimeout(30000);
			con.setUseCaches(false);
			//헤더 추가
			con.addRequestProperty("Authorization",  "KakaoAK b5325f52a8de4012552b43820391ab67");
			//스트림 가져오기
			InputStreamReader isr = new InputStreamReader(con.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			//문자열을 읽어서 sb에 저장
			while(true) {
				String line = br.readLine();
				if(line == null) {
					break;
				}
				sb.append(line);
			}
			//json 파싱을 위해서 String으로 변환
			String json = sb.toString();
			//JSNObject -JSON 객체
			//JSONArray - JSON 배열 
			//객체는 get 자료형("키")
			//배열은 get 자료형(인덱스)
			
			//시작이 {이므로 JSON 객체로 변환
			JSONObject root = new JSONObject(json);
			JSONArray documents = root.getJSONArray("documents");
			
			
			
			//주소가 없는 경우를 대비해 length가 1 이상인 경우만 수행
			if(documents.length()>0) {
				JSONObject first = documents.getJSONObject(0);
				JSONObject roadaddress= first.getJSONObject("road_address");
				addr = roadaddress.getString("address_name");
			}
			
			isr.close();
			br.close();
			con.disconnect();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return address;
	}






}
