<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <div align="center">
  <h1>Login Form</h1>
  <form action="<%=request.getContextPath()%>/login" method="post">
   <table style="with: 100%">
    <tr>
     <td>loginname</td>
     <td><input type="text" name="loginname" /></td>
    </tr>
    <tr>
     <td>passwort</td>
     <td><input type="password" name="passwort" /></td>
    </tr>

   </table>
   <input type="submit" value="Submit" />
  </form>
 </div>
</body>
</html>