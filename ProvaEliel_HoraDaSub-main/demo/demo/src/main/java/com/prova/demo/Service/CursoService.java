package com.prova.demo.Service;

import com.prova.demo.Model.Curso;
import com.prova.demo.Repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired; // Faltava isso
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class CursoService {

    @Autowired // Faltava
    private CursoRepository cursoRepository;

    public Curso criarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public List<Curso> listarTodos() {
        return cursoRepository.findAll(); // Corrigido
    }

    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));
    }

    public Curso editarCurso(Long id, Curso cursoDetails) {
        Curso curso = buscarPorId(id);
        curso.setNome(cursoDetails.getNome());
        curso.setCargaHoraria(cursoDetails.getCargaHoraria());
        return cursoRepository.save(curso);
    }

    public void deletarCurso(Long id) {
        Curso curso = buscarPorId(id); // Valida existência
        cursoRepository.delete(curso);
    }
}