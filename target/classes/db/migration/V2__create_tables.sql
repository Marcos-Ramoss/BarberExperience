-- Tabela de barbearias
CREATE TABLE IF NOT EXISTS barbearias (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cnpj VARCHAR(18) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(255),
    logradouro VARCHAR(255),
    numero VARCHAR(20),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado VARCHAR(2),
    cep VARCHAR(10),
    hora_abertura TIME,
    hora_fechamento TIME,
    dias_funcionamento VARCHAR(100),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    data_criacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabela de profissionais
CREATE TABLE IF NOT EXISTS profissionais (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(255),
    barbearia_id BIGINT NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    data_criacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (barbearia_id) REFERENCES barbearias(id)
);

-- Tabela de associação para especialidades do profissional
CREATE TABLE IF NOT EXISTS profissional_especialidades (
    profissional_id BIGINT NOT NULL,
    especialidade VARCHAR(255) NOT NULL,
    PRIMARY KEY (profissional_id, especialidade),
    FOREIGN KEY (profissional_id) REFERENCES profissionais(id)
);

-- Remover coluna antiga, se existir (ajuste conforme seu banco)
-- ALTER TABLE profissionais DROP COLUMN IF EXISTS especialidade;

-- Tabela de clientes
CREATE TABLE IF NOT EXISTS clientes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(255) UNIQUE NOT NULL,
    data_nascimento DATE,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    data_criacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabela de serviços
CREATE TABLE IF NOT EXISTS servicos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10,2) NOT NULL,
    duracao_minutos INT NOT NULL,
    barbearia_id BIGINT NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    data_criacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (barbearia_id) REFERENCES barbearias(id)
);

-- Tabela de agendamentos
CREATE TABLE IF NOT EXISTS agendamentos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cliente_id BIGINT NOT NULL,
    profissional_id BIGINT NOT NULL,
    barbearia_id BIGINT NOT NULL,
    data_hora DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDENTE',
    observacoes TEXT,
    data_criacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (profissional_id) REFERENCES profissionais(id),
    FOREIGN KEY (barbearia_id) REFERENCES barbearias(id)
);

-- Tabela de relacionamento agendamento_servicos
CREATE TABLE IF NOT EXISTS agendamento_servicos (
    agendamento_id BIGINT NOT NULL,
    servico_id BIGINT NOT NULL,
    PRIMARY KEY (agendamento_id, servico_id),
    FOREIGN KEY (agendamento_id) REFERENCES agendamentos(id),
    FOREIGN KEY (servico_id) REFERENCES servicos(id)
);

-- Índices para melhor performance
CREATE INDEX idx_barbearias_cnpj ON barbearias(cnpj);
CREATE INDEX idx_profissionais_cpf ON profissionais(cpf);
CREATE INDEX idx_profissionais_barbearia ON profissionais(barbearia_id);
CREATE INDEX idx_clientes_cpf ON clientes(cpf);
CREATE INDEX idx_clientes_email ON clientes(email);
CREATE INDEX idx_servicos_barbearia ON servicos(barbearia_id);
CREATE INDEX idx_agendamentos_cliente ON agendamentos(cliente_id);
CREATE INDEX idx_agendamentos_profissional ON agendamentos(profissional_id);
CREATE INDEX idx_agendamentos_barbearia ON agendamentos(barbearia_id);
CREATE INDEX idx_agendamentos_data_hora ON agendamentos(data_hora);
CREATE INDEX idx_agendamentos_status ON agendamentos(status); 