package tr.demo.models;

import tr.demo.models.Bugs;
import tr.demo.models.Developers;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentaire {
    private String texte;
    private Bugs bug;
    private Developers developers;
}