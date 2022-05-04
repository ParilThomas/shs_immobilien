<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kaufübersicht</title>
</head>
<body>
<header>
<h1>Kaufübersicht</h1>
</header>
<main>
		
		<b>Haustyp:</b>${kform.haustyp} <br>
		<b>Startgebot: </b>${kform.startgebot} <br>
		<b>Bild:</b><img src="../kaufen_bild_servlet?haustyp=${kform.haustyp}"> 
	</main>

</body>
</html>