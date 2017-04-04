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


    <div class="parallax" id="about"><img src="<c:url value="/resources/img/header.jpg" />" alt="Header image"></div>
  </div>

    <div class="container" >
        <section>
        <div class="row"></div>
	<div class="row">
	<div class="col s12 m9 offset-m1">
    <h4 class="center">Przygotowujesz się do egzaminu kwalifikacyjnego z florystyki? Poćwicz testy!</h4>
    </div>
        <div class="row"></div>
        <div class="row">
	<div class="col s12 m9 offset-m1">

<p>W bazie znajdują się pytania z arkuszy egzaminacyjnych z lat <span class="red-text text-accent-2">2010 - 2016</span>.  Łączna liczba pytań w bazie to obecnie  <span class="red-text text-accent-2">550</span>.  Najnowsze pytania pochodzą z egzaminu z <span class="red-text text-accent-2">czerwca 2016 roku</span>. Testy możesz rozwiązywać na trzy sposoby:</p>
</div>
</div>
<div class="row">
<div class="col s12 m3 offset-m1 align-c">
<h5 class="center red-text text-accent-2">Symulacja testu</h5>
       <i class="material-icons medium center light-green-text text-darken-1" style="text-align: center;">receipt</i>

<p class="align-j">Losowane jest 40 pytań, na rozwiązanie których jest 60 minut. Po upływie czasu test zostaje przerwany, tak jak na prawdziwym egzaminie.</p>
</div>
<div class="col s12 m3 align-c">
<h5 class="center red-text text-accent-2">Szybki test</h5>
       <i class="material-icons medium center light-green-text text-darken-1">av_timer</i>

<p class="align-j">Opcja dla zabieganych. Losowane jest jedno pytanie, które od razu można sprawdzić.</p>
</div>
<div class="col s12 m3 align-c">
<h5 class="center red-text text-accent-2">PDF</h5>
       <i class="material-icons medium center light-green-text text-darken-1">print</i>
<p class="align-j">Przydatne dla nauczycieli. Generuje gotowy do druku test z 40 pytaniami i kluczem odpowiedzi.</p>
</div>
</div>
	<div class="col s12 m9 offset-m1">

<p>Jeżeli znajdziesz na stronie jakiś błąd, będę wdzięczny, jeżeli zgłosisz do mailowo na adres <a class="red-text text-accent-2" href="mailto:marcin@dudek.io">marcin@dudek.io</a>. Przy opisie błędu podaj numer pytania, który znajduje się w szarym pasku pod każdym pytaniem. Pomoże mi to szybko i bezproblemowo poprawić wszystkie niedociągnięcia.</p>
	</div>
</div>
</section>

    </div>

</div>

  <%@ include file="footer.jsp" %>
