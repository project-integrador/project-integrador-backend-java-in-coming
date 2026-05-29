USE projectintegrador;
DROP DATABASE projectintegrador;

SELECT * FROM aulas;
SELECT * FROM coordenadores;
SELECT * FROM professores;
SELECT * FROM solicitacoes_ausencia;
SELECT * FROM substituicoes;

USE projectintegrador;

SET FOREIGN_KEY_CHECKS = 0;

SET SQL_SAFE_UPDATES = 0;
	
DELETE FROM substituicoes;
DELETE FROM solicitacoes_ausencia;
DELETE FROM aulas;
DELETE FROM professores;
DELETE FROM coordenadores;

SET FOREIGN_KEY_CHECKS = 1;

ALTER TABLE substituicoes AUTO_INCREMENT = 1;
ALTER TABLE solicitacoes_ausencia AUTO_INCREMENT = 1;
ALTER TABLE aulas AUTO_INCREMENT = 1;
ALTER TABLE professores AUTO_INCREMENT = 1;
ALTER TABLE coordenadores AUTO_INCREMENT = 1;
-- =========================================
-- INSERTS PROFESSORES
-- =========================================

INSERT INTO professores 
(nome, email, password, matricula, telefone, area_atuacao, ativo)
VALUES
('Carlos Silva', '  ', '123456', 'PROF001', '17999990001', 'Tecnologia da Informação', true),

('Fernanda Souza', 'fernanda.souza@senai.com', '123456', 'PROF002', '17999990002', 'Matemática', true),

('Ricardo Lima', 'ricardo.lima@senai.com', '123456', 'PROF003', '17999990003', 'Administração', true),

('Juliana Alves', 'juliana.alves@senai.com', '123456', 'PROF004', '17999990004', 'Banco de Dados', true),

('Marcos Pereira', 'marcos.pereira@senai.com', '123456', 'PROF005', '17999990005', 'Redes', true);



-- =========================================
-- INSERTS COORDENADORES
-- =========================================

INSERT INTO coordenadores
(nome, email, password, matricula, telefone, ativo)
VALUES
('Roberto Mendes', 'roberto.mendes@senai.com', '123456', 'COORD001', '17988880001', true),

('Patricia Gomes', 'patricia.gomes@senai.com', '123456', 'COORD002', '17988880002', true);



-- =========================================
-- INSERTS AULAS
-- professor_id precisa existir
-- =========================================

INSERT INTO aulas
(nome_disciplina, carga_horaria, professor_id)
VALUES
('Programação Java', 80, 1),

('Banco de Dados MySQL', 60, 4),

('Matemática Aplicada', 40, 2),

('Redes de Computadores', 50, 5),

('Gestão Empresarial', 45, 3),

('Spring Boot', 70, 1),

('Lógica de Programação', 90, 2);



-- =========================================
-- INSERTS SOLICITACOES_AUSENCIA
-- aula_id e professor_id precisam existir
-- =========================================

INSERT INTO solicitacoes_ausencia
(data_ausencia, motivo, status, aula_id, professor_id)
VALUES
('2026-05-20',
 'Consulta médica',
 'PENDENTE',
 1,
 1),

('2026-05-22',
 'Problemas pessoais',
 'APROVADA',
 2,
 4),

('2026-05-25',
 'Curso de capacitação',
 'PENDENTE',
 4,
 5),

('2026-05-27',
 'Viagem acadêmica',
 'REJEITADA',
 5,
 3);



-- =========================================
-- INSERTS SUBSTITUICOES
-- coordenador_id, professor_substituto_id
-- e solicitacao_ausencia_id precisam existir
-- =========================================

INSERT INTO substituicoes
(coordenador_id, professor_substituto_id, solicitacao_ausencia_id)
VALUES
(1, 2, 2),

(2, 1, 3);
