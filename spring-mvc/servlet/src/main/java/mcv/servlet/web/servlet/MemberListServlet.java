package mcv.servlet.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mcv.servlet.domain.member.Member;
import mcv.servlet.domain.member.MemberRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * The type Member list servlet.
 * 저장된 모든 회원 목록을 조회
 * http://localhost:8080/servlet/members
 */
@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        List<Member> members = memberRepository.findAll();
        PrintWriter w = response.getWriter();
        w.write("<html>");
        w.write("<head>");
        w.write("   <meta charset=\"UTF-8\">");
        w.write("   <title>Title</title>");
        w.write("</head>");
        w.write("<body>");
        w.write("<a href=\"/index.html\">메인</a>");
        w.write("<table>");
        w.write("   <thead>");
        w.write("       <th>id</th>");
        w.write("       <th>username</th>");
        w.write("       <th>age</th>");
        w.write("   </thead>");
        w.write("   <tbody>");

        for (Member member : members) {
            w.write("       <tr>");
            w.write("           <td>"+ member.getId() +"</td>");
            w.write("           <td>"+ member.getUsername() +"</th>");
            w.write("           <td>"+ member.getAge() +"</td>");
            w.write("       </tr>");
        }

        w.write("   </tbody>");
        w.write(" </table>");
        w.write(" </body>");
        w.write(" </html>");
    }
}
