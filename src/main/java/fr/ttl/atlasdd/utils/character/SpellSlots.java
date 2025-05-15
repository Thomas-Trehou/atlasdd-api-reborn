package fr.ttl.atlasdd.utils.character;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class SpellSlots implements Serializable {
    private Map<Integer, Integer> slots = new HashMap<>(); // niveau -> nombre de slots
    private Map<Integer, Integer> slotsUsed = new HashMap<>(); // niveau -> slots utilisés
}

