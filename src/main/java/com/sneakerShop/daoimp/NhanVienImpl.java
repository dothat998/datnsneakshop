package com.sneakerShop.daoimp;

import com.sneakerShop.entity.NhanVien;

public interface NhanVienImpl {
	boolean KiemTraDangNhap(String email,String matkhau);
	boolean ThemNhanVien(NhanVien nhanVien);
}
