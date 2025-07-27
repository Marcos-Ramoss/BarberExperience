-- Adicionar campo usuario_id na tabela profissionais
ALTER TABLE profissionais 
ADD COLUMN usuario_id BIGINT,
ADD CONSTRAINT fk_profissionais_usuario 
FOREIGN KEY (usuario_id) REFERENCES usuarios(id);

-- Criar índice para melhor performance
CREATE INDEX idx_profissionais_usuario_id ON profissionais(usuario_id); 