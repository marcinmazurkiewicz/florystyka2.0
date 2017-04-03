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

<a href="<spring:url value="/single" />"><button>Losuj kolejne</button></a>

</div>


<%@ include file="footer.jsp" %>


