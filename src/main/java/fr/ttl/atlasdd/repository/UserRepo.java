package fr.ttl.atlasdd.repository;

import fr.ttl.atlasdd.sqldto.UserSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserSqlDto, Long> {
    Optional<UserSqlDto> findByEmail(String email);
    Optional<UserSqlDto> findBySlug(String slug);
}
