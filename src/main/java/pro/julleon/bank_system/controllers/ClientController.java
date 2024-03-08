package pro.julleon.bank_system.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.julleon.bank_system.models.dto.NewClientPayload;
import pro.julleon.bank_system.models.entities.Client;
import pro.julleon.bank_system.services.ClientService;
@Validated
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerNewClient(@Valid @RequestBody NewClientPayload clientPayload) {
        // Если нет ошибок валидации, регистрируем клиента
        Client client = clientService.registerNewClient(clientPayload);
        return ResponseEntity.ok(client);
    }


//    @PostMapping("/register")
//    public ResponseEntity<?> registerNewClient(@Valid @RequestBody NewClientPayload clientPayload,
//                                               BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            // Если есть ошибки валидации, формируем список ошибок и возвращаем ResponseEntity с ошибками
//            List<String> errors = bindingResult.getAllErrors().stream()
//                    .map(error -> messageSource.getMessage(Objects.requireNonNull(error.getDefaultMessage()), null, Locale.getDefault()))
//                    .collect(Collectors.toList());
//            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, errors);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
//        }
//        // Если нет ошибок валидации, регистрируем клиента
//        Client client = clientService.registerNewClient(clientPayload);
//        return ResponseEntity.ok(client);
//    }
}