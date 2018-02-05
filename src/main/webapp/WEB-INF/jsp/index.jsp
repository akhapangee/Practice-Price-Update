<%@include file="_includes/header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    function disable1() {
        if (document.getElementById("percent").checked) {
            document.getElementById("amount").checked = false;
        } else {
            document.getElementById("amount").checked = false;
        }
    }
    function disable2() {
        if (document.getElementById("amount").checked) {
            document.getElementById("percent").checked = false;
        } else {
            document.getElementById("percent").checked = true;
        }
    }
    function btnUpdate() {
        var strconfirm = confirm("Update price?");
        if (strconfirm) {
            return true;
        }
    }
</script>
<h1><a href="${pageContext.request.contextPath}/home">Welcome Home</a></h1>
<div style="margin-bottom: 10px;">
    <form method="POST" action="" onsubmit="btnUpdate()">
        <label>Category Id</label>
        <select name="category_choice" onchange="alert('hello')" >
            <!--<option value ="0" selected="selected" >--select category--</option>-->
            <c:forEach var="category" items="${requestScope.categories}">
                <option value="${category.id}" type="submit">${category.categoryName}</option>
            </c:forEach>
        </select>
        <select name="updateOption">
            <option value ="increment" selected="selected" >Increase by</option>
            <option value="decrement">Decrease by</option>
        </select>
        <input type="text" pattern="\d+" size="35" name="updateValue" required="required" autofocus/>
        <input type="checkbox" name="checkBoxValue" value="percent" id="percent" onclick="disable1()" >Percent
        <input type="checkbox" name="checkBoxValue" value="amount" id="amount" onclick="disable2()" checked>Amount
        <button type="submit" value="Submit" >Update Price</button>
    </form>
</div>
<table border="1" width = "100%" style="text-align: left;">
    <tr>
        <th>Product Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Category</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Status</th>
    </tr>
    <c:forEach var="product" items="${requestScope.products}">
        <tr>
            <td style="width:10%;">${product.productId}</td>
            <td>${product.productName}</td>
            <td style="width:50%">${product.description}</td>
            <td>${product.categoryName}</td>
            <td>${product.price}</td>
            <td>${product.quantity}</td>
            <td>${product.status}</td>
        </tr>
    </c:forEach>
</table>
<%@include file="_includes/footer.jsp" %>