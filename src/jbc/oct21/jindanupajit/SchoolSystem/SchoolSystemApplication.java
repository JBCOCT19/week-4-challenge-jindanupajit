package jbc.oct21.jindanupajit.SchoolSystem;

import jbc.oct21.jindanupajit.SchoolSystem.control.TelnetController;
import jbc.oct21.jindanupajit.SchoolSystem.control.TerminalController;
import jbc.oct21.jindanupajit.SchoolSystem.view.Terminal;

public class SchoolSystemApplication {
    public static void main(String[] args) {
        Thread console = new Thread(new TerminalController(new Terminal( )));
        console.setName("Console");
        console.start();

        Thread telnet = new Thread(new TelnetController());
        telnet.setName("Telnet");
        telnet.start();
    }
}
