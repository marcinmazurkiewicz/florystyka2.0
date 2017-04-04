<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ include file="header.jsp" %>

<body class="custom_grey white-text">

  <%@ include file="menu.jsp" %>

<div id="main_container">
  <div id="index-banner" class="parallax-container" >
    <div class="section no-pad-bot">
      <div class="container">
        <h1 class="header center sec_header" style="padding-top: 20px">Kwalifikacja R.26 </h1>
        <div class="row center">
          <h5 class="header col s12 light lalign">Wykonywanie kompozycji florystycznych</h5>
        </div>


      </div>
    </div>


    <div class="parallax" id="about"><img src="<c:url value="/resources/img/header2.jpg" />" alt="Header image"></div>
  </div>

    <div class="container" >
        <section>
        <div class="row"></div>
	<div class="row">
	<div class="col s12 m10 offset-m1">
    <h4 class="center">Co to za strona?</h4>
    </div>
        <div class="row"></div>
        <div class="row">
	<div class="col s12 m10 offset-m1">

<p class="align-j">Projekt powstał jako odpowiedź na brak dobrej i darmowej bazy testów dla technika florysty, jakie istnieją dla innych zawodów.
   Strona została napisana w 2015 roku jako projekt "po godzinach" dla starogardzkich florstek, przygotowujących się do testu R26 :) </p>
</div>
	<div class="col s12 m10 offset-m1">
<p class="align-j">Szkoda, aby praca włożona w stworzenie strony została zmarnowana, dlatego postanowiłem udostępnić ją innym osobom, które przygotowują się do egzaminu.
                   W bazie znajdują się pytania z arkuszy egzaminacyjnych z lat 2010 - 2016. &nbsp; Łączna liczba pytań w bazie to obecnie ${how_many}.</p>

</div>

	<div class="col s12 m10 offset-m1">
<p class="align-j">Jeżeli znajdziesz na stronie jakiś błąd, będę wdzięczny, jeżeli zgłosisz do mailowo na adres <a class="red-text text-accent-2" href="mailto:marcin@dudek.io">marcin@dudek.io</a>.
Przy opisie błędu podaj numer pytania, który znajduje się w szarym pasku pod każdym pytaniem. Pomoże mi to szybko i bezproblemowo poprawić wszystkie niedociągnięcia.</p>
</div>
</div>

</section>

    </div>

</div>

  <%@ include file="footer.jsp" %>