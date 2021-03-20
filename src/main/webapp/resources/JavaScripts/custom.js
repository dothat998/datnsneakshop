$(document).ready(function() {
	var masanpham = 0;

	$("#cot1").click(function() {
		var chuoi = $("#cot1").attr("data-text");
		alert(chuoi);
	});

	$("#btnDangNhap").click(function() {
		var ten = $("#email").val();
		var password = $("#matkhau").val();

		$.ajax({
			url : "/sneakerShop/api/KiemTraDangNhap",
			type : "GET",
			data : {
				email : ten,
				matkhau : password
			},
			success : function(value) {
				if (value == "true") {
					$("#ketqua").text("dang nhap thanh cong !")
					duongDanHienTai = window.location.href;
					duongDan = duongDanHienTai.replace("dangnhap/", "");
					window.location = duongDan;

				} else {
					$("#ketqua").text("dang nhap that bai !")
				}

			}
		})

	});

	$("#dangnhap").click(function() {
		$(this).addClass("actived");
		$("#dangky").removeClass("actived");
		$(".container-login-form").show();
		$(".container-signup-form").css("display", "none");
	});

	$("#dangky").click(function() {
		$(this).addClass("actived");
		$("#dangnhap").removeClass("actived");
		$(".container-login-form").hide();
		$(".container-signup-form").show();
	});

	$(".btn-giohang").click(function(){
		var machitiet = $(this).attr("data-machitiet");
		var mamau = $(this).closest("tr").find(".mau").attr("data-mamau");
		var tenmau = $(this).closest("tr").find(".mau").text();
		var masize = $(this).closest("tr").find(".size").attr("data-masize");
		var tensize = $(this).closest("tr").find(".size").text();
		var soluong = $(this).closest("tr").find(".soluong").text();
		var tensp = $("#tensp").text();
		var masp = $("#tensp").attr("data-masp");
		var giatien = $("#giatien").attr("data-value");
		
		
		$.ajax({
			url:"/sneakerShop/api/ThemGioHang",
		    type:"GET",
		    data:{
		    	masp:masp,
		    	masize:masize,
		    	mamau:mamau,
		    	tensp: tensp,
		    	giatien:giatien,
		    	tenmau:tenmau,
		    	tensize:tensize,
		    	soluong:soluong,
		    	machitiet:machitiet
		    	
		    },
			success: function(value){
				$("#giohang").find("div").addClass("circle-giohang");
				$("#giohang").find("div").html("<span>" + value + "</span>") ;
			}
		})    
//		}).done(function(){
//			$.ajax({
//				url:"/sneakerShop/api/LaySoLuongGioHang",
//			    type:"GET",
//				success: function(value){
//					$("#giohang").find("div").addClass("circle-giohang");
//					$("#giohang").find("div").html("<span>" + value + "</span>") ;
//				}
//			})
//		});
	});
	
	GanTongTienGioHang();
	
	function GanTongTienGioHang(isEventChange){
		var tongtiensp = 0;	
		$(".giatien").each(function(){
			var giatien = $(this).text();
			var soluong = $(this).closest("tr").find(".soluong-giohang").val();
			
			var tongtien = parseInt(giatien) * soluong;
			var format = parseFloat(tongtien).toFixed(3).replace(/(\d)(?=(\d{3})+\.)/g, "$1.")
			
			if(!isEventChange){
				$(this).html(format);
			}
			tongtiensp = tongtiensp +  tongtien
			var formatTongtien = tongtiensp.toFixed(3).replace(/(\d)(?=(\d{3})+\.)/g, "$1.").toString()
			$("#tongtien").html(formatTongtien+"")
			
		})
	}
	
	$(".xoa-giohang").click(function(){
		var self = $(this);
		var mamau = $(this).closest("tr").find(".mau").attr("data-mamau");
		var masize = $(this).closest("tr").find(".size").attr("data-masize");
		var masp = $(this).closest("tr").find(".tensp").attr("data-masp");
		
		$.ajax({
			url:"/sneakerShop/api/XoaGioHang",
		    type:"GET",
		    data:{
		    	masp:masp,
		    	masize:masize,
		    	mamau:mamau,
		    	
		    },
			success: function(value){
				self.closest("tr").remove();
				GanTongTienGioHang(true);
			}
		})   
	})
	
	$(".soluong-giohang").change(function(){
		var soluong = $(this).val();
		var giatien = $(this).closest("tr").find(".giatien").attr("data-value");
		
		var tongtien = soluong * parseInt(giatien);
		var format = tongtien.toFixed(3).replace(/(\d)(?=(\d{3})+\.)/g, "$1.").toString()
		$(this).closest("tr").find(".giatien").html(format);
		GanTongTienGioHang(true);
		
		var mamau = $(this).closest("tr").find(".mau").attr("data-mamau");
		var masize = $(this).closest("tr").find(".size").attr("data-masize");
		var masp = $(this).closest("tr").find(".tensp").attr("data-masp");
		
		$.ajax({
			url:"/sneakerShop/api/CapNhatGioHang",
		    type:"GET",
		    data:{
		    	masp:masp,
		    	masize:masize,
		    	mamau:mamau,
		    	soluong:soluong
		    	
		    },
			success: function(value){
				
			}
		})    
	})
	$("body").on("click",".paging-item",function(){
		$(".paging-item").removeClass("active");
		$(this).addClass("active");
		var sotrang = $(this).text();
		var spbatdau = (sotrang - 1) * 6;
		
		$.ajax({
			url:"/sneakerShop/api/AdminLaySanPhamLimit",
		    type:"GET",
		    data:{
		    	spbatdau:spbatdau,
		    	
		    },
			success: function(value){
				var tbodysanpham = $("#table-sanpham").find("tbody");
				tbodysanpham.empty();
				tbodysanpham.append(value);
			}
		})
	})
	
	$("#checkall").change(function(){
		if(this.checked){
			$("#table-sanpham input").each(function(){
				$(this).attr("checked",true);
			})
		}else{
			$("#table-sanpham input").each(function(){
				$(this).attr("checked",false);
			})
		}
	})
	$("#xoa-sanpham").click(function(){
		$("#table-sanpham > tbody input:checked").each(function(){
			var masanpham = $(this).val();
			var This = $(this);
			This.closest("tr").remove();
			$.ajax({
				url:"/sneakerShop/api/XoaSanPham",
			    type:"GET",
			    data:{
			    	masp:masanpham,
			    	
			    },
				success: function(value){
					This.closest("tr").remove();
				}
			})
		})
	})
	
})