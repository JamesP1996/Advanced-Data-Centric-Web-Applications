<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add New Customer</title>
</head>
<body>
<h1>Add New Customer</h1>
<form:form modelAttribute="customer">
  <table>
    <tr>
      <td>Customer Name:</td>
      <td><form:input path="cName"></form:input></td>
      <td><form:errors path="cName">may not be empty</form:errors></td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="submit" value="Add"/>
      </td>
    </tr>
  </table>
  <div>
		<a href="/">Home</a> 
		<a href="/showOrders.html">List Orders</a> 
		<a href="/showProducts.html">List Products</a> 
  </div>
</form:form>
</body>
</html>