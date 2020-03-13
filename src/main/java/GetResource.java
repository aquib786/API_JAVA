import com.yammer.metrics.annotation.Timed;
import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;
import org.hibernate.validator.constraints.NotBlank;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Path("/")
@Produces(value = MediaType.APPLICATION_JSON)

public class GetResource {


    @NotBlank
    private JacksonDBCollection<Employee_data,String> collection;


    public GetResource(JacksonDBCollection<Employee_data,String> collection){
        this.collection = collection;
    }

    @GET
    @Timed
    public List<Employee_data> index() {
        return Arrays.asList(new Employee_data("Mohammad Aquib" , "22"));

    }

    @GET
    @Path("dummy")
    @Timed

    public List<Employee_data> details(){
        DBCursor<Employee_data> dbCursor = collection.find();
        List<Employee_data> blogs = new ArrayList<Employee_data>();
        while (dbCursor.hasNext()) {
            Employee_data blog = dbCursor.next();

            blogs.add(blog);
        }
        return blogs;

    }
}
