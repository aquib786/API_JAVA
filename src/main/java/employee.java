import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import net.vz.mongodb.jackson.JacksonDBCollection;
import org.bson.Document;

import java.io.IOException;

public class employee extends Service<EmployeeConfiguration> {


    public void initialize(Bootstrap<EmployeeConfiguration> bootstrap) {

    }

    public void run(EmployeeConfiguration configuration, Environment environment) throws Exception {
        Mongo mongo = new Mongo(configuration.getMongohost(), configuration.getMongoport());
        MongoManaged mongoManaged = new MongoManaged(mongo);
        environment.manage(mongoManaged);

        environment.addHealthCheck(new MongoHealthCheck(mongo));
        DB db = mongo.getDB(configuration.getMongodb());
        JacksonDBCollection<Employee_data,String> data= JacksonDBCollection.wrap(db.getCollection("data"), Employee_data.class, String.class);


        environment.addResource(new Employee_Resource(data));
    }

    public static void main(String[] args) throws Exception {
       new employee().run(args);

    }
}
