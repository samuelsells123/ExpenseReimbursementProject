<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    	<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<title>ERS Login</title>
	</head>
	
	<br><br><br>
	
	<body>
	  <form name="LoginForm" action="LoginServlet" method="post">
	    <h1 align="center">ERS Login</h1>
	    <table align="center">
	      <tr>
	        <td>Username</td>
	        <td><input type="text" name="username"></td>
	      </tr>
	      <tr>
	        <td>Password</td>
	        <td><input type="password" name="password"></td>
	      </tr>
	      <tr>
	        <td></td>
	        <td><input type="submit" value="submit" id="button" /></td>
	      </tr>
	    </table>
	  </form>
	  
	  <br><br><br>
	  
	  <form name="NewUserForm" action="NewUserServlet" method="post">
	    <h1 align="center">New User?</h1>
	    <table align="center">
	      <tr>
	        <td>Name</td>
	        <td><input type="text" name="name"></td>
	      </tr>
	      <tr>
	        <td>Salary ($)</td>
	        <td><input type="number" step="0.01" name="salary"></td>
	      </tr>
	      <tr>
	        <td>City</td>
	        <td><input type="text" name="city"></td>
	      </tr>
	      <tr>
	        <td>State</td>
	        <td><input type="text" name="state"></td>
	      </tr>
	      <tr>
	        <td>Username</td>
	        <td><input type="text" name="username-create"></td>
	      </tr>
	      <tr>
	        <td>Password</td>
	        <td><input type="password" name="password-create"></td>
	      </tr>
	      <tr>
	        <td></td>
	        <td><input type="submit" value="submit" id="button" /></td>
	      </tr>
	    </table>
	  </form>
	</body>
</html>