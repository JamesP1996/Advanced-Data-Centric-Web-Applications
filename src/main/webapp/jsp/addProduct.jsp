<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product</title>
</head>
<body>
<form:form modelAttribute="product">
  <table>
    <tr>
      <td>Product Description:</td>
      <td><form:input path="pDesc"></form:input></td>
      <td><form:errors path="pDesc">may not be empty</form:errors></td>
    </tr>
    <tr>
      <td>Quantity in Stock:</td>
      <td><form:input path="qtyInStock" ></form:input></td>
      <td><form:errors path="qtyInStock">must be greater than or equal to 0</form:errors></td>
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