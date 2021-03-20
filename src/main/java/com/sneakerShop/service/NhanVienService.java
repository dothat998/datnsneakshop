package com.sneakerShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sneakerShop.dao.NhanVienDAO;
import com.sneakerShop.daoimp.NhanVienImpl;
import com.sneakerShop.entity.NhanVien;

@Service
public class NhanVienService implements NhanVienImpl {
	
	@Autowired
	NhanVienDAO nhanvienDao;
	@Override
	public boolean KiemTraDangNhap(String email, String matkhau) {
		// TODO Auto-generated method stub
		boolean kiemtra =  nhanvienDao.KiemTraDangNhap(email, matkhau);
		return kiemtra;
	}
	@Override
	public boolean ThemNhanVien(NhanVien nhanVien) {
		
		boolean ktThem = nhanvienDao.ThemNhanVien(nhanVien);
		return ktThem;
	}
}
