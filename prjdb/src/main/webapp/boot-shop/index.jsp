<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<title>Gamsung store</title>
	<!-- Favicon-->
	<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
	<!-- Bootstrap icons-->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
	<!-- Core theme CSS (includes Bootstrap)-->
	<link href="boot-shop/css/styles.css" rel="stylesheet" />
	<link href="boot-shop/css/gamsung.css" rel="stylesheet" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>

<body>
	<div>
		<!-- 네비게이션 start -->
		<jsp:include page="menu/menu.jsp" />
		<!-- 네비게이션 end -->
	</div>

	<!-- 헤더 start -->
	<header class="bg-dark py-5 main-banner">
		<div class="image-box__background"></div>
		<div class="image-box__overlay"></div>
		<div class="container px-4 px-lg-5 my-5 image-box__content">
			<div class="text-center text-white">
				<h1 class="display-4 fw-bolder">GamSung Shop</h1>
				<p class="lead fw-normal text-white-50 mb-0">간직하고픈 순간의 향기를 담은..</p>
			</div>
		</div>
	</header>
	<!-- 헤더 end -->
	
	<!-- 상품 리스트 영역 start-->
	<section class="py-5">
		<div class="container px-4 px-lg-5 mt-5">
			<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-start">
				<c:choose>
					<c:when test="${empty products }">
						<p>준비된 상품이 없습니다.</p>
					</c:when>
					<c:otherwise>
						<c:forEach items="${products }" var="p">
							<div class="col mb-5">
								<div class="card h-100">
									<c:if test="${p.itemSaleRate > 0}">
										<!-- 세일 표시 -->
										<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">
											Sale</div>
									</c:if>
									<!-- 상품 이미지 -->
									<img class="card-img-top" src="boot-shop/img/${p.itemImage }"
										alt="..." />
									<!-- 상품 정보 -->
									<div class="card-body p-4">
										<div class="text-center">
											<!-- 상품 이름  itemDetail.do -->
											<h5 class="fw-bolder"><span onclick="itemDetail(${p.itemId})">${p.itemName }</span></h5>
											<!-- 상품 평점 -->
											<div class="d-flex justify-content-center small text-warning mb-2">
												<c:forEach var="i" begin="1" end="${p.itemStar}">
													<div class="bi-star-fill"></div>
												</c:forEach>
												<!-- <div class="bi-star-half"></div> -->
											</div>
											<!-- 상품 가격-->
											<span class="text-muted <c:if test="${0 ne p.itemSaleRate }">text-decoration-line-through</c:if>">
												<fmt:formatNumber value="${p.itemPrice }" type="currency" currencySymbol="￦"/>
											</span>
											<c:if test="${p.itemSaleRate ne 0 }">
											<span>
												<fmt:formatNumber value="${(p.itemSaleRate*0.01)* p.itemPrice}" type="currency" currencySymbol="￦"/>
											</span>
											</c:if>
										</div>
									</div>
									<!-- 상품 장바구니 -->
									<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
										<div class="text-center">
											<a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</section>
	<form id="itemform" action="productItem.do" method="post">
		<input type="hidden" id="itemId" name="itemId">
	</form>
	
	<!-- 상품 리스트 영역 end-->
	
	<!-- 푸터 start-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Yedam Gamsung by 륜 & 진</p>
		</div>
	</footer>
	<!-- 푸터 end-->
	
	<!-- Bootstrap core JS-->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
	
	<script type="text/javascript">
		function itemDetail(id) {
			let form = document.getElementById("itemform");
			form.itemId.value = id;
			form.submit(); //itemDetail.do로 이동
		}
	</script>
</body>

</html>