<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- Stylesheet Test TP-->
<link rel="stylesheet" href="../css/verkaufen.css">
<title>Login</title>
</head>
<body>
    <p class="willkommen"></p>
    <header>
        <div class="center">
            <h1>Login</h1>
        </div>
    </header>

    <main>

        <form action="../LoginServlet" method="post"
            accept-charset="utf-8" enctype="multipart/form-data">

            <div class="verkaufsformular">

                <p>
                    <label for="mail">E-Mail:</label><br> <input type="email"
                        id="mail" name="mail" placeholder="Ihre E-Mail" required />
                </p>

                <p>
                    <label for="Passwort">Passwort:</label><br> <input type="Passwort"
                        id="Passwort" name="Passwort" placeholder="Ihr Passwort" required />
                </p>

                <p>
                    <button type="submit" name="login" value="login">Anmelden</button>
                    <input class="abbrechen" type="button" value="Registrieren"
                        onclick="location.href = 'registrieren.jsp'">
                </p>


            </div>
        </form>




    </main>
    </body>
</html>