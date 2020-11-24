package fr.demo.models;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder

public class Bugs {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name, description;
}