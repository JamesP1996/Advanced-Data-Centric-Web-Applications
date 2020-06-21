<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error!</title>
</head>
<body>
<form:form modelAttribute="order">
<h1>Error Creating the following Order</h1>
<h2>Quantity too large: Product Stock = ${stock}</h2>
<table>
<tr>
<td><b>Product ID</b></td>
<td><b>Customer ID</b></td>
<td><b>Quantity</b></td>
</tr>

<tr>
<td> ${order.getProd().getpId()} </td>
<td> ${order.getCust().getcId()} </td>
<td> ${order.getQty()} </td>
</tr>

</table>
</form:form>
<div>
		<a href="/">Home</a> 
		<a href="/newOrder.html">New Order</a> 
		<a href="/showOrders.html">List Orders</a> 
  </div>
</body>
</html>