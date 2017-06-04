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

<form:radiobutton class="radio" id="${question.id}A" name="answer" path="answer" value="A" />
<label class="answer" for="${question.id}A">
	A. ${question.answerA}
</label>

<form:radiobutton class="radio" id="${question.id}B" name="answer" path="answer" value="B" />
<label class="answer" for="${question.id}B">
	B. ${question.answerB}
</label>

<form:radiobutton class="radio" id="${question.id}C" name="answer" path="answer" value="C" />
<label class="answer" for="${question.id}C">
	C. ${question.answerC}
</label>

<form:radiobutton class="radio" id="${question.id}D" name="answer" path="answer" value="D" />
<label class="answer" for="${question.id}D">
	D. ${question.answerD}
</label>
<input type="hidden" name="questionID"  value="${question.id}" />

<div class=qid>Pyt. ${question.getId()}</div>
<input class="light-green" type="submit" value="Sprawdź" />
</form:form>
</div>
<%@ include file="footer.jsp" %>
