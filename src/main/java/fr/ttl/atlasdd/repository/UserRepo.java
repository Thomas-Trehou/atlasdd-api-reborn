package fr.ttl.atlasdd.repository;

import fr.ttl.atlasdd.sqldto.UserSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserSqlDto, Long> {
    UserSqlDto findByEmail(String email);
    UserSqlDto findBySlug(String slug);
    UserSqlDto findByPseudo(String pseudo);
}
