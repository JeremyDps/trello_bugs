package tr.demo.models;

import java.util.Date;

//import javax.persistence.Temporal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBugs {
    private String name;
    private String description;
    private String priority;
    private String progress;

    //@Temporal(TemporalType.DATE)
    private Date creation_date;
    private int developers_id;
}