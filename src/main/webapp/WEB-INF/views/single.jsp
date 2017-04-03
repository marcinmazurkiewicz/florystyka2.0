<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="header.jsp" %>

<body>
<div id="container">
<div id="wrapper">
<%@ include file="menu.jsp" %>

<div class=quest>
${question.getContent()}
<c:if test="${question.img != null}"><img src="<c:url value="/resources/quest_img/${question.img}" />" /></c:if>
</div>

<form:form method="POST" action="single" modelAttribute="solution">

<form:radiobutton class="radio" id="${question.id}${question.getAnswerType(0)}" name="answer" path="answer" value="${question.getAnswerType(0)}" />
<label class="answer" for="${question.id}${question.getAnswerType(0)}">
	${question.getAnswerContent(0)}
</label> 

<form:radiobutton class="radio" id="${question.id}${question.getAnswerType(1)}" name="answer" path="answer" value="${question.getAnswerType(1)}" />
<label class="answer" for="${question.id}${question.getAnswerType(1)}">
	${question.getAnswerContent(1)}
</label> 

<form:radiobutton class="radio" id="${question.id}${question.getAnswerType(2)}" name="answer" path="answer" value="${question.getAnswerType(2)}" />
<label class="answer" for="${question.id}${question.getAnswerType(2)}">
	${question.getAnswerContent(2)}
</label> 

<form:radiobutton class="radio" id="${question.id}${question.getAnswerType(3)}" name="answer" path="answer" value="${question.getAnswerType(3)}" />
<label class="answer" for="${question.id}${question.getAnswerType(3)}">
	${question.getAnswerContent(3)}
</label> 
<input type="hidden" name="questionID"  value="${question.id}" />

<div class=qid>Pyt. ${question.getId()}</div>
<input type="submit" value="Sprawdź" />
</form:form>
</div>
<%@ include file="footer.jsp" %>


