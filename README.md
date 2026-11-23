# ProjetoGame — Roguelike de Cartas e Dados

Roguelike de turnos em Java, inspirado em **D&D 5e** e **Baldur's Gate 3**. Uma dupla de heróis sobe uma torre de 10 andares (10 salas por andar, cada sala é uma batalha). O combate usa **cartas** (deck de até 30 por herói) e **dados** (d20 para acertar, dados de dano por efeito).

> Trabalho universitário em dupla. Projeto em desenvolvimento: ainda não há luta jogável.

## Ideias centrais

- Regras de D&D: atributos, modificadores, CA, crítico e dano por tipo.
- Pontos de ação (PA) acumuláveis e deck que não se recicla durante o andar.
- Dois heróis ativos, cada um com deck, mão e PA próprios.
- Dificuldade crescente por andar.

## Estado atual

| Área | Status |
|---|---|
| Atributos e dados | Feito |
| Cartas (Builder, catálogo, deck, mão) | Feito / em andamento |
| Efeitos (dano, cura, buff de CA, dano por turno) | Feito |
| Heróis (Guerreiro, Mago, Clérigo) | Em andamento |
| Buffs vivos nos atores | Código definido, falta integrar com turnos |
| Pontos de ação, resolvedor de ações | A fazer |
| Batalha, turnos, iniciativa, salvaguarda | Em andamento |
| Vilões, bosses, torre, níveis, loot | A fazer |
| Interface | A fazer (console primeiro) |

Próximo marco: **primeira luta de teste 1x1 no console**.

## Estrutura

```
ProjetoGame/
└── core/
    ├── atores/       Ator, Heroi, Vilao e os heróis (aventureiros/)
    ├── carta/        Carta, Builder, CatalogoCartas, Deck, Mao, enums
    ├── dado/         Dados
    ├── efeitos/      Efeito, buffs e implementações
    └── stats/        Atributo, Atributos
```

Todos os `package` começam com `core.` (ex.: `package core.carta;`). A raiz do projeto é `ProjetoGame/`.

## Como rodar

Requisitos: JDK 17 ou superior e VS Code com o *Extension Pack for Java*.

1. Abra a pasta `ProjetoGame` no VS Code (File → Open Folder).
2. Se aparecerem erros de pacote que não somem, rode `Ctrl+Shift+P` → **Java: Clean Java Language Server Workspace**.
3. Execute a classe de teste (com `main`) pelo botão **Run** do VS Code.

Por ora não há uma classe principal do jogo; os testes são feitos com um `main` de rascunho.

## Exemplo: criando uma carta

```java
Carta CORTE_PODEROSO = new Carta.Builder(8, "Corte Poderoso", TipoCarta.TECNICA, 3)
        .atributo(Atributo.FORCA)
        .efeito(new EfeitoDano(new Dados(2, 8), TipoDano.CORTANTE))
        .build();
```

E um herói montando o deck:

```java
deck.adicionar(CatalogoCartas.ESPADA_LONGA, 4);
```

## Documentação

- **GDD**: regras e design do jogo (versão 0.4).
- **[ARQUITETURA.md](ARQUITETURA.md)**: classes, decisões técnicas e pendências.

## Fluxo de trabalho

- Tarefas como *issues* no GitHub, com branches por funcionalidade.
- Rafael: cartas, efeitos, buffs, heróis e vilões.
- Vinicius: turnos, jogadas, iniciativa e salvaguarda.
- Dados: iniciado pelo Vinicius e aprimorado pelos dois.
- Combinar quem altera `Ator` e `Heroi` para evitar conflitos.

## Autores

- Rafael (RafaelITSR)
- Vinicius Xavier(Vinix7341).
 