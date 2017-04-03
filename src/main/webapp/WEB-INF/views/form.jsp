<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Language" content="pl">
<title>Testy zawodowe technik florysta</title>

<style type="text/css">
/* <![CDATA[ */
html {
	margin: 0 auto;
	padding:0;
	height:100%;
	font-family: 'Lato', sans-serif;
	font-size: 62.5%;
}

body {
	background:, url('img/bg2.png');
	background-color:  #333;
	margin: 0 auto;
	padding:0;
	height:100%;
	font-size: 18px;
	font-size: 1.8rem;
	}
	
#wrapper {
	margin: 0 auto;
	max-width: 1000px;
	background-color: #ffffff;
	padding-bottom: 20px;
	margin-bottom: 50px;
	
}

.quest {
	background-color: #cccccc;
	padding: 10px;
	margin: 10px auto;
	width: 90%;
	border: 2px solid #aaaaaa;
	font-size: bigger;
	font-weight: 700;
}
.quest table {
	margin: 0 auto;
	margin-top: 10px;
	border: 1px solid #000000;
	border-collapse: collapse;
	text-align: center;
	background: #eeeeee;
	vertical-align: middle;
}

.quest table td {
	border: 1px solid #000000;
	padding: 8px;
	margin: 0;
}

.quest img {
	padding-top: 10px;
	max-width: 100%;
	display: block;
	margin: 0 auto;
	
}

.info {
	background-color: #ffffff;
	padding: 10px;
	margin: 0 auto;
	margin-bottom: 5px;
	width: 90%;
	border: 1px solid #cccccc;
}
.info p {
	text-align: justify;
	line-height: 30px;
}

.info img {
	padding-top: 10px;
	max-width: 100%;
	display: block;
	margin: 0 auto;
	
}

.info h1 {
	text-align: center;
	font-size: 24px;
	font-size: 2.4rem;
}

.info h2 {
	text-align: center;
	font-size: 20px;
	font-size: 2.0rem;
}

.answer {
	display: block;
	background-color: #ffffff;
	padding: 10px;
	margin: 0 auto;
	margin-bottom: 5px;
	width: 90%;
	border: 1px solid #cccccc;
}

.answer span, .badanswer span, .goodanswer span, .youranswer span {
	padding-right: 10px;
}

.badanswer {
	background-color: #F1D3D3;
	padding: 10px;
	margin: 0 auto;
	margin-bottom: 5px;
	width: 90%;
	border: 1px solid #F58D8D;
}

.goodanswer {
	background-color: #9EDE9E;
	padding: 10px;
	margin: 0 auto;
	margin-bottom: 5px;
	font-weight: bold;
	width: 90%;
	border: 1px solid #00a000;
}

.youranswer {
	background-color: #C7EAF9;
	padding: 10px;
	margin: 0 auto;
	margin-bottom: 5px;
	width: 90%;
	border: 1px solid #2878D2;
}


.answer:hover {
	background-color: #dddddd;
	border: 1px solid #999999;
}


.lbl {
	display: block;
	cursor: pointer;	
}

.radio {
    display: none;
    
}

input[type=radio]:checked + .answer {
    background-color: #C7EAF9;
    border: 1px solid #2878D2;
}

.qid {
	background-color: #eeeeee;
	padding: 3px;
	padding-right: 15px;
	margin: 0 auto;
	margin-bottom: 10px;
	width: 90%;
	text-align: right;
	font-size: 11px;
	font-size: 1.1rem;
	border: 1px solid #cccccc;
}

#navi {
margin-bottom: 30px;
margin-top: 0px;
}
.menu {
	width: 20%;
	float: left;
	border-top:solid 5px #000000;
	text-align: center;
	padding-top: 10px;
	padding-bottom: 10px;
}

.menu:hover {
	background-color: #F5F5F5;
	border-top: solid 5px #31b3dd;
	border-bottom: solid 5px #31b3dd;
	padding-bottom: 5px;
}

.menu a, a:link, a:visited {
	color: #2F393C;
}
.menu span {
	font-size: 34px;
	font-size: 3.4rem;
	
}

@media only screen and (max-width: 680px) {
	.menu {
	width: 20%;
	}
	
	.menu span {
	font-size: 16px;
	font-size: 1.6rem;
	}
	
	
	
	.menu span span {
	font-size: 9px;
	font-size: 0.9rem;
	padding-right: 2px;
	padding-left: 2px;
	display: block;
}
}

.menu span span {
	font-size: 14px;
	font-size: 1.4rem;
	display: block;
}

.edge {
	float: left;
	width: 10%;
	border-top:solid 5px #000000;
}

.clear {
	clear: both;
}

#zegar {
	padding: 10px;
	margin: 0 auto;
	margin-top: 10px;
	margin-bottom: 10px;
	width: 90%;
	border: 2px solid #aaaaaa;
	font-size: bigger;
	font-weight: 700;
	text-align: center;
}

.sticky {
	position: fixed;
	max-width:400px;
	top: 0;
	left: 20px;
	background: #ffffff;
	z-index: 100;
}

input[type="submit"] {
	width: 92%;
	margin: 0 auto;
    background-color: #777;
    border: 1px solid #222;
    border-radius: 0.202em;
	display: block;
	padding: 10px;
	color: #FFF;
	text-align: center;
	text-decoration: none;
	font-family: inherit;
	cursor: pointer;
	overflow: visible;
	font-size: 20px;
	font-size:2.0rem;
	line-height: 1.618em;
	font-weight: bold;
}

#container {
min-height: 100%;
height: auto !important;
height: 100%;
margin: 0 auto -60px; 
}

.push {
height: 36px; /*klasa .push musi mieÄ tÄ samÄ wysokoĹÄ co klasa .footer */
}

#footer {
height: 36px;
	color: #2F393C;
	text-align: right;
	padding-right: 20px;
	
	margin: 0, auto;
	font-size: 14px;
	font-size:1.4rem;
}
#footer a, a:link, a:visited {
	color: #2F393C;
	text-decoration: none;
}

#footer p {
	background-color: f5f5f5;
	display: inline-block;
	padding-top: 7px;
	padding-bottom:7px;
	padding-left: 10px;
	padding-right: 10px;
}


.answerchck {

    border:2px solid blue;
    background-color:yellow;
    background-color: #ffffff;
	padding: 10px;
	margin: 0 auto;
	margin-bottom: 5px;
	width: 90%;
	border: 1px solid #cccccc;
    
}

/*.chb{display:none;}*/


/* ]]> */
</style>


<link href='https://fonts.googleapis.com/css?family=Lato:400,700,400italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
</head>
<body>
<div id="container">
<div id="wrapper">

<%@ include file="menu.jsp" %>
<form:form method="POST" modelAttribute="samochod">
	Marka<form:input path="marka" />
	Model<form:input path="model" />
	Nr rejestracyjny<form:input path="numerRejestracyjny" />
	<form:radiobutton class="radio" id="a" path="answer" value="AnswerType.A" /><label for="a" class=answer>A. value</label>
	<form:radiobutton class="radio" id="b" path="answer" value="AnswerType.B" /><label for="b" class=answer> B value</label>
	
	<input type="submit" value="zapisz" />
</form:form>
</div>
<%@ include file="footer.jsp" %>
