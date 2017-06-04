<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Language" content="pl">
<title>Testy zawodowe technik florysta</title>

  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="http://egzamin-florystyka.pl/r26_css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="http://egzamin-florystyka.pl/r26_css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>

<link href='https://fonts.googleapis.com/css?family=Lato:400,700,400italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'>

<script src="<c:url value="/resources/js/pdfmake.min.js"/>"></script>
<script src="<c:url value="/resources/js/vfs_fonts.js"/>"></script>

<script>

function genPDF() {
	var questions = ${pdfContent};
	var emptyLine = '\n';
	var docDefinition = {
		pageSize: 'A4',
		pageMargins: [40,50],
	    header: { text: '\nwww.egzamin-florystyka.pl', fontSize: 10, alignment : 'right', margin: [20,0]  },
  		content: [
			emptyLine,
    		{ text: 'Kwalifikacja zawodowa R.26', fontSize: 18, bold: true },
			emptyLine,
    	    { text: 'Wykonywanie kompozycji florystycznych (test próbny)',fontSize: 14, bold: true},
  			emptyLine,
			questions,
		],
		defaultStyle: {
	   		font: 'Arial'
  		},
  		styles :  {
    		content: {
				fontSize:11,
				bold: true
    	},
    	answer: {
      		fontSize: 10,
     		 margin: [5, 3]
    	},
    	id: {
      		fontSize: 15,
    	},
    	table: {
			margin: [0, 10]
		 },
		 answerKey: {
			 alignment: 'center',
	 	},
	 	singleKey: {
			fontSize:10,
			 margin: [3, 5]
		}
  		}
	};
	pdfMake.fonts = {
		  Arial: {
    		normal: 'arial.ttf',
    		bold: 'arialbd.ttf',
    		italics: 'ariali.ttf',
		    bolditalics: 'arialbi.ttf'
  	}
	};
	pdfMake.createPdf(docDefinition).download('r26.pdf');
}

//for old web browsers, which not support pdfMake.download()
function openPDF() {
	var questions = ${pdfContent};
	var emptyLine = '\n';
	var docDefinition = {
		pageSize: 'A4',
		pageMargins: [40,50],
	    header: { text: '\nwww.egzamin-florystyka.pl', fontSize: 10, alignment : 'right', margin: [20,0]  },
  		content: [
			emptyLine,
    		{ text: 'Kwalifikacja zawodowa R.26', fontSize: 18, bold: true },
			emptyLine,
    	    { text: 'Wykonywanie kompozycji florystycznych (test próbny)',fontSize: 14, bold: true},
  			emptyLine,
			questions,
		],
		defaultStyle: {
	   		font: 'Arial'
  		},
  		styles :  {
    		content: {
				fontSize:11,
				bold: true
    	},
    	answer: {
      		fontSize: 10,
     		 margin: [5, 3]
    	},
    	id: {
      		fontSize: 15,
    	},
    	table: {
			margin: [0, 10]
		 },
		 answerKey: {
			 alignment: 'center',
	 	},
	 	singleKey: {
			fontSize:10,
			 margin: [3, 5]
		}
  		}
	};
	pdfMake.fonts = {
		  Arial: {
    		normal: 'arial.ttf',
    		bold: 'arialbd.ttf',
    		italics: 'ariali.ttf',
		    bolditalics: 'arialbi.ttf'
  	}
	};
	pdfMake.createPdf(docDefinition).open();
}

function info() {
	var info = document.getElementById('downloadinfo');
	if(info.innerHTML === "") {
		info.innerHTML = '<p>Rozpocząto generowanie pliku, za chwilę rozpocznie się pobieranie. Jeżeli tak się nie stanie, kliknij <a class="red-text text-accent-2" href="javascript:openPDF()">tutaj</a> </p>';
	}
}
</script>

</head>


<body class="custom_grey white-text">

  <%@ include file="menu.jsp" %>

<div id="main_container">
  <div id="index-banner" class="parallax-container" >
    <div class="section no-pad-bot">
      <div class="container">
        <h1 class="header center sec_header" style="padding-top: 20px">Kwalifikacja R.26 </h1>
        <div class="row center">
          <h5 class="header col s12 light">Wykonywanie kompozycji florystycznych</h5>
        </div>


      </div>
    </div>


    <div class="parallax" id="about"><img src="<c:url value="/resources/img/header3.jpg" />" alt="Header image"></div>
  </div>

    <div class="container" >
        <section>
        <div class="row"></div>
	<div class="row">
	<div class="col s12 m10 offset-m1  align-c">
    <h4 class="red-text text-accent-2">Test w PDF</h4>

    <p>Gotowy do druku test do rozwiązania na zajęciach lub do zabrania tam, gdzie nie ma sieci.</p>
    <p>Na ostatniej stronie znajduje się klucz odpowiedzi - zajrzyj do niego dopiero po rozwiązaniu testu ;)</p>
    </div>
    	<div class="col s12 m10 offset-m1">

    <h5 class="center">Wylosowano pytania do testu</h5>
    </div>
        <div class="row"></div>
        <div class="row">
	<div class="col s12 m10 offset-m1 align-c">

<a onclick="info()" href="javascript:genPDF()" class="waves-effect waves-light btn light-green"> <i class="tiny material-icons left">file_download</i>Generuj PDF</a>
<p id="downloadinfo"></p>

</div>
</div>

</section>

    </div>

</div>

  <%@ include file="footer.jsp" %>