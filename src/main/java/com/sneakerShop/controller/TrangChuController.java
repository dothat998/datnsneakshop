package com.sneakerShop.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sneakerShop.entity.DanhMucSanPham;
import com.sneakerShop.entity.GioHang;
import com.sneakerShop.entity.SanPham;
import com.sneakerShop.service.DanhMucService;
import com.sneakerShop.service.SanPhamService;

@Controller
@RequestMapping("/")
@SessionAttributes("giohang")
public class TrangChuController {
	
	@Autowired 
	SanPhamService sanPhamService;
	@Autowired
	DanhMucService danhMucService;
	
	@GetMapping
	@Transactional
	public String Default(ModelMap modelMap,HttpSession httpSession){
		
		
		if(httpSession.getAttribute("user") != null){
			String email = (String) httpSession.getAttribute("user");
			String chucaidau = email.substring(0, 1);
			modelMap.addAttribute("chucaidau", chucaidau);
		}
		List<SanPham> listSanPhams = sanPhamService.LayDanhSachSanPhamLimit(0);
		modelMap.addAttribute("listSanPham", listSanPhams);
		List<DanhMucSanPham> danhMucSanPhams = danhMucService.LayDanhMuc();
		modelMap.addAttribute("danhmuc",danhMucSanPhams);
		

		/*--------------------*/
		if(null != httpSession.getAttribute("giohang")){
			@SuppressWarnings("unchecked")
			List<GioHang> gioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
			modelMap.addAttribute("soluongsanphamgiohang",gioHangs.size());
		}
		 
		return "trangchu";
	}
}
