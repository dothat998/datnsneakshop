<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="header.jsp" />
<%-- <link rel="stylesheet" href='<c:url value="/resources/Style/styleChitiet.css"/>' /> --%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Real Sneakers Shop</title>
</head>
<body>

	<div id="header-chitiet" class="container-fluid">
		<nav class="navbar navbar-default none_nav wow rubberBand">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="http://localhost:8080/sneakerShop/"><img
					src='<c:url value="/resources/Image/balen.jpg"/>'
					style="width: 50px"></a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div id="header-chitiet" class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-center">
					<li class="active"><a href="/sneakerShop/">TRANG CHỦ</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Sản phẩm <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<c:forEach var="value" items="${danhmuc }">
								<li><a
									href='<c:url value="/sanpham/${value.getMadanhmuc()}/${value.getTendanhmuc() }"/>'>${value.getTendanhmuc() }</a></li>
								<li role="separator" class="divider"></li>
							</c:forEach>

						</ul></li>
					<li><a href="https://bitly.com.vn/uubbux" target="_blank">DỊCH
							VỤ</a></li>
					<li><a href="https://www.facebook.com/DoThat98/"
						target="_blank">LIÊN HỆ</a></li>

				</ul>

				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${chucaidau != null }">
							<li><a class="circle-avartar" href="#"><span>${chucaidau }</span></a></li>
						</c:when>

						<c:otherwise>
							<li><a href='<c:url value="/dangnhap/"/>'>ĐĂNG NHẬP</a></li>
						</c:otherwise>
					</c:choose>

					<li id="giohang"><a href='<c:url value="/giohang/"/>' ><img
							src='<c:url value="/resources/Image/ic_shopping_cart_white_24dp_1x.png"/>'>
							<c:if test="${soluongsanphamgiohang > 0 }">
								<div class="circle-giohang">
									<span>${soluongsanphamgiohang }</span>
								</div>
							</c:if> <c:if
								test="${soluongsanphamgiohang <= 0 || soluongsanphamgiohang == null  }">
								<div>
									<span>${soluongsanphamgiohang }</span>
								</div>
							</c:if> </a></li>


				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid --> </nav>
	</div>

	<div class="container">
		<div class="row" style="margin-top: 16px">

			<div class="col-sm-2 col-md-2">
				<h3>Danh mục</h3>
				'
				<ul class="mymenu">
					<c:forEach var="value" items="${danhmuc }">
						<li><a href="#">${value.getTendanhmuc() }</a></li>
					</c:forEach>
				</ul>
			</div>

			<div class="col-sm-8 col-md-8">
				<div class="row">
					<div class="col-sm-4 col-md-4">
						<img
							src='<c:url value="/resources/Image/sanpham/${sanpham.getHinhsanpham() }"/>' />
					</div>


					<div class="col-sm-8 col-md-8">
						<h3 id="tensp" data-masp="${sanpham.getMasanpham() }">${sanpham.getTensanpham() }</h3>
						<h4 id="giatien" data-value="${sanpham.getGiatien() }"
							style="color: red">${sanpham.getGiatien() }VND</h4>

						<table class="table">
							<thead>
								<tr>
									<td><h5>Màu sản phẩm</h5></td>
									<td><h5>Size</h5></td>
									<td><h5>Số lượng</h5></td>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="chitietsanpham"
									items="${sanpham.getChitietsanpham() }">
									<tr>
										<td class="mau"
											data-mamau="${chitietsanpham.getMausanpham().getMamau() }">${chitietsanpham.getMausanpham().getTenmau() }</td>
										<td class="size"
											data-masize="${chitietsanpham.getSizesanpham().getMasize() }">${chitietsanpham.getSizesanpham().getSize() }</td>
										<td class="soluong">${chitietsanpham.getSoluong() }</td>
										<td><button
												data-machitiet="${chitietsanpham.getMachitietsanpham()}"
												class="btn btn-success btn-giohang">BUY</button></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-sm-2 col-md-2">
				<h3>MÔ TẢ</h3> </br>
				<span>${sanpham.getMota()}</span>
			</div>
		</div>
	</div>
	<div id="footer" class="container-fluid">
		<div class="row">
			<div class="col-sm-4 col-md-4 wow tada">
				<p>
					<span class="title-footer">THÔNG TIN SHOP</span>
				</p>
				<span>Real Sneaker là một thương hiệu thời trang đầy uy tín,
					luôn đảm bảo chất lượng sản phẩm tốt nhất cho khách hàng.</span>
			</div>

			<div class="col-sm-4 col-md-4 wow tada">
				<p>
					<span class="title-footer">LIÊN HỆ</span>
				</p>
				<span>116 Phố Viên - Đức Thắng - Bắc Từ Liêm TP. Hà Nội</span> <span>dothat998@gmail.com</span>
				<span>0968758298</span>
			</div>

			<div class="col-sm-4 col-md-4 wow tada">
				<p>
					<span class="title-footer">GÓP Ý</span>
				</p>
				<form action="" method="post">
					<input name="tenNhanVien" class="material-textinput"
						style="margin-bottom: 8px" type="text" placeholder="Email" />
					<textarea name="tuoi" style="margin-bottom: 8px" rows="4" cols="50"
						placeholder="Nội dung"></textarea>
					<button class="material-primary-button">ĐỒNG Ý</button>
				</form>

			</div>
		</div>

	</div>

	<jsp:include page="footer.jsp" />
</body>


</html>