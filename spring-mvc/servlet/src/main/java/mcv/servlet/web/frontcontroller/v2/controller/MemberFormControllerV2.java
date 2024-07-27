package mcv.servlet.web.frontcontroller.v2.controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mcv.servlet.web.frontcontroller.MyView;
import mcv.servlet.web.frontcontroller.v2.ControllerV2;

import java.io.IOException;

/**
 * The type Member form controller v 2.
 */
public class MemberFormControllerV2 implements ControllerV2 {

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
