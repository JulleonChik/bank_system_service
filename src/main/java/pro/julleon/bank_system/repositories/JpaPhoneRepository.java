package pro.julleon.bank_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.julleon.bank_system.models.entities.Phone;

import java.util.List;

@Repository
public interface JpaPhoneRepository extends JpaRepository<Phone, Long> {
    @Query("SELECT p.number FROM Phone p WHERE p.number IN :phones")
    List<String> findExistingNumbers(@Param("phones") List<String> phones);
}
