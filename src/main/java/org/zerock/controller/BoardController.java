package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	private final BoardService service;
	
	@GetMapping("/list")
	public void list(Model model) {
		System.out.print("list...........");
		
		model.addAttribute("list", service.getList());
	}
	
	@GetMapping("/register")
	public void registerGET() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		System.out.print("board : " + board);
		
		Long bno = service.register(board);
		
		System.out.print("BNO : " + bno);
		rttr.addFlashAttribute("result", bno); // 한번만 전송 되고, 브라우저 창에는 남지 않음
		
		return "redirect:/board/list";
	}
	
	 @GetMapping({"/get", "/modify"})
	 public void get(@RequestParam("bno") Long bno, Model model) {
		 System.out.print("BNO : " + bno);
	 	 model.addAttribute("board", service.get(bno));
	 }
	
	@GetMapping("/hope")
	public void hopeGET() {
		System.out.print("###################### hope #######################");
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		
		int count = service.modify(board);
		
		if (count == 1) {
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		
		int count = service.remove(bno);
		
		if (count == 1) {
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/board/list";
	}

}
