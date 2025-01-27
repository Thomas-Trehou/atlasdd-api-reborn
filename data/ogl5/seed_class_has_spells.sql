INSERT INTO ogl5_class_has_spells (class_id, spell_id)
SELECT c.id AS class_id, s.id AS spell_id
FROM ogl5_classes c
         JOIN ogl5_spells s ON s.classes LIKE CONCAT('%', c.name, '%');