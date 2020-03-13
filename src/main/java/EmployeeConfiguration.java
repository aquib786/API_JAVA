import com.yammer.dropwizard.config.Configuration;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class EmployeeConfiguration  extends Configuration {

    @JsonProperty
    @NotEmpty
    private String mongohost = "localhost";

    @JsonProperty
    @Min(1)
    @Max(65535)
    private int mongoport = 27017;

    @JsonProperty
    @NotEmpty
    private String mongodb = "demo004";


    public String getMongodb() {
        return mongodb;
    }

    public int getMongoport() {
        return mongoport;
    }

    public String getMongohost() {
        return mongohost;
    }


}
