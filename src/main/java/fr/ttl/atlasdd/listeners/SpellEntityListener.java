package fr.ttl.atlasdd.listeners;

import fr.ttl.atlasdd.entity.character.ogl5.Ogl5Class;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5Spell;
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
    public void updateClassSpells(Ogl5Spell spell) {
        List<Ogl5Class> classes = entityManager.createQuery("SELECT c FROM Ogl5Class c", Ogl5Class.class).getResultList();
        for (Ogl5Class classDto : classes) {
            if (spell.getClasses().toLowerCase().contains(classDto.getName().toLowerCase())) {
                classDto.getClassSpells().add(spell);
                entityManager.merge(classDto);
            }
        }
    }
}
