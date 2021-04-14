package io.github.gabrielrleal.projetodsc.controller;

import io.github.gabrielrleal.projetodsc.exception.ResourceNotFoundException;
import io.github.gabrielrleal.projetodsc.model.Disciplina;
import io.github.gabrielrleal.projetodsc.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinaRepository;
    //get todas disciplinas
    @GetMapping("disciplinas")
    public List<Disciplina> getAllDisciplina(){
        return this.disciplinaRepository.findAll();
    }
    //get disciplina por id
    @GetMapping("/disciplinas/{id}")
    public ResponseEntity<Disciplina> getDisciplinaById(@PathVariable(value = "id") Long disciplinaId)
            throws ResourceNotFoundException {
            Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                    .orElseThrow(() -> new ResourceNotFoundException("disciplina nao encontrada para esse id:"+disciplinaId));
            return ResponseEntity.ok().body(disciplina);
    }
    //post disciplina
    @PostMapping("disciplinas")
    public Disciplina createDisciplina(@RequestBody Disciplina disciplina){
        return this.disciplinaRepository.save(disciplina);
    }
    //update disciplina
    @PutMapping("/disciplinas/{id}")
    public ResponseEntity<Disciplina> updateDisciplina(@PathVariable(value = "id") Long disciplinaId,
       @Validated @RequestBody Disciplina disciplinaDetalhada) throws ResourceNotFoundException{
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(()-> new ResourceNotFoundException("Disciplina nao encontrada para o id:"+disciplinaId));
        disciplina.setNome(disciplinaDetalhada.getNome());
        disciplina.setNota(disciplinaDetalhada.getNota());

        return ResponseEntity.ok(this.disciplinaRepository.save(disciplina));
    }

    //deletar disciplina

    public Map<String, Boolean> deleteDisciplina(@PathVariable(value = "id") Long disciplinaId) throws ResourceNotFoundException{
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(()-> new ResourceNotFoundException("disciplina nao encontrada para o id"+disciplinaId));
        this.disciplinaRepository.delete(disciplina);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return  response;
    }




}
