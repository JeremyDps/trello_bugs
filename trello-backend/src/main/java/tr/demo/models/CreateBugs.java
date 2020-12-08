package tr.demo.models;

import java.util.Date;

//import javax.persistence.Temporal;
import tr.demo.enums.Bugs_priority;
import tr.demo.enums.Bugs_progress;

import tr.demo.models.Developers;

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
    private Bugs_priority priority;
    private Bugs_progress progress;

    //@Temporal(TemporalType.DATE)
    private Date creation_date;
    private Developers developers;
}