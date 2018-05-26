package ru.dmitry.selection_committee.server;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import ru.dmitry.selection_committee.server.services.*;
import ru.dmitry.selection_committee.server.services.impl.*;

/**
 * Класс для настройки доступа к БД и указания всех кнтроллеров доступа к БД
 */
public class ConfigService {

    @Bean
    public UserServices userServices(){
        return new UserServicesImpl();
    }

    @Bean
    public InstitutionService institutionService(){
        return new InstitutionServiceImpl();
    }

    @Bean
    public DepartmentService departmentService(){
        return new DepartmentServiceImpl();
    }

    @Bean
    public PulpitService pulpitService(){
        return new PulpitServiceImpl();
    }

    @Bean
    public SpecialityService specialityService(){
        return new SpecialityServiceImpl();
    }

    @Bean
    public MongoClient mongo(){
        return new MongoClient(new MongoClientURI("mongodb://user:password@selectioncommitencluster-shard-00-00-o7ysp.mongodb.net:27017,selectioncommitencluster-shard-00-01-o7ysp.mongodb.net:27017,selectioncommitencluster-shard-00-02-o7ysp.mongodb.net:27017/test?ssl=true&replicaSet=SelectionCommitenCluster-shard-0&authSource=admin"));
    }

}
