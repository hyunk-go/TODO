<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TodoList</title>
<link rel="stylesheet" sype="text/css" href="main.css">

</head>
<body>
<header><h1 id="main_title">나의 해야할 일들</h1></header>
<div id="add" onclick="location.href='./TodoFormServlet'">새로운 할일</div>
<section id="out">
<c:forEach var="type" items="${todoTypes}">
<section id="${type}">
<div class="listheader">${type}</div>
<c:forEach var="list" items="${todoList}">
<c:if test="${list.type eq type }">
<div class="item">
<h1>${list.title}</h1>
<div>등록날짜: ${list.regdate},이름:${list.name} 우선순위: ${list.sequence} 
</div>
<c:if test="${list.type ne 'DONE'}">
<button class="alter" onclick="changeButtonClick(${list.id},this)">-></button>
</c:if>
</div>
</c:if>
</c:forEach>
</section>
</c:forEach>
</section>
<script type="text/javascript">
function draw(type,eventNode)
{
	var insertItem=eventNode.parentNode;
	if (type == "TODO")
		type = "DOING";
	else if (type == "DOING") {
		type = "DONE";
		insertItem.removeChild(eventNode);
	}

	var list = document.querySelector("section[id=" + type + "]");

	list.appendChild(insertItem);
}

function changeButtonClick(id,eventNode){
	
	
	var xhr=new XMLHttpRequest();
	var type=eventNode.parentNode.parentNode.getAttribute("id");
	alert("id:");
	xhr.addEventListener("load",function(){
		if(this.responseText === "success")
			draw(type,eventNode);
	});
	xhr.open("GET","./TodoTypeServlet?id="+id+"&type="+type,true);
	xhr.send();
}
</script>

</body>
</html>