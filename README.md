# Projeto Mackenzie E-commerce API

Este repositório contém o backend de uma aplicação de e-commerce desenvolvida como projeto académico (Mackenzie). A aplicação foca-se na venda de produtos aromáticos (Home Sprays, Difusores, etc.) e fornece uma API RESTful completa.

## ♿ Acessibilidade e Inclusão

Este projeto foi desenvolvido com um forte compromisso com a inclusão e acessibilidade digital no seu **frontend**:

  * **Baixa Visão:** A interface utiliza um esquema de **cores de alto contraste** para facilitar a navegação e leitura por pessoas com baixa visão.
  * **Comunidade Surda:** A aplicação dispõe de recursos de acessibilidade em **Libras** (Língua Brasileira de Sinais), garantindo o acesso à informação para pessoas surdas.

-----

## 🛠 Tecnologias Utilizadas

  * **Linguagem:** Java 21
  * **Framework:** Spring Boot 3.5.7
  * **Gerenciador de Dependências:** Gradle
  * **Segurança:** Spring Security + JWT (JSON Web Token)
  * **Base de Dados:** H2 Database (em memória para desenvolvimento/testes)
  * **Outras Bibliotecas:**
      * Lombok (para redução de código boilerplate)
      * Validation API (para validação de DTOs)

## 🚀 Funcionalidades

### Cliente

  * **Catálogo:** Visualização e filtragem de produtos por nome e categoria.
  * **Carrinho:** Adicionar itens, remover itens e visualizar resumo (com persistência em sessão).
  * **Checkout:** Finalização de pedidos (simulação).
  * **Histórico:** Consulta de pedidos realizados.

### Administração

  * **Dashboard:** Visualização de receita total e quantidade de pedidos pagos.
  * **Gestão de Pedidos:** Acompanhamento de pedidos em andamento e atualização de status (ex: "Em Produção", "Enviado").
  * **Segurança:** Rotas protegidas acessíveis apenas por utilizadores com perfil `ROLE_ADMIN`.

## ⚙️ Configuração e Execução

### Pré-requisitos

  * Java JDK 21 instalado.
  * Variáveis de ambiente configuradas (ver abaixo).

### Variáveis de Ambiente

O ficheiro `application.yaml` referencia variáveis que devem ser definidas no sistema ou substituídas diretamente no ficheiro para execução local:

```yaml
JWT_SECRET: <Sua_Chave_Secreta_Para_Assinar_Tokens>
```

### Como Executar

1.  Clone o repositório.

2.  Abra o terminal na raiz do projeto.

3.  Execute o comando do Gradle:

    **Windows:**

    ```cmd
    ./gradlew.bat bootRun
    ```

    **Linux/Mac:**

    ```bash
    ./gradlew bootRun
    ```

A aplicação iniciará na porta padrão `8080`.

### Base de Dados (H2 Console)

A aplicação utiliza uma base de dados em memória H2 que é reiniciada a cada execução. Dados iniciais (categorias, essências, produtos e um utilizador admin) são carregados automaticamente via `data.sql`.

  * **URL do Console:** `http://localhost:8080/h2-console`
  * **JDBC URL:** `jdbc:h2:mem:testdb`
  * **User:** `sa`
  * **Password:** `1234`

## 📚 Documentação da API

Abaixo estão os principais endpoints disponíveis.

### Autenticação

| Método | Endpoint | Descrição |
| --- | --- | --- |
| POST | `/api/auth/registrar` | Regista um novo cliente. |
| POST | `/api/auth/login` | Realiza login e retorna o Token JWT. |

### Produtos e Categorias

| Método | Endpoint | Descrição |
| --- | --- | --- |
| GET | `/api/produtos` | Lista todos os produtos (permite filtros `nome` e `categoriaId`). |
| GET | `/api/produtos/{id}` | Detalhes de um produto específico. |
| GET | `/api/produtos/categorias` | Lista todas as categorias disponíveis. |
| GET | `/api/essencias` | Lista as essências disponíveis para personalização. |

### Carrinho de Compras

| Método | Endpoint | Descrição |
| --- | --- | --- |
| GET | `/api/carrinho` | Visualiza o carrinho atual. |
| POST | `/api/carrinho` | Adiciona um item ao carrinho. |
| DELETE | `/api/carrinho/{itemId}` | Remove um item do carrinho. |

### Pedidos

| Método | Endpoint | Descrição |
| --- | --- | --- |
| POST | `/api/pedidos` | Finaliza o pedido (Checkout). |
| GET | `/api/pedidos/consulta` | Consulta histórico de pedidos (via email). |

### Painel Administrativo (Requer Token Admin)

| Método | Endpoint | Descrição |
| --- | --- | --- |
| GET | `/api/admin/dashboard` | Retorna dados consolidados (receita, total vendas). |
| GET | `/api/admin/pedidos-em-andamento` | Lista pedidos que não foram finalizados/cancelados. |
| GET | `/api/admin/pedidos/{id}` | Detalhes completos de um pedido. |
| PUT | `/api/admin/pedidos/{id}/status` | Atualiza o status de um pedido. |

## 👤 Utilizadores Padrão (Seed Data)

O ficheiro `data.sql` insere automaticamente um administrador para testes:

  * **Email:** `admin@email.com`
  * **Senha:** Hash Bcrypt pré-configurado.
