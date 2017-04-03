<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="header.jsp" %>

<body>
<div id="container">
<div id="wrapper">
<%@ include file="menu.jsp" %>
<c:choose> 
<c:when test="${points > 20}"><div class=goodanswer>Test zaliczony  :)  Twój wynik to ${points} punktów (${(points*100)/40}%) Gratulacje!</div></c:when>
<c:otherwise><div class=badanswer>Test niezaliczony  :(  Twój wynik to ${points} punktów (${(points*100)/40}%)</div></c:otherwise>
</c:choose>


<c:forEach items="${solutions.answers}" var="solution" varStatus="i" >

<div class=quest>
${i.count}. ${questions.get(i.index).content}

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

<a href="test"><button>Losuj kolejny zestaw</button></a>

</div>


<%@ include file="footer.jsp" %>


