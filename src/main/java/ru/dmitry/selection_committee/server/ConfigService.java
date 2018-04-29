package ru.dmitry.selection_committee.server;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import ru.dmitry.selection_committee.server.services.UserServices;
import ru.dmitry.selection_committee.server.services.impl.UserServicesImpl;

public class ConfigService {

    @Bean
    public UserServices userServices(){
        return new UserServicesImpl();
    }

    @Bean
    public MongoClient mongo(){
        return new MongoClient(new MongoClientURI("mongodb://DiManNazarow:4815162342@selectioncommitencluster-shard-00-00-o7ysp.mongodb.net:27017,selectioncommitencluster-shard-00-01-o7ysp.mongodb.net:27017,selectioncommitencluster-shard-00-02-o7ysp.mongodb.net:27017/test?ssl=true&replicaSet=SelectionCommitenCluster-shard-0&authSource=admin"));
    }

}
