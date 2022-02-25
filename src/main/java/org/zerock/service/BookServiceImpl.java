package org.zerock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.mapper.AdminMapper;
import org.zerock.mapper.AttachMapper;
import org.zerock.mapper.BookMapper;
import org.zerock.model.AttachImageVO;
import org.zerock.model.BookVO;
import org.zerock.model.CateFilterDTO;
import org.zerock.model.CateVO;
import org.zerock.model.Criteria;
import org.zerock.model.SelectDTO;


@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
	@Autowired
	private AdminMapper adminMapper;
	
	/* 상품 검색 */
	@Override
	public List<BookVO> getGoodsList(Criteria cri) {
		
		System.out.println("getGoodsList().......");
		
		String type = cri.getType();
		String[] typeArr = type.split("");
		String[] authorArr = bookMapper.getAuthorIdList(cri.getKeyword());
		
		
		if(type.equals("A") || type.equals("AC") || type.equals("AT") || type.equals("ACT")) {
			if(authorArr.length == 0) {
				return new ArrayList();
			}
		}
		
		for(String t : typeArr) {
			if(t.equals("A")) {
				cri.setAuthorArr(authorArr);
			}
		}		
		
		List<BookVO> list = bookMapper.getGoodsList(cri);
		
		list.forEach(book -> {
			
			int bookId = book.getBookId();
			
			List<AttachImageVO> imageList = attachMapper.getAttachList(bookId);
			
			book.setImageList(imageList);
			
		});
		
		return list;
	}

	/* 사품 총 갯수 */
	@Override
	public int goodsGetTotal(Criteria cri) {
		
		System.out.println("goodsGetTotal().......");
		
		return bookMapper.goodsGetTotal(cri);
	}
	
	/* 국내 카테고리 리스트 */
	@Override
	public List<CateVO> getCateCode1() {
		
		System.out.println("getCateCode1().......");
		
		return bookMapper.getCateCode1();
	}

	/* 외국 카테고리 리스트 */
	@Override
	public List<CateVO> getCateCode2() {
		
		System.out.println("getCateCode2().......");
		
		return bookMapper.getCateCode2();
	}
	
	/* 검색결과 카테고리 필터 정보 */
	@Override
	public List<CateFilterDTO> getCateInfoList(Criteria cri) {

		List<CateFilterDTO> filterInfoList = new ArrayList<CateFilterDTO>();
		
		String[] typeArr = cri.getType().split("");
		String [] authorArr;
		
		for(String type : typeArr) {
			if(type.equals("A")){
				authorArr = bookMapper.getAuthorIdList(cri.getKeyword());
				if(authorArr.length == 0) {
					return filterInfoList;
				}
				cri.setAuthorArr(authorArr);
			}
		}
		
		String[] cateList = bookMapper.getCateList(cri);
		
		String tempCateCode = cri.getCateCode();
		
		for(String cateCode : cateList) {
			cri.setCateCode(cateCode);
			CateFilterDTO filterInfo = bookMapper.getCateInfo(cri);
			filterInfoList.add(filterInfo);
		}
		
		cri.setCateCode(tempCateCode);
		
		return filterInfoList;
	}
	
	@Override
	public BookVO getGoodsInfo(int bookId) {
		
		System.out.println("getGoodsInfo().......");
		
		BookVO goodsInfo = bookMapper.getGoodsInfo(bookId);
		System.out.println("goodsInfo : " + goodsInfo);
		goodsInfo.setImageList(adminMapper.getAttachInfo(bookId));

		
		return goodsInfo;
	}
	
	@Override
	public BookVO getBookIdName(int bookId) {
		
		return bookMapper.getBookIdName(bookId);
	}
	
	@Override
	public List<SelectDTO> likeSelect() {
		
		List<SelectDTO> list = bookMapper.likeSelect();
		
		list.forEach(dto -> {
			
			int bookId = dto.getBookId();
			
			List<AttachImageVO> imageList = attachMapper.getAttachList(bookId);
			
			dto.setImageList(imageList);
			
		});				
		
		return list;	
	}
	
}
