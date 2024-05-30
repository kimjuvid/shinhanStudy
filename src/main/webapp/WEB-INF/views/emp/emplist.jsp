<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<body>
	<%@ include file="../common/header.jsp"%>
	<%-- <c:set var="path" value="${pageContext.servletContext.contextPath }"></c:set> --%>
	<p>${path }</p>

	<a href="${path}/emp/empInsert.do">신규직원등록</a> 
	<form id="conditionForm">
	부서선택 :
	<select id="deptSelect" name="deptSelect">
	<option value="0">전체</option>
	
		<c:forEach var="dept" items="${deptlist }">
			<option ${deptSelect==dept.department_id?"selected":"" } value="${dept.department_id}">${dept.department_name }</option>
		</c:forEach>
	</select>
	
	직책선택 :
	<select id="jobSelect" name="jobSelect">
	<option value="all">전체</option>
	
		<c:forEach var="job" items="${joblist }">
			<option ${jobSelect==job.job_id?"selected":"" } value="${job.job_id}">${job.job_title }</option>
		</c:forEach>
	</select>
	<input type="date" id="hdate" name="hdate" value="${hdate}">
	<input type="number" id="salary" name="salary" value="${salary }">

	</form>
	
	<button onclick="f_condition()">4가지 조건으로 조회</button>
	<button onclick="f_deptSelect()">부서조회</button>
	<button onclick="f_jobSelect()">직책별조회</button>
	<h1>직원 목록</h1>

	<table border="1">
		<thead>
			<tr>
				<th>직원번호</th>
				<th>이름</th>
				<th>성</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>직책</th>
				<th>급여</th>
				<th>부서</th>
				<th>매니저</th>
				<th>커미션</th>
				<th>입사일</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="emp" items="${emplist}">
				<tr>
					<td><a
						href="${path}/emp/empDetail.do?empid=${emp.employee_id }">${emp.employee_id }</a>
					</td>
					<td>${emp.first_name }</td>
					<td>${emp.last_name }</td>
					<td>${emp.email }</td>
					<td>${emp.phone_number }</td>
					<td>${emp.job_id }</td>
					<td>${emp.salary }</td>
					<td>${emp.department_id }</td>
					<td>${emp.manager_id }</td>
					<td>${emp.commission_pct }</td>
					<td>${emp.hire_date }</td>
					<td><button
							onclick="location.href='${path}/emp/empDelete.do?empid=${emp.employee_id}'">삭제</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script>
		function f_condition(){
			// serialize하면 값이 deptSelect=60&jobSelect=IT_PROG&hdate=2006-01-01&salary=100 이런식으로 만들어짐
			var param = $("#conditionForm").serialize();
			// get방식
			location.href = "${path}/emp/empAll2.do?"+param;
			
			/* var deptid = $("#deptSelect").val();
			var jobtid = $("#jobSelect").val();
			var hdate = $("#hdate").val();
			var salary = $("#salary").val();
			
			cosnole.log(deptid);
			cosnole.log(jobtid);
			cosnole.log(hdate);
			cosnole.log(salary);
			
			location.href = "${path}/emp/empAll.do"; */
		}
	
		function f_deptSelect() {
			var deptid = $("#deptSelect").val();
			alert(deptid);
			// 부서아이디를 가지고 가기
			location.href = "${path}/emp/empAll.do?deptid="+deptid;
		}
		
		function f_jobSelect() {
			var jobtid = $("#jobSelect").val();
			alert(deptid);
			// 부서아이디를 가지고 가기
			location.href = "${path}/emp/empAll.do?jobtid="+jobid;
		}
	</script>
</body>
</html>