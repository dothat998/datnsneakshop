package com.sneakerShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sneakerShop.dao.DanhMucDAO;
import com.sneakerShop.daoimp.DanhMucImpl;
import com.sneakerShop.entity.DanhMucSanPham;

@Service
public class DanhMucService implements DanhMucImpl{

	@Autowired
	DanhMucDAO danhMucDAO;
	@Override
	public List<DanhMucSanPham> LayDanhMuc() {
		return danhMucDAO.LayDanhMuc();
	}
	
}
