<!-- @author Simon Schrödl -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="immo.portal.servlets.SuchenServlet"%>
<!DOCTYPE html>
<html lang="de">

<head>
	<!-- Verwendete CSS Imports -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="../css/startseite.css">
	<link rel="stylesheet" href="../css/dropdownNavBar.css">
	<link rel="stylesheet" href="../css/hauptbild.css">
	<link rel="stylesheet" href="../css/footer.css">

	<!-- Homepage Titel -->
	<title>sps-immobilien.de/Startseite</title>
</head>


<body>
	<header>
		<!-- Einbindung der Navigationsleiste -->
		<%@ include file="../jspf/navBarHauptbild.jspf"%>
	</header>

<main>
	<div class="kopfbereich">
		<!-- Such Form auf der Startseite zum Suchen von Objekten aller Art in der Datenbank -->
		<form action="../SuchenServlet" method="post">
			<div class="suchen">
				<table>
					<tr>
						<td><h3>Finden Sie Ihr Wunschobjekt</h3></td>
					</tr>
	
					<tr>
						<td><div class="textfeld">
							<input type="text" id="suchvar" name="suchvar" placeholder="Search..." />
								<div class="icon">
									<i class="fa fa-search"></i>
								</div>
							</div>
						</td>
					</tr>
					
					<tr>
						<td>
							<button class="suchenbutton" type="submit" name="suchen_absenden" value="absenden">Suchen</button>
						</td>
					</tr>
				</table>
			</div>
		</form>

	<section>
		<hr class="trennung">
			<h1>Erfahren Sie mehr über uns</h1>
			<h2>IMMOBILIEN, WOHNUNGEN &amp; HÄUSER KAUFEN</h2>
			<p class="infotext">
				SPS Immobilien ist das führende Dienstleistungsunternehmen <br>
				in Deutschland bei der Vermittlung von Wohn- und Gewerbeimmobilien.<br>
				Das Unternehmen bietet privaten und	institutionellen Kunden<br>
				eine professionell abgestimmte Dienstleistungspalette.
			</p>
			<hr class="trennung">
	</section>
</div>
</main>

<!-- Einbindung der Fußzeile -->
<%@ include file="../jspf/fußzeile.jspf"%>
</body>
</html>