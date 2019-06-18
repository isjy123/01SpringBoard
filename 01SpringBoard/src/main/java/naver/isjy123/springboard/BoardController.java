package naver.isjy123.springboard;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import naver.isjy123.springboard.domain.SearchCriteria;
import naver.isjy123.springboard.service.BoardService;

@Controller
//모든 요청 처리 메소드의 value 앞에 이 내용이 추가
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="register", method=RequestMethod.GET)
	public String register(Model model) {
		return "board/register";
	}
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public String register(HttpServletRequest request, Model model, RedirectAttributes attr) {
		
		int result = boardService.register(request);
		if(result >0) { //성공한 경우는 board.ist로 리다이렉트
			attr.addFlashAttribute("msg", "글쓰기 성공");
			return "redirect:list";
			
		}else { //실패하면 글쓰기 요청으로 돌아감
			attr.addFlashAttribute("msg", "글쓰기 실패");
			return "redirect:register";
			
		}
	}
	//전체보기 요청을 처리하는 메소드 작성
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String list(@ModelAttribute("cri") SearchCriteria criteria, Model model) {
		Map<String, Object> map = boardService.list(criteria);
		model.addAttribute("map", map);
		return "board/list";
	}
	
	//상세보기 요청을 처리하는 메소드 작성
	@RequestMapping(value = "/detail/{bno}", method = RequestMethod.GET) 
	public String detail(@PathVariable("bno") int bno,Model model) {
		model.addAttribute("vo",boardService.detail(bno));
		return "board/detail"; 
	}
	
	//게시글 수정보기 처리 요청의 메소드 추가
	@RequestMapping(value = "/update/{bno}", method = RequestMethod.GET) 
	public String update(@PathVariable("bno") int bno, Model model) {
		model.addAttribute("vo", boardService.updateView(bno));
		return "board/update"; 
	}
	
	//게시글 수정 처리요청의 메소드 추가
	@RequestMapping(value = "/update", method = RequestMethod.POST) 
	public String update(HttpServletRequest request, RedirectAttributes rttr) {
		boardService.update(request);
		rttr.addFlashAttribute("msg", "게시글 수정에 성공하셨습니다."); 
		return "redirect:list";
	}
	
	//게시글 삭제 처리요청의 메소드추가
	@RequestMapping(value = "/delete/{bno}", method = RequestMethod.GET) 
	public String delete(@PathVariable("bno") int bno, RedirectAttributes rttr){
		int result = boardService.delete(bno);
		if(result>0) {
			rttr.addFlashAttribute("msg", "게시글 삭제에 성공하셨습니다."); 
			return "redirect:/board/list";
		}else {
			return "redirect:board/delete";
		}
			
		
	}
}
