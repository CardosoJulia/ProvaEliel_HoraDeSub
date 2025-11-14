package com.prova.demo.Service;

import com.prova.demo.Model.Aluno;
import com.prova.demo.Repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException; // Para erros
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno criarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
    }

    public Aluno editarAluno(Long id, Aluno alunoDetails) {
        Aluno aluno = buscarPorId(id); // Reusa a busca (e a exceção)

        aluno.setNome(alunoDetails.getNome());
        aluno.setMatricula(alunoDetails.getMatricula());
        aluno.setStatus(alunoDetails.getStatus());

        if (alunoDetails.getCurso() != null) { // Lógica simples para atualizar curso
            aluno.setCurso(alunoDetails.getCurso());
        }

        return alunoRepository.save(aluno);
    }

    public void deletarAluno(Long id) {
        Aluno aluno = buscarPorId(id); // Garante que existe antes de deletar
        alunoRepository.delete(aluno);
    }
}