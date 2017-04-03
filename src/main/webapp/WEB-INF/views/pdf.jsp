<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Language" content="pl">
<title>Testy zawodowe technik florysta</title>
<c:url value="/resources/css/style.css" var="styleCSS" />
<link href="${styleCSS}" rel="stylesheet" type="text/css"/>

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
		info.innerHTML = '<p>Rozpocząto generowanie pliku, za chwilę rozpocznie się pobieranie. Jeżeli tak się nie stanie, kliknij <a href="javascript:openPDF()">tutaj</a> </p>';
	}
}
</script>
</head>
<body>
<div id="container">
<div id="wrapper">
<%@ include file="menu.jsp" %>
<div id="pdfinfo" ><p>Wylosowano pytania do testu</p>
<a onclick="info()" href="javascript:genPDF()"><button >Generuj PDF</button></a>
<p id="downloadinfo"></p>
</div>
</div>


<%@ include file="footer.jsp" %>
