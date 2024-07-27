package mcv.servlet.web.frontcontroller.v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mcv.servlet.web.frontcontroller.MyView;

import java.io.IOException;

/**
 * The interface Controller v 2.
 * view 분리 (MyView)
 */
public interface ControllerV2 {

    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
