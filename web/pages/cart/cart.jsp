<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%@include file="/pages/common/login_success_menu.jsp"%>
		<script type="text/javascript">
			$(function () {
				$("a.deleteItem").click(function () {
					return confirm("您确定要删除心爱的【"+ $(this).parent().parent().find("td:first").text() +"】吗");
				})
				$("#cart_clear").click(function () {
					return confirm("您确定要删除您的购物车吗");
				})
				//给输入框绑定失去焦点事件
				$(".updateCount").change(function () {
					var name = $(this).parent().parent().find("td:first").text();
					var count = this.value;
					var id = $(this).attr("bookId");
					if(confirm("您确定要将【"+ name +"】 修改数量为【"+count+"】吗")){

						location.href = "http://localhost:8080/book/cartServlet?action=updateCount&count="+count+"&id="+id;
						this.defaultValue = count;

					}
					else {
						//将表单的值设置为默认值
						this.value = this.defaultValue;
					}
				});
			})
		</script>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>

			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td style="color: crimson" colspan="5">当前购物车为空哦！快去挑选喜欢的物品，加入购物车吧！！！</td>
				</tr>
			</c:if>

			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input style="width: 80px" class="updateCount" bookId="${entry.value.id}"type="text" value="${entry.value.count}">
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>


		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="cart_clear" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>