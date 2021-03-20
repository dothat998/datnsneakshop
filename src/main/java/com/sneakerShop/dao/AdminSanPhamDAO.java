package com.sneakerShop.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sneakerShop.daoimp.AdminSanPhamImpl;
import com.sneakerShop.entity.SanPham;
@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AdminSanPhamDAO implements AdminSanPhamImpl{

	@Autowired
	SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<SanPham> LayDanhSachSanPhamLimit(int spbatdau) {
		Session session = sessionFactory.getCurrentSession();
		List<SanPham> listSanPhams = new ArrayList<>();
		if (spbatdau < 0) {
			String query = "from SANPHAM";
			listSanPhams = (List<SanPham>) session.createQuery(query).getResultList();
		} else {
			listSanPhams = (List<SanPham>) session.createQuery("from SANPHAM").setFirstResult(spbatdau).setMaxResults(6)
					.getResultList();
		}

		return listSanPhams;
	}

}
