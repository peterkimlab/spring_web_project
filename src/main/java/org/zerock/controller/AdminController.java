package org.zerock.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.model.AttachImageVO;
import org.zerock.model.AuthorVO;
import org.zerock.model.BookVO;
import org.zerock.model.Criteria;
import org.zerock.model.PageDTO;
import org.zerock.service.AdminService;
import org.zerock.service.AuthorService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
    private AuthorService authorService;
	
	@Autowired
	private AdminService adminService;
	
	/* 관리자 메인 페이지 이동 */
	@RequestMapping(value="main", method = RequestMethod.GET)
	public void adminMainGET() throws Exception{
		
		logger.info("관리자 페이지 이동");
		
	}
	
	/* 상품 관리(상품목록) 페이지 접속 */
	@RequestMapping(value = "goodsManage", method = RequestMethod.GET)
	public void goodsManageGET(Criteria cri, Model model) throws Exception{
		
		logger.info("상품 관리(상품목록) 페이지 접속");
		
		/* 상품 리스트 데이터 */
		List list = adminService.goodsGetList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
		} else {
			model.addAttribute("listCheck", "empty");
			return;
		}
		
		/* 페이지 인터페이스 데이터 */
		model.addAttribute("pageMaker", new PageDTO(cri, adminService.goodsGetTotal(cri)));
		
	}
    
    /* 상품 등록 페이지 접속 */
    @RequestMapping(value = "goodsEnroll", method = RequestMethod.GET)
    public void goodsEnrollGET(Model model) throws Exception{
        logger.info("상품 등록 페이지 접속");
        
        ObjectMapper objm = new ObjectMapper();
		
		List list = adminService.cateList();
		
		String cateList = objm.writeValueAsString(list);
		
		model.addAttribute("cateList", cateList);
		
		logger.info("변경 전.........." + list);
		logger.info("변경 후.........." + cateList);
		
		System.out.println("변경 전.........." + list);
		System.out.println("변경 후.........." + cateList);
        
    }
    
    /* 상품 조회 페이지 */
	@GetMapping({"/goodsDetail", "/goodsModify"})
	public void goodsGetInfoGET(int bookId, Criteria cri, Model model) throws JsonProcessingException {
		
		logger.info("goodsGetInfo()........." + bookId);
		
		ObjectMapper mapper = new ObjectMapper();
		
		/* 카테고리 리스트 데이터 */
		model.addAttribute("cateList", mapper.writeValueAsString(adminService.cateList()));		
		
		/* 목록 페이지 조건 정보 */
		model.addAttribute("cri", cri);
		
		/* 조회 페이지 정보 */
		model.addAttribute("goodsInfo", adminService.goodsGetDetail(bookId));
		
	}
	
	/* 상품 정보 수정 */
	@PostMapping("/goodsModify")
	public String goodsModifyPOST(BookVO vo, RedirectAttributes rttr) {
		
		logger.info("goodsModifyPOST.........." + vo);
		
		int result = adminService.goodsModify(vo);
		
		rttr.addFlashAttribute("modify_result", result);
		
		return "redirect:/admin/goodsManage";		
		
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
        
        System.out.println("authorManage ::::::: " + list);
        
        logger.info("authorManage -------- " +  list);
        
        if(!list.isEmpty()) {
			model.addAttribute("list",list);	// 작가 존재 경우
		} else {
			model.addAttribute("listCheck", "empty");	// 작가 존재하지 않을 경우
		}
        
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
    
    /* 작가 상세 페이지 */
    @GetMapping({"/authorDetail", "/authorModify"})
	public void authorGetInfoGET(int authorId, Criteria cri, Model model) throws Exception {
		
		logger.info("authorDetail......." + authorId);
		
		/* 작가 관리 페이지 정보 */
		model.addAttribute("cri", cri);
		
		/* 선택 작가 정보 */
		model.addAttribute("authorInfo", authorService.authorGetDetail(authorId));
		
	}
    
    /* 작가 정보 수정 */
	@PostMapping("/authorModify")
	public String authorModifyPOST(AuthorVO author, RedirectAttributes rttr) throws Exception{
		
		logger.info("authorModifyPOST......." + author);
		System.out.println("authorModifyPOST ::::::: " + author);
		
		int result = authorService.authorModify(author);
		
		rttr.addFlashAttribute("modify_result", result);
		
		return "redirect:/admin/authorManage";
		
	}
	
	/* 상품 등록 */
	@PostMapping("/goodsEnroll")
	public String goodsEnrollPOST(BookVO book, RedirectAttributes rttr) {
		
		System.out.println("goodsEnroll ::::::: " + book);
		logger.info("goodsEnrollPOST......" + book);
		
		adminService.bookEnroll(book);
		
		rttr.addFlashAttribute("enroll_result", book.getBookName());
		
		return "redirect:/admin/goodsManage";
	}
	
	/* 작가 검색 팝업창 */
	@GetMapping("/authorPop")
	public void authorPopGET(Criteria cri, Model model) throws Exception{
		
		logger.info("authorPopGET.......");
		
		cri.setAmount(3);
		
		/* 게시물 출력 데이터 */
		List list = authorService.authorGetList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list",list);	// 작가 존재 경우
		} else {
			model.addAttribute("listCheck", "empty");	// 작가 존재하지 않을 경우
		}
		
		System.out.println("authorPopGET ::::::: " + list);
		
		
		/* 페이지 이동 인터페이스 데이터 */
		model.addAttribute("pageMaker", new PageDTO(cri, authorService.authorGetTotal(cri)));	

	
	}
	
	/* 상품 정보 삭제 */
	@PostMapping("/goodsDelete")
	public String goodsDeletePOST(int bookId, RedirectAttributes rttr) {
		
		logger.info("goodsDeletePOST..........");
		
		int result = adminService.goodsDelete(bookId);
		
		rttr.addFlashAttribute("delete_result", result);
		
		return "redirect:/admin/goodsManage";
		
	}
	
	/* 작가 정보 삭제 */
	@PostMapping("/authorDelete")
	public String authorDeletePOST(int authorId, RedirectAttributes rttr) {
		
		logger.info("authorDeletePOST..........");
		
		int result = 0;
		
		try {
			
			result = authorService.authorDelete(authorId);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			result = 2;
			rttr.addFlashAttribute("delete_result", result);
			
			return "redirect:/admin/authorManage";
			
		}
		
		
		rttr.addFlashAttribute("delete_result", result);
		
		return "redirect:/admin/authorManage";
		
	}
	
	/* 첨부 파일 업로드 */
	@PostMapping(value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AttachImageVO>> uploadAjaxActionPOST(MultipartFile[] uploadFile) {
		
		logger.info("uploadAjaxActionPOST..........");
		System.out.println("uploadAjaxActionPOST..........");
		
		/* 이미지 파일 체크 */
		for(MultipartFile multipartFile: uploadFile) {
			
			File checkfile = new File(multipartFile.getOriginalFilename());
			String type = null;
			
			try {
				type = Files.probeContentType(checkfile.toPath());
				logger.info("MIME TYPE : " + type);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(!type.startsWith("image")) {
				
				List<AttachImageVO> list = null;
				return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
				
			}
			
		}// for	
		
		String uploadFolder = "/Users/peter/workspace/spring_workspace/image_folder";
		
		/* 날짜 폴더 경로 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		
		String datePath = str.replace("-", File.separator);
		
		/* 폴더 생성 */
		File uploadPath = new File(uploadFolder, datePath);
		
		System.out.println("uploadPath : " + uploadPath);
		
		if(uploadPath.exists() == false) {
			System.out.println("uploadPath doesn't exist");
			uploadPath.mkdirs();
		}
		
		/* 이미저 정보 담는 객체 */
		List<AttachImageVO> list = new ArrayList();
		
		
		// 향상된 for
		for(MultipartFile multipartFile : uploadFile) {
			
			/* 이미지 정보 객체 */
			AttachImageVO vo = new AttachImageVO();
			
			/* 파일 이름 */
			String uploadFileName = multipartFile.getOriginalFilename();
			vo.setFileName(uploadFileName);
			vo.setUploadPath(datePath);
			
			/* uuid 적용 파일 이름 */
			String uuid = UUID.randomUUID().toString();
			vo.setUuid(uuid);
			
			uploadFileName = uuid + "_" + uploadFileName;
			
			/* 파일 위치, 파일 이름을 합친 File 객체 */
			File saveFile = new File(uploadPath, uploadFileName);
			
			/* 파일 저장 */
			try {
				System.out.println("saveFile : " + saveFile);
				multipartFile.transferTo(saveFile);
				
				/* 썸네일 생성(ImageIO) */
//				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);
//				
//				BufferedImage bo_image = ImageIO.read(saveFile);
//				BufferedImage bt_image = new BufferedImage(300, 500, BufferedImage.TYPE_3BYTE_BGR);
//								
//				Graphics2D graphic = bt_image.createGraphics();
//				
//				graphic.drawImage(bo_image, 0, 0,300,500, null);
//					
//				ImageIO.write(bt_image, "jpg", thumbnailFile);
				
				/* 방법 2 */
				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);	
				
				BufferedImage bo_image = ImageIO.read(saveFile);

				//비율 
				double ratio = 3;
				//넓이 높이
				int width = (int) (bo_image.getWidth() / ratio);
				int height = (int) (bo_image.getHeight() / ratio);					
				
				
				Thumbnails.of(saveFile)
		        .size(width, height)
		        .toFile(thumbnailFile);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			list.add(vo);
			
		} //for
		
		ResponseEntity<List<AttachImageVO>> result = new ResponseEntity<List<AttachImageVO>>(list, HttpStatus.OK);
		
		return result;

	}
	
	
}
