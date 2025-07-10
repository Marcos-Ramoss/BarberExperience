package com.barberexperience.infrastructure.persistence.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.barberexperience.domain.valueobjects.Endereco;
import com.barberexperience.domain.valueobjects.HorarioFuncionamento;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "barbearias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BarbeariaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome", nullable = false, length = 255)
    private String nome;
    
    @Column(name = "cnpj", unique = true, nullable = false, length = 18)
    private String cnpj;
    
    @Column(name = "telefone", length = 20)
    private String telefone;
    
    @Column(name = "email", length = 255)
    private String email;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "rua", column = @Column(name = "logradouro")),
        @AttributeOverride(name = "numero", column = @Column(name = "numero")),
        @AttributeOverride(name = "bairro", column = @Column(name = "bairro")),
        @AttributeOverride(name = "cidade", column = @Column(name = "cidade")),
        @AttributeOverride(name = "estado", column = @Column(name = "estado")),
        @AttributeOverride(name = "cep", column = @Column(name = "cep"))
    })
    private Endereco endereco;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "abertura", column = @Column(name = "hora_abertura")),
        @AttributeOverride(name = "fechamento", column = @Column(name = "hora_fechamento"))
    })
    private HorarioFuncionamento horarioFuncionamento;
    
    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;
    
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;
    
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    
    @OneToMany(mappedBy = "barbearia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProfissionalEntity> profissionais = new ArrayList<>();
    
    @OneToMany(mappedBy = "barbearia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ServicoEntity> servicos = new ArrayList<>();
    
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