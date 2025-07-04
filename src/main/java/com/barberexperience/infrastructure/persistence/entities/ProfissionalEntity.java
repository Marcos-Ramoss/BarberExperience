package com.barberexperience.infrastructure.persistence.entities;

import com.barberexperience.domain.valueobjects.Especialidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profissionais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfissionalEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome", nullable = false, length = 255)
    private String nome;
    
    @Column(name = "cpf", unique = true, nullable = false, length = 14)
    private String cpf;
    
    @Column(name = "telefone", length = 20)
    private String telefone;
    
    @Column(name = "email", length = 255)
    private String email;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "especialidade", nullable = false)
    private Especialidade especialidade;
    
    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;
    
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;
    
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barbearia_id", nullable = false)
    private BarbeariaEntity barbearia;
    
    @OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AgendamentoEntity> agendamentos = new ArrayList<>();
    
    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
        dataAtualizacao = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }
} 