<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Language" content="pl">
<title>Testy zawodowe technik florysta</title>

  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="http://egzamin-florystyka.pl/r26_css/test_style.css" type="text/css" rel="stylesheet" media="screen,projection"/>

<link href='https://fonts.googleapis.com/css?family=Lato:400,700,400italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
</head>
<body class="custom_grey white-text">
<%@ include file="menu.jsp" %>
<div id="container">
<div id="wrapper">

<div class="head-info align-c">
<h4>Logowanie</h4>
<c:url value="/login" var="loginUrl"/>
<form action="${loginUrl}" method="post">
	<c:if test="${param.error != null}">
		<p>
			Nieprawidłowy login lub hasło.
		</p>
	</c:if>
	<c:if test="${param.logout != null}">
		<p>
			You have been logged out.
		</p>
	</c:if>
	<p>
		<label for="username">Username</label>
		<input type="text" id="username" name="username"/>
	</p>
	<p>
		<label for="password">Password</label>
		<input type="password" id="password" name="password"/>
	</p>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<button type="submit" class="btn">Zaloguj</button>
</form>
</div>
<%@ include file="footer.jsp" %>
