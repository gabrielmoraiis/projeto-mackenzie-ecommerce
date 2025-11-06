INSERT INTO categoria (nome)
VALUES ('Home Spray');
INSERT INTO categoria (nome)
VALUES ('Difusores');
INSERT INTO categoria (nome)
VALUES ('Sabonetes');
INSERT INTO categoria (nome)
VALUES ('Refis');
INSERT INTO categoria (nome)
VALUES ('Spray Automotivo');
INSERT INTO categoria (nome)
VALUES ('Óleo Espumante Corporal');
INSERT INTO categoria (nome)
VALUES ('Perfume');

INSERT INTO essencia (nome)
VALUES ('Lavanda Francesa');
INSERT INTO essencia (nome)
VALUES ('Capim-Limão');
INSERT INTO essencia (nome)
VALUES ('Alecrim Silvestre');

INSERT INTO produto (nome, descricao, preco, url_imagem, categoria_id)
VALUES ('Home Spray Clássico', 'Perfeito para perfumar o ambiente de forma suave.', 49.90, 'url/imagem/spray1.jpg', 1),
       ('Difusor de Varetas Elegance', 'Design sofisticado com aroma duradouro.', 89.90, 'url/imagem/difusor1.jpg', 2),
       ('Sabonete Líquido Hidratante', 'Limpa e hidrata a pele.', 35.50, 'url/imagem/sabonete1.jpg', 3);

INSERT INTO produto (nome, descricao, preco, url_imagem, categoria_id)
VALUES
    ('Refil Home Spray Capim-Limão', 'Refil econômico de 250ml para seu Home Spray.', 39.90, 'url/imagem/refil_cl.jpg', 4),
    ('Spray Automotivo Alecrim', 'Aroma herbal para renovar o ar do seu veículo.', 29.90, 'url/imagem/spray_carro1.jpg', 5),
    ('Perfume Unissex Lavanda & Alecrim', 'Fragrância sofisticada que combina o relaxamento da lavanda com a energia do alecrim.', 149.90, 'url/imagem/perfume1.jpg', 7);
INSERT INTO produto_essencias (produto_id, essencia_id)
VALUES (4, 2);
INSERT INTO produto_essencias (produto_id, essencia_id)
VALUES (5, 3);
INSERT INTO produto_essencias (produto_id, essencia_id)
VALUES (6, 1);
INSERT INTO produto_essencias (produto_id, essencia_id)
VALUES (6, 3);
INSERT INTO produto_essencias (produto_id, essencia_id)
VALUES (1, 1);
INSERT INTO produto_essencias (produto_id, essencia_id)
VALUES (1, 2);

INSERT INTO produto_essencias (produto_id, essencia_id)
VALUES (2, 3);

INSERT INTO produto_essencias (produto_id, essencia_id)
VALUES (3, 1);

INSERT INTO opcao_adicional (nome, preco_adicional) VALUES ('Kit de Vareta (5 un.)', 10.00);
INSERT INTO opcao_adicional (nome, preco_adicional) VALUES ('Embalagem para Presente', 5.50);
INSERT INTO produto_opcoes_adicionais (produto_id, opcao_adicional_id) VALUES (2, 1);
INSERT INTO produto_opcoes_adicionais (produto_id, opcao_adicional_id) VALUES (2, 2);
INSERT INTO produto_opcoes_adicionais (produto_id, opcao_adicional_id) VALUES (3, 2);


INSERT INTO cliente (nome_completo, email, whatsapp, senha, role)
VALUES ('Administrador', 'admin@email.com', '11000000000',
        '$2a$10$4THJYH7TdQao4ZDCVtDk1uq8IY0hJIfhJWi6mPiSkhuoi/FLgEQQe',
        'ROLE_ADMIN');

INSERT INTO endereco (id, rua, numero, bairro, cidade, estado, cep)
VALUES (1, 'Rua Exemplo Fictício', '123', 'Centro', 'São Paulo', 'SP', '01000-000');

INSERT INTO pedido (cliente_id, endereco_id, status, total_pedido, data_criacao)
VALUES (1, 1, 'PAGAMENTO_CONFIRMADO', 150.75, '2025-11-05T10:00:00');

INSERT INTO pedido (cliente_id, endereco_id, status, total_pedido, data_criacao)
VALUES (1, 1, 'EM_PRODUCAO', 210.00, '2025-11-06T17:00:00');

INSERT INTO pedido (cliente_id, endereco_id, status, total_pedido, data_criacao)
VALUES (1, 1, 'AGUARDANDO_PAGAMENTO', 49.90, '2025-11-06T18:00:00');