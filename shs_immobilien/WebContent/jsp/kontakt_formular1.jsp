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
<br><b>Vorname:</b>${form.vorname}
<br><b>Nachname: </b>${form.nachname}
<br><b>Telefon:</b>${form.telefon}
<br><b>E-Mail: </b>${form.email}
<br><b>Anliegen:</b>${form.anliegen}

<%-- "noch nicht richtig implementiert" <br><b>File:</b>${form.file} --%>

</main>
</body>
</html>