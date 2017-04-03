<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    
<div id="navi">
	<!--<div class="edge"></div>-->
	<a href="<spring:url value="/" />"><div class="menu"><span>START<span>Strona główna</span></span></div></a>
	<a href="<spring:url value="/test" />"><div class="menu"><span>TEST<span>losuj 40 pytań</span></span></div></a>
	<a href="<spring:url value="/single" />"><div class="menu"><span>SZYBKO<span>losuj 1 pytanie</span></span></div></a>
	<a href="<spring:url value="/pdf" />"><div class="menu"><span>PDF<span>test do druku</span></span></div></a>
	<a href="<spring:url value="/info" />"><div class="menu"><span>INFO<span>o projekcie</span></span></div></a>
	<!--<div class="edge"></div>-->
	<div class="clear"></div>
</div>