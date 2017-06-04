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
<body>
<div id="container">
<div id="wrapper">
<c:choose>
<c:when test="${points > 20}"><div class="good-result">Test zaliczony  :)  Twój wynik to ${points} punktów (${(points*100)/40}%) Gratulacje!</div></c:when>
<c:otherwise><div class="bad-result">Test niezaliczony  :(  Twój wynik to ${points} punktów (${(points*100)/40}%)</div></c:otherwise>
</c:choose>


<c:forEach items="${solutions.answers}" var="solution" varStatus="i" >

<div class=quest>
${i.count}. ${questions.get(i.index).content}
<c:if test="${questions.get(i.index).img != null}"><img src="<c:url value="/resources/quest_img/${questions.get(i.index).img}" />" /></c:if>

</div>
<c:choose>
<c:when test="${questions.get(i.index).getAnswerType(0).equals(questions.get(i.index).getCorrect()) }"><div class=goodanswer></c:when>
<c:when test="${questions.get(i.index).getAnswerType(0).equals(solution.answer) }"><div class=youranswer></c:when>
<c:otherwise><div class=badanswer></c:otherwise>
</c:choose>
${questions.get(i.index).getAnswerContent(0)}</div>




<c:choose>
<c:when test="${questions.get(i.index).getAnswerType(1).equals(questions.get(i.index).getCorrect()) }"><div class=goodanswer></c:when>
<c:when test="${questions.get(i.index).getAnswerType(1).equals(solution.answer) }"><div class=youranswer></c:when>
<c:otherwise><div class=badanswer></c:otherwise>
</c:choose>
${questions.get(i.index).getAnswerContent(1)}</div>



<c:choose>
<c:when test="${questions.get(i.index).getAnswerType(2).equals(questions.get(i.index).getCorrect()) }"><div class=goodanswer></c:when>
<c:when test="${questions.get(i.index).getAnswerType(2).equals(solution.answer) }"><div class=youranswer></c:when>
<c:otherwise><div class=badanswer></c:otherwise>
</c:choose>
${questions.get(i.index).getAnswerContent(2)}</div>



<c:choose>
<c:when test="${questions.get(i.index).getAnswerType(3).equals(questions.get(i.index).getCorrect()) }"><div class=goodanswer></c:when>
<c:when test="${questions.get(i.index).getAnswerType(3).equals(solution.answer) }"><div class=youranswer></c:when>
<c:otherwise><div class=badanswer></c:otherwise>
</c:choose>
${questions.get(i.index).getAnswerContent(3)}</div>




<div class=qid>Pyt. ${questions.get(i.index).id}</div>

</c:forEach>

<a href="test"><button class="light-green">Losuj kolejny zestaw</button></a>

</div>


<%@ include file="footer.jsp" %>


