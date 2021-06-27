<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<!--
	Forty by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>Forty by HTML5 UP</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
	</head>
	<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Header -->
					<header id="header" class="alt">
						<nav>
							<a href="#menu">Menu</a>
						</nav>
					</header>

				<!-- Menu -->
					<nav id="menu">
						<ul class="links">
							<li><a href="index.html">Home</a></li>
							<li><a href="landing.html">Landing</a></li>
							<li><a href="generic.html">Generic</a></li>
							<li><a href="elements.html">Elements</a></li>
						</ul>
						<ul class="actions stacked">
							<li><a href="#" class="button primary fit">Get Started</a></li>
							<li><a href="#" class="button fit">Log In</a></li>
						</ul>
					</nav>

				<!-- Banner -->
					<section id="banner" class="major">
						<div class="inner">
							<!-- 로그인 안되었을 때  -->
						<c:if test="${empty authInfo }">
						<form action="login.sm" method="get" name="frm">
						<table border = 1>
							<tr><td colspan="3">
								<input type = "checkbox" name="idStore" value="store" 
									<c:if test="${isId !=null }">checked</c:if>
								/>아이디저장 | 
								<input type = "checkbox" name="autologin" value="auto"/>
								자동로그인</td></tr>
							<tr><td>아이디</td>
								<td><input type="text" name="userId" value="${isId }"/><span>${userFail }</span></td>
								<td rowspan="2">
									<input type="image" src="images/img1.jpg" 
									width="60" alt="login" />
								</td></tr>
							<tr><td>비밀번호</td>
								<td><input type="password" name="userPw"/><span>${pwFail }</span></td></tr>
							<tr><td colspan="3">
									<a href="idSearch.mem">아이디</a>/<a href="#">비밀번호 찾기</a> |
									<a href="memAgree.mem">회원가입</a>
								</td></tr>
						</table>
						</form>
					</c:if>
					<c:if test="${!empty authInfo }">
						<!-- 로그인 되었을 때 -->
							<c:if test="${authInfo.grade == 1 }">
							<!-- 일반 회원 -->
								<a href="myPage.mem">마이페이지</a>
								<a href="goodsCartList.gd">장바구니</a>
								<a href="purchaseCon.gd">주문확인</a>
							</c:if>
							<c:if test="${authInfo.grade != 1 }">
								<a href="empMyPage.em">마이페이지</a>
								<!-- 직원 -->
								<a href="goodsList.gd">상품등록</a>
								<a href="#">공지사항</a>
								<a href="empList.em">직원 리스트</a>
								<a href="memList.mem" >회원 리스트</a>
								<a href="venta.vnt" >판매현황</a>
							</c:if>
							<a href="logout.sm">로그아웃</a>
						</c:if>
						<hr />
						<!-- 상품리스트 -->
						<script>
							function goodsBuy(prodNum){
								if(${authInfo == null}){
									alert("로그인을 하셔야 합니다.");
									return false;
								}else{
									location.href='prodInfo.gd?prodNum='+prodNum;
								}
							}
						</script>
						<table align="center">
								<tr>
								<c:forEach items="${lists }" var="dto" varStatus="cnt">
									<td ><a href="javascript:goodsBuy('${dto.prodNum}')">
									<img width="200" height="200" 
									src='goods/upload/${dto.prodImage.split(",")[0] }'><br />
									${dto.prodName }<br />
									가격 : <fmt:formatNumber value="${dto.prodPrice }" 
					type="currency" /></a> </td>
									<c:if test="${cnt.count % 3 == 0 }">
									</tr><tr>
									</c:if>
								</c:forEach>
							</tr>
						</table>
						</div>
					</section>

				<!-- Main -->
					<div id="main">

				<!-- Contact -->
					<section id="contact">
						<div class="inner">
							<section>
								<form method="post" action="#">
									<div class="fields">
										<div class="field half">
											<label for="name">Name</label>
											<input type="text" name="name" id="name" />
										</div>
										<div class="field half">
											<label for="email">Email</label>
											<input type="text" name="email" id="email" />
										</div>
										<div class="field">
											<label for="message">Message</label>
											<textarea name="message" id="message" rows="6"></textarea>
										</div>
									</div>
									<ul class="actions">
										<li><input type="submit" value="Send Message" class="primary" /></li>
										<li><input type="reset" value="Clear" /></li>
									</ul>
								</form>
							</section>
							<section class="split">
								<section>
									<div class="contact-method">
										<span class="icon solid alt fa-envelope"></span>
										<h3>Email</h3>
										<a href="#">information@untitled.tld</a>
									</div>
								</section>
								<section>
									<div class="contact-method">
										<span class="icon solid alt fa-phone"></span>
										<h3>Phone</h3>
										<span>(000) 000-0000 x12387</span>
									</div>
								</section>
								<section>
									<div class="contact-method">
										<span class="icon solid alt fa-home"></span>
										<h3>Address</h3>
										<span>1234 Somewhere Road #5432<br />
										Nashville, TN 00000<br />
										United States of America</span>
									</div>
								</section>
							</section>
						</div>
					</section>

				<!-- Footer -->
					<footer id="footer">
						<div class="inner">
							<ul class="icons">
								<li><a href="#" class="icon brands alt fa-twitter"><span class="label">Twitter</span></a></li>
								<li><a href="#" class="icon brands alt fa-facebook-f"><span class="label">Facebook</span></a></li>
								<li><a href="#" class="icon brands alt fa-instagram"><span class="label">Instagram</span></a></li>
								<li><a href="#" class="icon brands alt fa-github"><span class="label">GitHub</span></a></li>
								<li><a href="#" class="icon brands alt fa-linkedin-in"><span class="label">LinkedIn</span></a></li>
							</ul>
							<ul class="copyright">
								<li>&copy; Untitled</li><li>Design: <a href="https://html5up.net">HTML5 UP</a></li>
							</ul>
						</div>
					</footer>

			</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.scrolly.min.js"></script>
			<script src="assets/js/jquery.scrollex.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>

	</body>
</html>