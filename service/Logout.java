package service;

import controller.Flow;
import view.Output;

public class Logout {
    public void logOut() {
        Output.notifyWithPrintln("LOG OUT");
        Flow.startFlow();
    }
}
