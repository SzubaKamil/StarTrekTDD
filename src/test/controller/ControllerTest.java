package test.controller;

import controller.Controller;

public class ControllerTest {

    private Controller controller;

    @org.junit.Before
    public void setup (){
        controller = new Controller();
    }

    @org.junit.Test
    public void moveEnterprise() {
        controller.moveEnterprise("0,1.0");
        controller.moveEnterprise("");
        controller.moveEnterprise("123");
        controller.moveEnterprise("200,200");
        controller.moveEnterprise("15 12");
        controller.moveEnterprise("abc,abc");
    }

}