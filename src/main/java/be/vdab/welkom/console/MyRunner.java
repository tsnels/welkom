package be.vdab.welkom.console;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.swing.plaf.basic.BasicComboBoxUI;
import java.sql.SQLException;

@Component
public class MyRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hallo");
    }
}
