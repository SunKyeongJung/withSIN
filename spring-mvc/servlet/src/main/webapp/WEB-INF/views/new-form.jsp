<%--
  * /WEB-INF 아래에 있는 자원들은 외부에서 호출 할 수 없음
    항상 컨트롤러를 거쳐서 내부에서 forward 해야 호출됨
  * redirect vs forward
    리다이렉트는 실제 클라이언트(웹 브라우저)에 응답이 나갔다가, 클라이언트가 redirect 경로로 다시 요청한다.
    따라서 클라이언트가 인지할 수 있고, URL 경로도 실제로 변경된다. 반면에 포워드는 서버 내부에서 일어나는
    호출이기 때문에 클라이언트가 전혀 인지하지 못한다
  http://localhost:8080/servlet-mvc/members/new-form
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Title</title>
</head>
<body>

<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save]
절대경로: /save -> http://localhost:8080/save
상대경로: save -> http://localhost:8080/servlet-mvc/members/save -->
<form action="save" method="post">
  username: <input type="text" name="username" />
  age: <input type="text" name="age" />
  <button type="submit">전송</button>
</form>

</body>
</html>