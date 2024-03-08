package pro.julleon.bank_system.services;

import pro.julleon.bank_system.models.dto.NewClientPayload;
import pro.julleon.bank_system.models.entities.Client;

public interface ClientService {
//    Client createClient(String username, String password, BigDecimal initialBalance, String phone, String email, String fullName, LocalDate birthDate);

//    Client createClient(NewClientPayload clientPayload);

    Client registerNewClient(NewClientPayload clientPayload);
}
