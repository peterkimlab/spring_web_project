package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@PostMapping("/register")
	public void register(BoardVO board) {
		System.out.print("board : " + board);
		
		Long bno = service.register(board);
		
		System.out.print("BNO : " + bno);
	}

}
