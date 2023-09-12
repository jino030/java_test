<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Gamsung store-${p.itemName }</title>
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
		
        <!-- 상품 디테일 -->
        <section class="py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="row gx-4 gx-lg-5 align-items-center">
                    <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" src="boot-shop/img/${p.itemImage }" alt="..." /></div>
                    <div class="col-md-6">
                        <div class="small mb-1">상품코드 : ${p.itemId }</div>
                        <h1 class="display-5 fw-bolder">${p.itemName }</h1>
                        <div class="fs-5 mb-5">
                            <span class="text-muted <c:if test="${0 ne p.itemSaleRate }">text-decoration-line-through</c:if>">
								<fmt:formatNumber value="${p.itemPrice }" type="currency" currencySymbol="￦"/>
							</span>
							<c:if test="${p.itemSaleRate ne 0 }">
							<span>
								<fmt:formatNumber value="${(p.itemSaleRate*0.01)* p.itemPrice}" type="currency" currencySymbol="￦"/>
							</span>
							</c:if>
                        </div>
                        <p class="lead">${p.itemContent }</p>
                        <div class="d-flex">
                            <input class="form-control text-center me-3" id="inputQuantity" type="num" value="1" style="max-width: 3rem" />
                            <button class="btn btn-outline-dark flex-shrink-0" type="button">
                                <i class="bi-cart-fill me-1"></i>
                                Add to cart
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        
        <!-- 별점 높은 4개만.. -->
        <section class="py-5 bg-light">
            <div class="container px-4 px-lg-5 mt-5">
                <h2 class="fw-bolder mb-4">Related products</h2>
                <div class="myRow row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                    <!-- 상품한개.. -->
                    <div class="col mb-5 myCol" style="display:none;">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder item-name">Fancy Product</h5>
                                    <!-- Product price-->
                                    <span class="item-price">$40.00 - $80.00</span>
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">View options</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        
        <script type="text/javascript">
        
        	$.ajax({
        		url: 'AjaxProductItem.do',
        		method: 'post',
        		success: function (result) {
					console.log(result);
					
					for(let i=0; i<result.length; i++){
						let clone = $('.myCol:eq(0)').clone();
						let salePrice = result[i].itemPrice * (result[i].itemSaleRate * 0.01);
						
						clone.css('display', 'block');
						clone.find('img').attr('src','boot-shop/img/' + result[i].itemImage);
						clone.find('.item-name').text(result[i].itemName);
						clone.find('.item-price').text(result[i].itemPrice + ' ' + (salePrice==0?'':(' → (할인가)'+salePrice)));
						$('.myRow').append(clone);
					}
					
					console.log(clone);
					
				}
        	})
        
        </script>
    </body>
</html>
