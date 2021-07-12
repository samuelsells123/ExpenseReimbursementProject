<html>
	<head>
		<meta charset="UTF-8">
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

