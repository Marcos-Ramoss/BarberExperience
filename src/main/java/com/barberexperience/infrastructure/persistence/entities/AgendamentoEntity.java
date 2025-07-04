package com.barberexperience.infrastructure.persistence.entities;

import com.barberexperience.domain.valueobjects.StatusAgendamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;
    
    @Column(name = "duracao_minutos", nullable = false)
    private Integer duracaoMinutos;
    
    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusAgendamento status;
    
    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String observacoes;
    
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;
    
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barbearia_id", nullable = false)
    private BarbeariaEntity barbearia;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profissional_id", nullable = false)
    private ProfissionalEntity profissional;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servico_id", nullable = false)
    private ServicoEntity servico;
    
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