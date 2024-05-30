<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"
contentType="text/html; charset=utf-8"
 pageEncoding="utf-8"%>

<html>
<head>
	<meta charset="utf-8">
	<title>Home 페이지</title>
</head>
<body>
hello
<h1>이름은 ${myname }</h1>
<h1>myscore는 ${myscore }</h1>
<h2>Get방식 요청(요청 주소에 param을 이용 : 1.값이 동일. 2.field존재, 3. 값이 불일치)</h2>
<form action="${pageContext.servletContext.contextPath }/sample/hello6.do">
  email : <input type="email" name="email" value="kimjh2012@gmail.com"> <br>
  password : <input type="password" name="pwd" value="1234"><br>
  phone : <input type="text" name="phone" value="010-2622-4052">
  <input type="submit" value="서버전송(get)">
</form>

<h2>Post방식요청</h2>
<form
method="post"
 action="${pageContext.servletContext.contextPath }/sample/hello6.do">
  email : <input type="email" name="email" value="kimjh2012@gmail.com"> <br>
  password : <input type="password" name="pwd" value="1234"><br>
  phone : <input type="text" name="phone" value="010-2622-4052">
  <input type="submit" value="서버전송(get)">
</form>

</body>
</html>
