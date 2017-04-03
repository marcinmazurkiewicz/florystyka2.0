<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="header.jsp" %>

<body>
<div id="container">
<div id="wrapper">
<%@ include file="menu.jsp" %>
<form:form method="POST" action="test" modelAttribute="solutions">
<c:forEach items="${solutions.answers}" varStatus="i" >
<div class=quest>

${i.count}. ${questions.get(i.index).content}
</div>

<form:radiobutton class="radio" id="${questions.get(i.index).id}${questions.get(i.index).getAnswerType(0)}" 
	path="answers[${i.index}].answer" value="${questions.get(i.index).getAnswerType(0)}" />

<label class="answer" for="${questions.get(i.index).id}${questions.get(i.index).getAnswerType(0)}">
	${questions.get(i.index).getAnswerContent(0)}
</label> 

<form:radiobutton class="radio" id="${questions.get(i.index).id}${questions.get(i.index).getAnswerType(1)}" 
	path="answers[${i.index}].answer" value="${questions.get(i.index).getAnswerType(1)}" />

<label class="answer" for="${questions.get(i.index).id}${questions.get(i.index).getAnswerType(1)}">
	${questions.get(i.index).getAnswerContent(1)}
</label> 

<form:radiobutton class="radio" id="${questions.get(i.index).id}${questions.get(i.index).getAnswerType(2)}" 
	path="answers[${i.index}].answer" value="${questions.get(i.index).getAnswerType(2)}" />

<label class="answer" for="${questions.get(i.index).id}${questions.get(i.index).getAnswerType(2)}">
	${questions.get(i.index).getAnswerContent(2)}
</label> 

<form:radiobutton class="radio" id="${questions.get(i.index).id}${questions.get(i.index).getAnswerType(3)}" 
	path="answers[${i.index}].answer" value="${questions.get(i.index).getAnswerType(3)}" />

<label class="answer" for="${questions.get(i.index).id}${questions.get(i.index).getAnswerType(3)}">
	${questions.get(i.index).getAnswerContent(3)}
</label> 


<input type="hidden" name="answers[${i.index}].questionID"  value="${questions.get(i.index).id}" />

<div class=qid>Pyt. ${questions.get(i.index).id}</div>
</c:forEach>
<input type="submit" value="Sprawdź" />
</form:form>
</div>
<%@ include file="footer.jsp" %>


