package com.sneakerShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sneakerShop.dao.HoaDonDAO;
import com.sneakerShop.daoimp.HoaDonImpl;
import com.sneakerShop.entity.HoaDon;

@Service
public class HoaDonService implements HoaDonImpl{

	@Autowired
	HoaDonDAO hoaDonDAO;
	
	@Override
	public int ThemHoaDon(HoaDon hoaDon) {
		return  hoaDonDAO.ThemHoaDon(hoaDon);
		
	}

}
