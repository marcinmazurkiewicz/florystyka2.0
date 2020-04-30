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
<script src="<c:url value="/resources/js/register.js"/>"></script>

</head>
<body class="custom_grey white-text">
<%@ include file="menu.jsp" %>
<div id="container">
<div id="wrapper">

<div class="head-info align-c">
<h4>Rejestracja</h4>
<form:form action="signup" modelAttribute="user" method="POST">

	<p>
		<label for="mail">Adres e-mail</label>
		<form:input name="mail" path="mail" />
	</p>
	<p>
		<label for="password">Hasło</label>
		<form:password id="password" name="password" path="password"/>
	</p>
	<p>
    		<label for="confirmPassword">Powtórz hasło</label>
    		<input type="password" id="confirm_password" name="confirmPassword" onkeyup='check();' />
    <span id='message'></span>
    </p>
	<button type="submit" class="light-green" id="register" onmouseenter="active()">Zarejestruj</button>
</form:form>
</div>
<%@ include file="footer.jsp" %>
