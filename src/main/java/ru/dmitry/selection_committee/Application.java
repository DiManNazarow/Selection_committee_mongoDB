package ru.dmitry.selection_committee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.dmitry.selection_committee.server.ConfigService;
import ru.dmitry.selection_committee.server.models.Admin;
import ru.dmitry.selection_committee.server.services.UserServices;

@SpringBootApplication
@Import({ConfigService.class})
public class Application implements CommandLineRunner {

    @Autowired
    private UserServices userServices;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}
