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