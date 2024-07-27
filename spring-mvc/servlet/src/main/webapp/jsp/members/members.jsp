<%--
  <%@ page contentType="text/html;charset=UTF-8" language="java" %> jsp 문서라는 뜻, jsp 문서는 얘가 있어야함
  <% ... %> 이 표시 안에는 자바코드를 입력함
  <%= ... %> 이 표시 안에는 자바코드를 출력함
  http://localhost:8080/jsp/members/new-form.jsp: 실행시 .jsp 까지 함께 써야함
--%>
<%@ page import="mcv.servlet.domain.member.MemberRepository" %>
<%@ page import="mcv.servlet.domain.member.Member" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 이 부분은 자바 코드, 비지니스 로직
    MemberRepository memberRepository = MemberRepository.getInstance();
    List<Member> members = memberRepository.findAll();
%>
<html>
<head>
    <title>JSP 회원목록</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
    <%
        for (Member member : members) {
            out.write(" <tr>");
            out.write("     <td>" + member.getId() + "</td>");
            out.write("     <td>" + member.getUsername() + "</td>");
            out.write("     <td>" + member.getAge() + "</td>");
            out.write(" </tr>");
        }
    %>
    </tbody>
</table>
</body>
</html>
