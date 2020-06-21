<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Order</title>
</head>
<body>
<form:form modelAttribute="order"  >
  <table >
    <tr>
      <td>Customer:</td>
      <td><form:select path="cust" items="${customerList}" name="cust" ></form:select></td>
    </tr>
    <tr>
      <td>Product:</td>
      <td><form:select path="prod" items="${productList}" name="prod"></form:select></td>
    </tr>
    <tr>
      <td>Quantity:</td>
      <td><form:input path="qty"/></td>
      <td><form:errors path="qty">must be greater than or equal to 1</form:errors></td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="submit" value="Add"/>
      </td>
    </tr>
  </table>
  <div>
		<a href="/">Home</a> 
		<a href="/showCustomers.html">List Customers</a> 
		<a href="/showProducts.html">List Products</a> 
  </div>
</form:form>
</body>
</html>