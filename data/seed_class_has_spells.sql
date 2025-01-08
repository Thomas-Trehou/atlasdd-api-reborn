INSERT INTO class_has_spells (class_id, spell_id)
SELECT c.id AS class_id, s.id AS spell_id
FROM classes c
         JOIN spells s ON s.classes LIKE CONCAT('%', c.name, '%');