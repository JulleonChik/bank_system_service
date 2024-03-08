package pro.julleon.bank_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.julleon.bank_system.models.entities.Email;

import java.util.List;

@Repository
public interface JpaEmailRepository extends JpaRepository<Email, Long> {

    @Query("SELECT e.email FROM Email e WHERE e.email IN :emails")
    List<String> findExistingEmails(@Param("emails") List<String> emails);
}
