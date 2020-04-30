<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ include file="header.jsp" %>

<body>
<div id="container">
<div id="wrapper">
<%@ include file="menu.jsp" %>

<div class=quest>
${question.getContent()}
</div>

</div>
<%@ include file="footer.jsp" %>


