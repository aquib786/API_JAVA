import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yammer.metrics.annotation.Timed;
import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("/")
@Produces(value = MediaType.APPLICATION_JSON)

public class Employee_Resource{



    @NotBlank
    private JacksonDBCollection<Employee_data,String> collection;

    public Employee_Resource(){

    }
    public Employee_Resource(JacksonDBCollection<Employee_data,String> collection){
        this.collection = collection;
    }

    @POST
    @Path("post")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Timed

    public Response publishNewDocument(@Valid Employee_data data) {

        collection.insert(data);
        return Response.ok(data).build();
    }
    @GET
    @Timed
    public List<Employee_data> index() {
        //return Arrays.asList(new Employee_data("Mohammad Aquib" , "22"));
        DBCursor<Employee_data> dbCursor = collection.find();
        List<Employee_data> blogs = new ArrayList<Employee_data>();
        while (dbCursor.hasNext()) {
            Employee_data blog = dbCursor.next();

            blogs.add(blog);
        }
        return blogs;
    }
}
