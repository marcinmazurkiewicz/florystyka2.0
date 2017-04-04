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
  <link href="resources/css/test_style.css" type="text/css" rel="stylesheet" media="screen,projection"/>

<link href='https://fonts.googleapis.com/css?family=Lato:400,700,400italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'>

<style>

nav {
    position: fixed;
    top: 0;
    left: 0;
}

#timer {
    height: 64px;
}
@media only screen and (max-width: 680px) {
#logo-container {
    font-size: 1.5rem;
}
}

</style>

<script>

var minute = 60;
var second = 1;
var finish = 0;
var timeout = 0;

function countdown()
{
	if(minute <= 0 && second <= 0)
	{
		document.getElementById('logo-container').innerHTML='Koniec czasu - nastąpi przekierowanie do strony z wynikami';
		timeout = 1;
		document.getElementById("examform").submit();
	}

	second = second - 1;
	if (second < 0) {second = 59; minute = minute - 1;}
	if (minute < 0) {minute = 59;}

	if(minute >= 0 && second >= 0)
	{
		document.getElementById('logo-container').innerHTML='Czas do końca: '+minute+' min '+second+' sek' ;
		setTimeout("countdown()",1000);
	}
}

function checktest()
{
	finish=1;
	document.getElementById("examform").submit();
}

</script>
<script>window.onload = countdown;</script>
</head>
<body class="custom_grey white-text">
<%@ include file="menu.jsp" %>
<div id="container">
<div id="wrapper">
<div id="timer"></div>

<div class="head-info align-c">
<h4>Symulacja - test 40 pytań</h4>

<p>Sprawdź się w dokładnie takim samym trybie, jaki obowiązuje podczas rzeczywistego egzaminu.</p>
<p>Na rozwiązanie całej części pisemnej otrzymujesz maksymalnie 60 minut.<br /> To całkiem sporo czasu, więc zachowaj spokój :)</p>
</div>

<form:form method="POST" action="test" modelAttribute="solutions" id="examform">
<c:forEach items="${solutions.answers}" varStatus="i" >
<div class="quest">

${i.count}. ${questions.get(i.index).content}
<c:if test="${questions.get(i.index).img != null}"><img src="<c:url value="/resources/quest_img/${questions.get(i.index).img}" />" /></c:if>

</div>

<form:radiobutton class="radio" id="${questions.get(i.index).id}${questions.get(i.index).getAnswerType(0)}"
	path="answers[${i.index}].answer" value="${questions.get(i.index).getAnswerType(0)}" />

<label class="answer" for="${questions.get(i.index).id}${questions.get(i.index).getAnswerType(0)}">
	A. ${questions.get(i.index).getAnswerContent(0)}
</label>

<form:radiobutton class="radio" id="${questions.get(i.index).id}${questions.get(i.index).getAnswerType(1)}"
	path="answers[${i.index}].answer" value="${questions.get(i.index).getAnswerType(1)}" />

<label class="answer" for="${questions.get(i.index).id}${questions.get(i.index).getAnswerType(1)}">
	B. ${questions.get(i.index).getAnswerContent(1)}
</label>

<form:radiobutton class="radio" id="${questions.get(i.index).id}${questions.get(i.index).getAnswerType(2)}"
	path="answers[${i.index}].answer" value="${questions.get(i.index).getAnswerType(2)}" />

<label class="answer" for="${questions.get(i.index).id}${questions.get(i.index).getAnswerType(2)}">
	C. ${questions.get(i.index).getAnswerContent(2)}
</label>

<form:radiobutton class="radio" id="${questions.get(i.index).id}${questions.get(i.index).getAnswerType(3)}"
	path="answers[${i.index}].answer" value="${questions.get(i.index).getAnswerType(3)}" />

<label class="answer" for="${questions.get(i.index).id}${questions.get(i.index).getAnswerType(3)}">
	D. ${questions.get(i.index).getAnswerContent(3)}
</label>


<input type="hidden" name="answers[${i.index}].questionID"  value="${questions.get(i.index).id}" />

<div class=qid>Pyt. ${questions.get(i.index).id}</div>
</c:forEach>
<button class="light-green" id="check">Sprawdź</button>
<script>document.getElementById("check").setAttribute("onclick", "checktest()");</script>
</form:form>
</div>
<%@ include file="footer.jsp" %>
