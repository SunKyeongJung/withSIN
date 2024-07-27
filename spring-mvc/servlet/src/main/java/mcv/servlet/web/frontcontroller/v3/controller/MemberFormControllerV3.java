package mcv.servlet.web.frontcontroller.v3.controller;

import mcv.servlet.web.frontcontroller.ModelView;
import mcv.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

/**
 * The type Member form controller v 3.
 */
public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
