<!DOCTYPE html>
<html lang="de">

<head>
<meta charset="UTF-8">

<!-- Stylesheet Test TP-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/startseite.css">
<link rel="stylesheet" href="../css/dropdownNavBar.css">


<title>sps-immobilien.de/home</title>
</head>


<body>
	<header>
		<p class="willkommen"></p>
		<%@ include file="../jspf/allgbutton.jspf"%>
	</header>
	
<main>
<div class="header">
<form class="ansicht">
<div class="suchen">
<table>
<tr>
<td><h3>Finden Sie Ihr Wunschobjekt</h3><td>
</tr>
<tr>
<td><div class="textfeld">
<input type="text" id="search" name="search" placeholder="Search..." />
 <div class="icon"><i class="fa fa-search"></i></div>
</div></td>
</tr>
<tr><td><button class="suchenbutton" type="submit" name="suchen_absenden" value="absenden">Suchen</button></td></tr>
</table>
</div>
</form>



<section>
<hr class="trennung">
<h2>Erfahren Sie mehr über uns</h2>
<h1>IMMOBILIEN, WOHNUNGEN &amp; HÄUSER KAUFEN</h1>
<p class="infotext">
SPS Immobilien ist ein weltweit führendes Dienstleistungsunternehmen <br>
in der Vermittlung von Wohn und Gewerbeimmobilien.<br>
Das Unternehmen bietet privaten und institutionellen Kunden <br>
eine professionell abgestimmte Dienstleistungspalette.
</p>
<hr class="trennung">
</section>
</div>



<footer>
<i class="fa fa-building-o">&nbsp;SPS-Immobilien |</i>
<i class="fa fa-map-marker">&nbsp; Baumweg 10 - 85296 Rohrbach |</i>
<i class="fa fa-phone">&nbsp; +49-8442-4563-0 |</i>   
<i 	class="fa fa-envelope-o">&nbsp; contact@sps.com |</i> 
<i class="fa fa-fax">&nbsp; +49-8442-4563-4 </i>
</footer>



</main>
</body>
</html>