<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%@ include file="header.jsp" %>

<body>
<div id="container">
<div id="wrapper">
<%@ include file="menu.jsp" %>

	<div class=info><h1>Przygotowujesz się do egzaminu kwalifikacyjnego z florystyki? Poćwicz testy!</h1>
	<img src="<c:url value="/resources/img/start.png" />" /><br />
	<h2>Kwalifikacja R.26 Wykonywanie kompozycji florystycznych</h2>
	<p>W bazie znajdują się pytania z arkuszy egzaminacyjnych z lat 2010 - 2016. Łączna liczba pytań w bazie to obecnie ${how_many}.
	Najnowsze pytania pochodzą z egzaminu z czerwca 2016 roku.</p>
	
	<p>Strona oferuje <strong>"symulację testu kwalifikacyjnego"</strong> - losowane jest 40 pytań, na rozwiązanie których jest 60 minut. 
	Po upływie czasu test zostaje przerwany, tak jak na prawdziwym egzaminie. 
	Dla zabieganych powstała opcja <strong>"szybkiego testu"</strong>. Losowane jest jedno pytanie, które od razu można sprawdzić.
	</p><br />
	<p>Jeżeli znajdziesz na stronie jakiś błąd, będę wdzięczny, jeżeli zgłosisz do mailowo na adres <a href="mailto:marcin.a.dudek@gmail.com">marcin.a.dudek@gmail.com</a>. Przy opisie błędu podaj numer pytania, który znajduje się w szarym pasku pod każdym pytaniem. 
	Pomoże mi to szybko i bezproblemowo poprawić wszystkie niedociągnięcia.</p>

	 </div>


</div>
<%@ include file="footer.jsp" %>


