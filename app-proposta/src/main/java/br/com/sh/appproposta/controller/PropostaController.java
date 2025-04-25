package br.com.sh.appproposta.controller;

import br.com.sh.appproposta.model.dto.PropostaDTO;
import br.com.sh.appproposta.model.form.PropostaForrm;
import br.com.sh.appproposta.service.PropostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.BindException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/propostas")

public class PropostaController {

    private final PropostaService propostaService;

    public PropostaController(final PropostaService propostaService) {
        this.propostaService = propostaService;
    }

    @Operation(summary = "Cria uma nova proposta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Proposta criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @PostMapping
    public ResponseEntity<PropostaDTO> criarProposta(@RequestBody PropostaForrm form, HttpServletRequest requestservet, HttpServletResponse response) throws BindException {
        PropostaDTO propostaDTO = propostaService.saveProposta(form);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(propostaDTO.getId()).toUri()).body(propostaDTO);
    }

    @Operation(summary = "Busca uma proposta pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proposta encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Proposta não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PropostaDTO> buscarProposta(@PathVariable Long id) {
        return ResponseEntity.ok(propostaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PropostaDTO>> buscarTodasPropostas() {
        return ResponseEntity.ok(propostaService.findAll());
    }
}
