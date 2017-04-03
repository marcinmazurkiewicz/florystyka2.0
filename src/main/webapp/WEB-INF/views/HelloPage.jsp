<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HelloPage</title>
</head>
<body>

<h1>First Spring MVC Application Demo</h1>
<%@taglib  uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form:form action="/formularz" modelAttribute="form" method="post">

 Imię: 
 <form:input path="imie" id="imie"></form:input>
 <c:if test="${pageContext.request.method=='POST'}"><form:errors path="imie" /></c:if>
 <br />

 Adres email: 
 <form:input path="email" id="email"></form:input>
 <c:if test="${pageContext.request.method=='POST'}"><form:errors path="email" /></c:if>
 <br />

 Wiek:
 <form:input path="wiek" id="wiek"></form:input>
 <c:if test="${pageContext.request.method=='POST'}"><form:errors path="wiek" /></c:if>
 <br />

 <input type="submit" value="Wyślij formularz" />
</form:form>

</body>
</html>