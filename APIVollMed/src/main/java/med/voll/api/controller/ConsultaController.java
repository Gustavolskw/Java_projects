package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;
import med.voll.api.domain.medicos.DadosDetalhadosMedico;
import med.voll.api.domain.medicos.DadosListagemMedicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {
    @Autowired
    private AgendaDeConsultas agenda;
    @Autowired
    private ConsultaRepository consultaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity agendar (@RequestBody @Valid DadosAgendamento dadosAgendamento){
       var dto =  agenda.agendar(dadosAgendamento);
        System.out.println(dadosAgendamento);
        return ResponseEntity.ok(new DadosDetalhamentoConsultas(dto.id(), dto.idMedico(), dto.idPaciente(), dto.data(),dto.especialidade()));

    }
    @GetMapping
    public ResponseEntity <Page<DadosListagemConsulta>>listarConsultas(@PageableDefault(size =10) Pageable paginacao){
        var page =  consultaRepository.findAllByCanceladaFalse(paginacao).map(DadosListagemConsulta::new);
        return ResponseEntity.ok(page);
    }
    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar (@RequestBody @Valid DadosCancelamentoConsulta dados){
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();

    }
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
       var consultas = consultaRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoConsultas(consultas));

    }








}
