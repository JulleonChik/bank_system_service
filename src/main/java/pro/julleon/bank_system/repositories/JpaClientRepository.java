package pro.julleon.bank_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.julleon.bank_system.models.entities.Client;

@Repository
public interface JpaClientRepository extends JpaRepository<Client, Long> {
    boolean existsByUsername(String userName);
}

