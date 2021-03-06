package com.sneakerShop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sneakerShop.entity.GioHang;
import com.sneakerShop.entity.SanPham;
import com.sneakerShop.service.AdminSanPhamService;
import com.sneakerShop.service.NhanVienService;
import com.sneakerShop.service.SanPhamService;

@Controller
@RequestMapping("api/")
@SessionAttributes({ "user", "giohang" })
public class ApiController {

	@Autowired
	NhanVienService nhanVienService;

	@Autowired
	SanPhamService sanPhamService;
	
	@Autowired 
	AdminSanPhamService adminSanPhamService;

	@GetMapping("KiemTraDangNhap")
	@ResponseBody
	public String KiemTraDangNhap(@RequestParam String email, @RequestParam String matkhau, ModelMap modelMap) {

		boolean kiemtra = nhanVienService.KiemTraDangNhap(email, matkhau);
		modelMap.addAttribute("user", email);

		return "" + kiemtra;

	}
	//------------------------------//
	
	@GetMapping("CapNhatGioHang")
	@ResponseBody
	public void CatNhatGioHang(HttpSession httpSession,@RequestParam int soluong ,@RequestParam int masp, @RequestParam int mamau,@RequestParam int masize){
		
		if(null != httpSession.getAttribute("giohang") ){
			List<GioHang> listGioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
			int vitri = KiemTraSanPhamDaTonTaiGioHang(listGioHangs, httpSession, masp, masize, mamau);
			listGioHangs.get(vitri).setSoluong(soluong);
		}
	}
	
	//------------------------------//
	@GetMapping("XoaGioHang")
	@ResponseBody
	public void XoaGioHang(HttpSession httpSession,@RequestParam int masp, @RequestParam int mamau,@RequestParam int masize){
		if(null != httpSession.getAttribute("giohang") ){
			List<GioHang> listGioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
			int vitri = KiemTraSanPhamDaTonTaiGioHang(listGioHangs, httpSession, masp, masize, mamau);
			listGioHangs.remove(vitri);
		}
	}
	//--------------------------------//
	
	@GetMapping("ThemGioHang")
	@ResponseBody
	public String ThemGioHang(@RequestParam int masp,@RequestParam int machitiet, @RequestParam int masize, @RequestParam int mamau,
			@RequestParam String tensp, @RequestParam String giatien, @RequestParam String tenmau,
			@RequestParam String tensize, @RequestParam int soluong, 
			HttpSession httpSession) {

		//ki???m tra session gi??? h??ng c?? t???n t???i hay k
		//n???u k => arrayList add t??t c??? th??ng s??? c???a gio hang
		if (null == httpSession.getAttribute("giohang")) {
			List<GioHang> gioHangs = new ArrayList<>();
			GioHang gioHang = new GioHang();
			gioHang.setMasp(masp);
			gioHang.setMasize(masize);
			gioHang.setMamau(mamau);
			gioHang.setTensp(tensp);
			gioHang.setGiatien(giatien);
			gioHang.setTenmau(tenmau);
			gioHang.setTensize(tensize);
			gioHang.setSoluong(1);
			gioHang.setMachitiet(machitiet);
			
			gioHangs.add(gioHang);
			httpSession.setAttribute("giohang", gioHangs);

			return gioHangs.size() + "";

		}
		//th??m san pham vao gio h??ng l???n th??? 2.Ki???m tra xem san pham them t???n t???i ch??a
		else {
			List<GioHang> listGioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
			int vitri = KiemTraSanPhamDaTonTaiGioHang(listGioHangs, httpSession, masp, masize, mamau);
			//san pham khong co trong cart th?? se them moi
			if (vitri == -1) {
				GioHang gioHang = new GioHang();
				gioHang.setMasp(masp);
				gioHang.setMasize(masize);
				gioHang.setMamau(mamau);
				gioHang.setTensp(tensp);
				gioHang.setGiatien(giatien);
				gioHang.setTenmau(tenmau);
				gioHang.setTensize(tensize);
				gioHang.setSoluong(1);
				gioHang.setMachitiet(machitiet);

				listGioHangs.add(gioHang);
			} else {
				//c???p nh???p th??m slg c???a san pham da co, v?? b??? tr??ng trong list
				int soluongmoi = listGioHangs.get(vitri).getSoluong() + 1;
				listGioHangs.get(vitri).setSoluong(soluongmoi);
			}

			return listGioHangs.size() + "";
		}
	}

	//l???y list gio hang ra so s??nh vs 3 tham s??? xem t???n t???i k.==>c?? th??? ra v??? tr?? s???n ph???m b??? tr??ng trong list
	private int KiemTraSanPhamDaTonTaiGioHang(List<GioHang> listGioHangs, HttpSession httpSession, int masp, int masize,
			int mamau) {
		for (int i = 0; i < listGioHangs.size(); i++) {
			if (listGioHangs.get(i).getMasp() == masp && listGioHangs.get(i).getMasize() == masize
					&& listGioHangs.get(i).getMamau() == mamau) {
				return i;
			}
		}
		return -1;

	}
	
	@GetMapping(path="AdminLaySanPhamLimit", produces="text/plain; charset=utf-8")
	@ResponseBody
	public String LaySanPhamLimit(@RequestParam int spbatdau){
		
		String html = "";
		
		List<SanPham> listSanPhams = adminSanPhamService.LayDanhSachSanPhamLimit(spbatdau);
		for (SanPham sanPham : listSanPhams) {
			
			html += "<tr>";
			html += "<td><div class='checkbox'><label><input class='checkboxsanpham' type='checkbox' value='"+sanPham.getMasanpham()+"'></label></div></td>";
			html += "<td class='tensp' data-masp='" + sanPham.getMasanpham() +"'>" + sanPham.getTensanpham() +"</td>";
			html += "<td class='giatien' >" +sanPham.getGiatien()  +"</td>";
			html += "<td class='gianhcho'>"+sanPham.getGianhcho() +"</td>";
			html += "<td style='color:white' class='btn btn-warning capnhatsanpham' data-id=" + sanPham.getMasanpham() + ">S???a</td>";
			html += "</tr>";
		}
		
		
		return html;
		
	}
	@GetMapping("XoaSanPham")
	@ResponseBody
	public String XoaSanPhamTheoMaSanPham(@RequestParam int masp){
		sanPhamService.XoaSanPhamTheoMaSanPham(masp);
		return "true";
	}
	
}
