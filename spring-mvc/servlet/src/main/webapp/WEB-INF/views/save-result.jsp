<%--
  <%= ... %> 반환타입 Object, ((Member)request.getAttribute("member")).get~~ 사용은 가능함
  ${...} jsp가 제공하는 이 표현식을 사용하면 더 간편
    reqauest.getAttribute로 member 가져옴 -> 프로퍼티 접근법 (setter, getter)으로 가져옴
  view를 출력하기 위해 특화된 로직, 최적화
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
성공
<ul>
  <li>id=${member.id}</li>
  <li>username=${member.username}</li>
  <li>age=${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
