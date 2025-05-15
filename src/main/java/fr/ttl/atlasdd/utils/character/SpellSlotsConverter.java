package fr.ttl.atlasdd.utils.character;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SpellSlotsConverter implements AttributeConverter<SpellSlots, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(SpellSlots attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erreur lors de la conversion en JSON", e);
        }
    }

    @Override
    public SpellSlots convertToEntityAttribute(String dbData) {
        try {
            if (dbData == null || dbData.isEmpty()) {
                return new SpellSlots();
            }
            return objectMapper.readValue(dbData, SpellSlots.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erreur lors de la conversion depuis JSON", e);
        }
    }
}

