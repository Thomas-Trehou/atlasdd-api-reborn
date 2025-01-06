package fr.ttl.atlasdd.apidto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SpellApiDto extends BaseApiDto{

    private String name;
    private String description;
    private String range;
    private String components;
    private String material;
    private String ritual;
    private String duration;
    private String concentration;
    private String castingTime;
    private String level;
    private String school;
    private String classes;
    private String higherLevel;
    private String archetype;
    private String domains;
    private String oaths;
    private String circles;
    private String patrons;
}
