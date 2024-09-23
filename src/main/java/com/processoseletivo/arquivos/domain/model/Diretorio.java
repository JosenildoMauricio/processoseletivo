package com.processoseletivo.arquivos.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "diretorio")
public class Diretorio {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(unique=true, nullable=false)
	private String nome;

	@JsonIgnoreProperties({"subDiretorios"})
	@ManyToOne
	@JoinColumn(name = "diretorio_pai_id")
	private Diretorio diretorioPai;

	@JsonIgnoreProperties({"diretorioPai", "subDiretorios"})
	@OneToMany(mappedBy = "diretorioPai", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Diretorio> subDiretorios = new HashSet<>();
	
	@CreationTimestamp
	@Column(name = "data_cadastro")
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(name = "data_atualizacao")
	private OffsetDateTime dataAtualizacao;
	
}
