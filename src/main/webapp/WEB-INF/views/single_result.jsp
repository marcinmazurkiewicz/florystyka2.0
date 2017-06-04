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

<div class="quest">
${question.getContent()}
</div>

<c:choose>
<c:when test="${question.getAnswerType(0).equals(question.getCorrect()) }"><div class=goodanswer>${question.getAnswerContent(0)}</div></c:when>
<c:when test="${question.getAnswerType(0).equals(solution.getAnswer()) }"><div class=youranswer>${question.getAnswerContent(0)}</div></c:when>
<c:otherwise><div class=badanswer>${question.getAnswerContent(0)}</div></c:otherwise>
</c:choose>


<c:choose>
<c:when test="${question.getAnswerType(1).equals(question.getCorrect()) }"><div class=goodanswer>${question.getAnswerContent(1)}</div></c:when>
<c:when test="${question.getAnswerType(1).equals(solution.getAnswer()) }"><div class=youranswer>${question.getAnswerContent(1)}</div></c:when>
<c:otherwise><div class=badanswer>${question.getAnswerContent(1)}</div></c:otherwise>
</c:choose>

<c:choose>
<c:when test="${question.getAnswerType(2).equals(question.getCorrect()) }"><div class=goodanswer>${question.getAnswerContent(2)}</div></c:when>
<c:when test="${question.getAnswerType(2).equals(solution.getAnswer()) }"><div class=youranswer>${question.getAnswerContent(2)}</div></c:when>
<c:otherwise><div class=badanswer>${question.getAnswerContent(2)}</div></c:otherwise>
</c:choose>

<c:choose>
<c:when test="${question.getAnswerType(3).equals(question.getCorrect()) }"><div class=goodanswer>${question.getAnswerContent(3)}</div></c:when>
<c:when test="${question.getAnswerType(3).equals(solution.getAnswer()) }"><div class=youranswer>${question.getAnswerContent(3)}</div></c:when>
<c:otherwise><div class=badanswer>${question.getAnswerContent(3)}</div></c:otherwise>
</c:choose>
<div class=qid>Pyt. ${question.getId()}</div>

<a href="<spring:url value="/single" />"><button class="light-green">Losuj kolejne</button></a>

</div>


<%@ include file="footer.jsp" %>


