package ru.dmitry.selection_committee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.dmitry.selection_committee.server.ConfigService;

/**
 * Главный класс приложения
 */
@SpringBootApplication
@Import({ConfigService.class})
public class Application implements CommandLineRunner {

    /**
     * Главный метод приложения
     * @param args масси в параметров
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}
