package mcv.servlet.web.frontcontroller.v4.controller;

import mcv.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

/**
 * The type Member form controller v 4.
 */
public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form";
    }
}
