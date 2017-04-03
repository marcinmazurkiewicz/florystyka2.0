<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="header.jsp" %>

<body>
<div id="container">
<div id="wrapper">
<%@ include file="menu.jsp" %>
<%@taglib  uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class=quest>
${question.getContent()}
</div>

</div>
<%@ include file="footer.jsp" %>


