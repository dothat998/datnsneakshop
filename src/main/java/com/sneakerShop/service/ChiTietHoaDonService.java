package com.sneakerShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sneakerShop.dao.ChiTietHoaDonDAO;
import com.sneakerShop.daoimp.ChiTietHoaDonImpl;
import com.sneakerShop.entity.ChiTietHoaDon;

@Service
public class ChiTietHoaDonService implements ChiTietHoaDonImpl{
	@Autowired
	ChiTietHoaDonDAO chiTietHoaDonDAO;
	@Override
	public boolean ThemChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		return chiTietHoaDonDAO.ThemChiTietHoaDon(chiTietHoaDon);
	}
}
