package com.prova.demo.Model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String matricula;

    @Enumerated(EnumType.STRING) // Boa prática
    private TipoAluno status; // O requisito pedia "status", não "tipoAluno"

    @ManyToOne(fetch = FetchType.LAZY) // LAZY é melhor para performance
    @JoinColumn(name = "curso_id") // Chave estrangeira
    @JsonBackReference // Lado "Filho" da relação (Evita loop)
    private Curso curso;

    public Aluno() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; } // Corrigido de Aluno para Long
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public TipoAluno getStatus() { return status; }
    public void setStatus(TipoAluno status) { this.status = status; }
    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }
}
