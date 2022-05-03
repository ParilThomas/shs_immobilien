<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kontkatformular</title>
</head>
<body>
<header>
<h1>Kontaktformularausgabe</h1>
</header>
<main>
<h2>Ihre Formulareingaben:</h2>
<br><b>Vorname:</b>${form1.vorname}
<br><b>Nachname: </b>${form1.nachname}
<br><b>Telefon:</b>${form1.telefon}
<br><b>E-Mail: </b>${form1.email}
<br><b>Anliegen:</b>${form1.anliegen}
<br><b>Bild:</b><img src="../kaufen_bild_servlet?id=${form1.id}">


</main>
</body>
</html>