package aplicacao.gestao.reserva.controller;

import aplicacao.gestao.reserva.dto.ReservaRequestDTO;
import aplicacao.gestao.reserva.model.Reserva;
import aplicacao.gestao.reserva.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserva")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService service;

    @PostMapping
    public ResponseEntity<Reserva> criarReserva(@RequestBody ReservaRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(service.criarReserva(requestDTO));
    }
}
