package pro.julleon.bank_system.services.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.julleon.bank_system.models.dto.NewClientPayload;
import pro.julleon.bank_system.models.entities.BankAccount;
import pro.julleon.bank_system.models.entities.Client;
import pro.julleon.bank_system.models.entities.Email;
import pro.julleon.bank_system.models.entities.Phone;
import pro.julleon.bank_system.repositories.JpaClientRepository;
import pro.julleon.bank_system.repositories.JpaEmailRepository;
import pro.julleon.bank_system.repositories.JpaPhoneRepository;
import pro.julleon.bank_system.services.ClientService;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    private final JpaClientRepository jpaClientRepository;
    private final JpaPhoneRepository jpaPhoneRepository;
    private final JpaEmailRepository jpaEmailRepository;

    @Autowired
    public ClientServiceImpl(JpaClientRepository jpaClientRepository, JpaPhoneRepository jpaPhoneRepository, JpaEmailRepository jpaEmailRepository) {
        this.jpaClientRepository = jpaClientRepository;
        this.jpaPhoneRepository = jpaPhoneRepository;
        this.jpaEmailRepository = jpaEmailRepository;
    }

    @Override
    public Client registerNewClient(NewClientPayload clientPayload) {

//        List<String> errors = new ArrayList<>();
//        boolean existsByUsername = jpaClientRepository.existsByUsername(clientPayload.username());
//        Set<String> existingPhones = jpaPhoneRepository.findExistingNumbers(clientPayload.phones());
//        Set<String> existingEmails = jpaEmailRepository.findExistingNumbers(clientPayload.emails());
//        if (existsByUsername) {
//            errors.add("Username " + clientPayload.username() + " already taken");
//        }
//        if (!existingPhones.isEmpty()) {
//            errors.add("Phone(s) " + existingPhones + " already taken");
//        }
//        if (!existingEmails.isEmpty()) errors.add("Email(s) " + existingEmails + " already taken");
//        if (!errors.isEmpty()) throw new ClientRegistrationException(errors);

        Client client = new Client();
        client.setUsername(clientPayload.username());
        client.setPassword(clientPayload.password());
        client.setFirstName(clientPayload.firstName());
        client.setMiddleName(clientPayload.middleName());
        client.setSurname(clientPayload.surname());
        client.setDateOfBirth(clientPayload.birthDate());
        Set<Phone> phones = clientPayload.phones().stream().map(s -> {
            Phone phone = new Phone();
            phone.setNumber(s);
            phone.setClient(client);
            return phone;
        }).collect(Collectors.toSet());
        client.setPhones(phones);

        Set<Email> emails = clientPayload.emails().stream().map(s -> {
            Email email = new Email();
            email.setEmail(s);
            email.setClient(client);
            return email;
        }).collect(Collectors.toSet());
        client.setEmails(emails);

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(clientPayload.initialBalance());
        bankAccount.setClient(client);
        client.setBankAccount(bankAccount);

        return jpaClientRepository.save(client);
    }
}

