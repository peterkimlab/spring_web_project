package org.zerock.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.model.AuthorVO;
import org.zerock.model.Criteria;
import org.zerock.model.PageDTO;
import org.zerock.service.AuthorService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
    private AuthorService authorService;
	
	/* 관리자 메인 페이지 이동 */
	@RequestMapping(value="main", method = RequestMethod.GET)
	public void adminMainGET() throws Exception{
		
		logger.info("관리자 페이지 이동");
		
	}
	
	/* 상품 등록 페이지 접속 */
    @RequestMapping(value = "goodsManage", method = RequestMethod.GET)
    public void goodsManageGET() throws Exception{
        logger.info("상품 등록 페이지 접속");
    }
    
    /* 상품 등록 페이지 접속 */
    @RequestMapping(value = "goodsEnroll", method = RequestMethod.GET)
    public void goodsEnrollGET() throws Exception{
        logger.info("상품 등록 페이지 접속");
    }
    
    /* 작가 등록 페이지 접속 */
    @RequestMapping(value = "authorEnroll", method = RequestMethod.GET)
    public void authorEnrollGET() throws Exception{
        logger.info("작가 등록 페이지 접속");
    }
    
    /* 작가 관리 페이지 접속 */
    // http://localhost:8080/admin/authorManage?pageNum=1&amount=10
    @RequestMapping(value = "authorManage", method = RequestMethod.GET)
    public void authorManageGET(Criteria cri, Model model) throws Exception{
        logger.info("작가 관리 페이지 접속........" + cri);
        
        /* 작가 목록 출력 데이터 */
        List list = authorService.authorGetList(cri);
        
        System.out.println("authorManage : " + list);
        
        logger.info("authorManage :" +  list);
        
        model.addAttribute("list", list);
        
        /* 페이지 이동 인터페이스 데이터 */
        int total = authorService.authorGetTotal(cri);
        
        PageDTO pageMaker = new PageDTO(cri, total);
        
        /* 페이지 이동 인터페이스 데이터 */
        model.addAttribute("pageMaker", new PageDTO(cri, authorService.authorGetTotal(cri)));
 

        
    }
    
    /* 작가 등록 */
    @RequestMapping(value="authorEnroll.do", method = RequestMethod.POST)
    public String authorEnrollPOST(AuthorVO author, RedirectAttributes rttr) throws Exception{
 
        logger.info("authorEnroll :" +  author);
        
        authorService.authorEnroll(author);      // 작가 등록 쿼리 수행
        
        rttr.addFlashAttribute("enroll_result", author.getAuthorName()); // 등록 성공 메세지(작가이름)
        
        return "redirect:/admin/authorManage";
        
    }
	
}
