package com.sneakerShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sneakerShop.dao.AdminSanPhamDAO;
import com.sneakerShop.dao.SanPhamDAO;
import com.sneakerShop.daoimp.AdminSanPhamImpl;
import com.sneakerShop.entity.SanPham;

@Service
public class AdminSanPhamService implements AdminSanPhamImpl{
	@Autowired
	AdminSanPhamDAO AdminsanPhamDAO;
	
	@Override
	public List<SanPham> LayDanhSachSanPhamLimit(int spbatdau) {
		// TODO Auto-generated method stub
		return AdminsanPhamDAO.LayDanhSachSanPhamLimit(spbatdau);
	}

}
