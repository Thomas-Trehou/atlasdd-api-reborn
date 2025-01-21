package fr.ttl.atlasdd.listeners;

import fr.ttl.atlasdd.sqldto.ogl5.ClassSqlDto;
import fr.ttl.atlasdd.sqldto.ogl5.SpellSqlDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpellEntityListener {

    @PersistenceContext
    private EntityManager entityManager;

    @PostPersist
    @PostUpdate
    public void updateClassSpells(SpellSqlDto spell) {
        List<ClassSqlDto> classes = entityManager.createQuery("SELECT c FROM CustomClassSqlDto c", ClassSqlDto.class).getResultList();
        for (ClassSqlDto classDto : classes) {
            if (spell.getClasses().toLowerCase().contains(classDto.getName().toLowerCase())) {
                classDto.getClassSpells().add(spell);
                entityManager.merge(classDto);
            }
        }
    }
}
