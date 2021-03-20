package com.sneakerShop.daoimp;

import java.util.List;

import com.sneakerShop.entity.SanPham;

public interface AdminSanPhamImpl {
	List<SanPham> LayDanhSachSanPhamLimit(int spbatdau);
}
