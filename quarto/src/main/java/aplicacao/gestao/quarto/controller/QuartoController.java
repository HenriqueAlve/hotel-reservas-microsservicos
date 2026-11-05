package aplicacao.gestao.quarto.controller;

import aplicacao.gestao.quarto.model.Quarto;
import aplicacao.gestao.quarto.service.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quarto")
public class QuartoController {

    @Autowired
    private QuartoService service;


    @PostMapping
    public ResponseEntity<Quarto> criar(@RequestBody Quarto camposDadosParaCriar){
        return ResponseEntity.status(HttpStatus.OK).body(service.criar(camposDadosParaCriar));
    }
}
