<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    	<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<title>Submit Reimbursement Request</title>
	</head>
	
	<br><br><br>
	
	<body>
	  <form name="SubmitRequest" action="SubmitRequestServlet" method="post">
	    <h1 align="center"></h1>
	    <table align="center">
	      <tr>
	        <td>Amount ($)</td>
	        <td><input type="number" step="0.01" name="amount"></td>
	      </tr>
		  <tr>
	        <td>Description</td>
	        <td> <input type="text" name="description"></td>
	      </tr>
	      <tr>
	        <td></td>
	        <td><input type="submit" value="submit" id="button" /></td>
	      </tr>
	    </table>
	  </form>
	  
	  <br>
	  
	  <form name= "EmployeeReturnForm" action="ReturnEmployeeServlet" method="post">
	    <input type="submit" value="Back to Homepage" name="return-button"/>
	  </form>
	</body>
</html>

