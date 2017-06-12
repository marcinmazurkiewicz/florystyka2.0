<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<nav class="light-green" role="navigation">
    <div class="nav-wrapper container ">
      <a data-scroll id="logo-container" class="brand-logo">Testy florystyczne</a>
      <ul class="right hide-on-med-and-down">
        <li><a href="<spring:url value="/" />">START</a></li>
        <li><a href="<spring:url value="/test" />">TEST</a></li>
        <li><a href="<spring:url value="/single" />">SZYBKO</a></li>
        <li><a href="<spring:url value="/pdf" />">PDF</a></li>
        <li><a href="<spring:url value="/info" />">INFO</a></li>
  	    <li><a href="<spring:url value="/account" />">MÓJ PROFIL</a></li>
  	    <li><a href="<spring:url value="/login" />">ZALOGUJ</a></li>
  	    <li><a href="<spring:url value="/logout" />">WYLOGUJ</a></li>


      </ul>

      <ul id="nav-mobile" class="side-nav">
        <li><a href="<spring:url value="/" />">START</a></li>
	    <li><a href="<spring:url value="/test" />">TEST</a></li>
	    <li><a href="<spring:url value="/single" />">SZYBKO</a></li>
	    <li><a href="<spring:url value="/pdf" />">PDF</a></li>
	    <li><a href="<spring:url value="/info" />">INFO</a></li>
	    <li><a href="<spring:url value="/account" />">MÓJ PROFIL</a></li>
	    <li><a href="<spring:url value="/login" />">ZALOGUJ</a></li>
        <li><a href="<spring:url value="/logout" />">WYLOGUJ</a></li>
	  </ul>
      <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
    </div>
  </nav>