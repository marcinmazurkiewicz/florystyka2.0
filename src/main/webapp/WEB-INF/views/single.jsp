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
<h4>Szybkie pytanie</h4>

<p>Poćwicz pojedyncze pytania. Od razu poznasz prawidłową odpowiedź, co pozwoli na jej łatwiejsze zapamiętanie.</p>
</div>

<form:form method="POST" action="single" modelAttribute="solution">
<div class=quest>

${question.content}
<c:if test="${question.img != null}"><img src="<c:url value="/resources/quest_img/${question.img}" />" /></c:if>

</div>

<form:radiobutton class="radio" id="${question.id}${question.getAnswerType(0)}" name="answer" path="answer" value="${question.getAnswerType(0)}" />
<label class="answer" for="${question.id}${question.getAnswerType(0)}">
	A. ${question.getAnswerContent(0)}
</label>

<form:radiobutton class="radio" id="${question.id}${question.getAnswerType(1)}" name="answer" path="answer" value="${question.getAnswerType(1)}" />
<label class="answer" for="${question.id}${question.getAnswerType(1)}">
	B. ${question.getAnswerContent(1)}
</label>

<form:radiobutton class="radio" id="${question.id}${question.getAnswerType(2)}" name="answer" path="answer" value="${question.getAnswerType(2)}" />
<label class="answer" for="${question.id}${question.getAnswerType(2)}">
	C. ${question.getAnswerContent(2)}
</label>

<form:radiobutton class="radio" id="${question.id}${question.getAnswerType(3)}" name="answer" path="answer" value="${question.getAnswerType(3)}" />
<label class="answer" for="${question.id}${question.getAnswerType(3)}">
	D. ${question.getAnswerContent(3)}
</label>
<input type="hidden" name="questionID"  value="${question.id}" />

<div class=qid>Pyt. ${question.getId()}</div>
<input class="light-green" type="submit" value="Sprawdź" />
</form:form>
</div>
<%@ include file="footer.jsp" %>
