package fr.ttl.atlasdd.apidto;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseApiDto {

    private long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
