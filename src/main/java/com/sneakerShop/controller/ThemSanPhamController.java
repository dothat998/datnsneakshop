package com.sneakerShop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sneakerShop.entity.DanhMucSanPham;
import com.sneakerShop.entity.MauSanPham;
import com.sneakerShop.entity.SanPham;
import com.sneakerShop.entity.SizeSanPham;
import com.sneakerShop.service.DanhMucService;
import com.sneakerShop.service.SanPhamService;

@Controller
@RequestMapping("/themsanpham")
public class ThemSanPhamController {
	@Autowired
	SanPhamService sanPhamService;
	
	@Autowired
	DanhMucService danhMucService;
//	
//	@Autowired
//	MauSanPhamService mauSanPhamService;
//	
//	@Autowired
//	SizeSanPhamService sizeSanPhamService;
	
	@GetMapping
	public String Default(ModelMap modelMap){
		List<SanPham> listSanPhams =  sanPhamService.LayDanhSachSanPhamLimit(0);
		List<SanPham> allSanPham = sanPhamService.LayDanhSachSanPhamLimit(-1);
		List<DanhMucSanPham> danhMucSanPhams = danhMucService.LayDanhMuc();
//		List<MauSanPham> listMauSanPham = mauSanPhamService.LayDanhSachMau();
//		List<SizeSanPham> listSizeSanPham = sizeSanPhamService.LayDanhSachSize();
		
		double tongsopage = Math.ceil((double) allSanPham.size() / 8);
		modelMap.addAttribute("listSanPham", listSanPhams);
		modelMap.addAttribute("allSanPham", allSanPham);
		modelMap.addAttribute("tongsopage", tongsopage);
		modelMap.addAttribute("danhmuc",danhMucSanPhams);
//		modelMap.addAttribute("listsize",listSizeSanPham);
//		modelMap.addAttribute("listmau",listMauSanPham);
		
		return "themsanpham";
	}
}
