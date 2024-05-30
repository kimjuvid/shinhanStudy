<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<script>
setTimeout(()=> {
	var message = "${resultMessage}";
	  if(message != "") alert(message);
}, 500)

</script>

</head>
<body>
<%-- 	<c:set var="path" value="${pageContext.request.servletContext.contextPath}/board" />
	<c:set var="cpath" value="${pageContext.request.servletContext.contextPath}" /> --%>

		<script src="${cpath}/static/js/jquery-3.7.1.min.js"></script>
		<script>
			$(function(){
				$("#btnJSON").on("click",f_jsonCall);
				$("#btnJSON2").on("click",f_jsonCall2);
			});
			function f_jsonCall2(){
				var output = "<ul>";
				$.ajax({
					url:"${cpath}/json2",
					type:"get",
					success:function(responseData){
						console.log(responseData);
						var obj = JSON.parse(responseData);
						var boardlist = obj["boardlist"];
						$.each(boardlist,function(index,item){
							output += "<li>"+item.title+"</li>"
						});
						output+="</ul>";
						$("#here").html(output);
					},
					error:function(err){
						alert(err);
					}
				});
			}
			function f_jsonCall(){
				var boardObj = {bno:100, title:"커피", content:"제일 맛있는집", writer:"익명"};
				var boardStr = JSON.stringify(boardObj);
				console.log(boardObj);
				console.log(boardObj.title);
				console.log(boardStr);
				
				$.ajax({
					url:"${cpath}/json",
					type:"get",
					data:{"jsonInfo":boardStr},
					success:function(responseData){
						console.log(responseData);
					},
					error:function(errorInfo){
						alert(errorInfo);
					}
				});
				
			}
		</script>
		<button id="btnJSON">JSON보내기</button>
		<button id="btnJSON2">JSON받기</button>
		<div id="here">여기</div>
		
	<%@ include file="../common/header.jsp" %>

	<a href="${path}/board/boardinsert.do">게시판 등록</a>
	<h1>Board 목록</h1>
	
	
	<form action="${path }/board/selectDelete.do">
	<table border="1">
		<tr>
			<th>선택</th>
			<th>bno</th>
			<th>title</th>
			<th>content</th>
			<th>writer</th>
			<th>pic</th>
			<th>작성일</th>
			<th></th>
		</tr>
		<c:forEach var="board" items="${blist}">
			<tr>
				<td>
				  <input type="checkbox" name="checkBno" value="${board.bno }">
				</td>
				<td><a href="${path }/board/boardDetail.do?bno=${board.bno}">${board.bno}</a></td>
				<td>${board.title}</td>
				<td>${board.content}</td>
				<td>${board.writer}</td>
				<td>
					<img alt="${board.title}" width="100" height="100"
					src="${path}/resources/uploads/${board.pic}">	
					
					<%--<a href="${cpath}/download.do?filename=${board.pic}">내려받기</a>
					<c:if test="${board.pic}!=''">
					     <a href="${cpath}/download.do?filename=${board.pic}">내려받기</a>
					</c:if> --%>
				</td>
				<td>${board.create_date}</td>
				<td>
				<input type="button" value="삭제하기" onclick="location.href='${path}/board/boardDelete.do?bno=${board.bno}'"/>
				</td>
				<%-- <td><button onclick="location.href='${path}/board/boardDelete.do?bno=${board.bno}'">삭제</button></td> --%>
			</tr>
		</c:forEach>
	</table>
	<input type="submit" value="선택삭제">
	</form>
</body>
</html>