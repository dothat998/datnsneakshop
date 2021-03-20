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
@RequestMapping("/chitiet")
@SessionAttributes("giohang")
public class ChiTietController {
	
	@Autowired
	SanPhamService sanPhamService;
	
	@Autowired
	DanhMucService danhMucService;
	
	@GetMapping("/{masanpham}")
	@Transactional
	public String Default(@PathVariable int masanpham, ModelMap map,HttpSession httpSession) {
		SanPham sanpham = sanPhamService.LayDanhSachChiTietSanPhamTheoMa(masanpham);
		List<DanhMucSanPham> danhMucSanPhams = danhMucService.LayDanhMuc();
		if(null != httpSession.getAttribute("giohang")){
			@SuppressWarnings("unchecked")
			List<GioHang> gioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
			map.addAttribute("soluongsanphamgiohang",gioHangs.size());
		}
		map.addAttribute("sanpham",sanpham);
		map.addAttribute("danhmuc",danhMucSanPhams);
		/*-----------------*/
		if(httpSession.getAttribute("user") != null){
			String email = (String) httpSession.getAttribute("user");
			String chucaidau = email.substring(0, 1);
			map.addAttribute("chucaidau", chucaidau);
		}
		
		
		return "chitiet";
	}
	
	
}
