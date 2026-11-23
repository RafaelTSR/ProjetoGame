# ARQUITETURA

Estrutura atual das classes, sistemas implementados e decisões técnicas do projeto.
Atualize este arquivo sempre que uma decisão mudar. Complementa o GDD (regras do jogo).

## 1. Princípios

- O núcleo (`core`) não depende de interface: nada de `System.out` nas regras. A UI chama o core, nunca o contrário.
- Dados separados de comportamento: a carta guarda informação, o resolvedor/efeitos executam.
- Objetos de definição (cartas, efeitos) são imutáveis e compartilhados. O que muda durante o jogo (buffs, vida, deck) vive nos atores.
- Campos começam `private`; `protected` só quando a subclasse precisa.
- Começar simples (cartas em código) e migrar para arquivo (JSON) depois.

## 2. Estrutura de pastas

Raiz do projeto: `ProjetoGame/`. Todo `package` começa com `core.`

```
ProjetoGame/
└── core/
    ├── atores/
    │   ├── Ator.java            (abstrata)
    │   ├── Heroi.java           (abstrata)
    │   ├── Vilao.java           (abstrata)
    │   └── aventureiros/
    │       ├── Guerreiro.java
    │       ├── Mago.java
    │       └── Clerigo.java
    ├── carta/
    │   ├── Carta.java           (imutável, com Builder)
    │   ├── CatalogoCartas.java
    │   ├── Deck.java
    │   ├── Mao.java
    │   ├── TipoCarta.java       (enum)
    │   └── TipoDano.java        (enum)
    ├── dado/
    │   ├── Dados.java
    │   └── Resultado.java       (a refazer, sem println)
    ├── efeitos/
    │   ├── Efeito.java          (interface)
    │   ├── EfeitoDano.java
    │   ├── EfeitoCura.java
    │   ├── EfeitoBuffCA.java
    │   ├── EfeitoDanoPorTurno.java
    │   ├── Buff.java            (interface)
    │   ├── BuffCA.java
    │   ├── BuffAtributo.java
    │   ├── BuffDanoPorTurno.java
    │   └── BuffDanoExtra.java
    └── stats/
        ├── Atributo.java        (enum)
        └── Atributos.java
```

Pacotes futuros: `core.combate` (Batalha, ResolvedorAcao, PontosAcao, Iniciativa), `core.torre`, `core.dados` (leitor JSON) e `ui`.

## 3. Hierarquia de atores

```
Ator (abstract)
├── Heroi (abstract)   → Deck, Mao, PontosAcao
│   ├── Guerreiro
│   ├── Mago
│   └── Clerigo
└── Vilao (abstract)   → ações próprias, afinidades, multiplicador de andar
    └── Boss (a definir)
```

`Ator` guarda: nome, vida, vidaMaxima, `Atributos`, lista de buffs vivos. Métodos principais: `estaVivo()`, `getCA()` (10 + mod DES + bônus de CA dos buffs), `receberDano(int)`, `receberDano(int, TipoDano)` (ponto único da futura afinidade), `curar(int)`, `adicionarBuff(Buff)`, `iniciarTurno()`.

Tipos de inimigo (esqueleto, golem...) serão **dados**, não subclasses. Subclasse só para comportamento próprio (boss).

## 4. Sistemas implementados

### 4.1 Atributos

- `Atributo` (enum, singular): FORCA, DESTREZA, CONSTITUICAO, INTELIGENCIA, SABEDORIA, CARISMA.
- `Atributos` (plural): dois `EnumMap`, `base` (fixo) e `bonus` (temporário). `get(a)` = base + bônus; `modificador(a)` = `Math.floorDiv(get(a) - 10, 2)`.
- `adicionarBonus` / `removerBonus` usam `merge(chave, valor, Integer::sum)`.

### 4.2 Dados

Começou com o Vinicius e foi aprimorada depois por Rafael e Vinicius. `Dados(quantidade, lados)` com `rolar()` (soma) e `Dados.rolar(lados)` estático. `Random` compartilhado. Pendente: semente fixa para testes.

### 4.3 Carta

Campos: `id`, `nome`, `tipoCarta`, `custoPA`, `atributo`, `List<Efeito> efeitos` (cópia imutável via `List.copyOf`). Criada só pelo `Carta.Builder` (construtor privado). Obrigatórios no Builder: id, nome, tipo, custo. Opcionais: `.atributo(...)`, `.efeito(...)` (repetível).

```java
Carta CORTE_PODEROSO = new Carta.Builder(8, "Corte Poderoso", TipoCarta.TECNICA, 3)
        .atributo(Atributo.FORCA)
        .efeito(new EfeitoDano(new Dados(2, 8), TipoDano.CORTANTE))
        .build();
```

### 4.4 Deck e Mão

- `Deck`: lista de cartas de **um herói**, limite 30. `adicionar(carta)` e `adicionar(carta, quantidade)`; o excedente é ignorado em silêncio (a sinalizar depois). `comprar()` sorteia e remove; devolve `null` se vazio.
- `Mao`: até 5 cartas. `completar(deck)`, `usar(carta)`, `estaVazia()`, `get(i)`, `tamanho()`, `getCartas()` (cópia imutável).
- Cada herói monta o deck no construtor, a partir do `CatalogoCartas`.

### 4.5 Efeitos

Contrato: `void aplicar(Ator usuario, Ator alvo, Carta carta)`. O efeito guarda a **receita** (dados, tipo, duração) e lê o atributo de `carta.getAtributo()`. Quem escolhe o alvo é o resolvedor, não o efeito.

| Efeito | Observação |
|---|---|
| `EfeitoDano` | dados + mod do atributo; aplica com tipo; depois um dano separado por cada buff de dano extra ativo |
| `EfeitoCura` | dados + mod do atributo; hoje cura o usuário |
| `EfeitoBuffCA` | cria `new BuffCA(valor, turnos)` no alvo |
| `EfeitoDanoPorTurno` | cria `new BuffDanoPorTurno(dados, tipo, turnos)` |

### 4.6 Buffs

Interface `Buff` com `ativo()` obrigatório e ganchos `default`: `aoAplicar`, `aoExpirar`, `aoIniciarTurno`, `bonusAoCausarDano`, `bonusCA`, `tipoDanoAdicional`.

| Buff | Duração | Como age |
|---|---|---|
| `BuffCA` | turnos | `getCA()` soma `bonusCA()`; nada a desfazer |
| `BuffAtributo` | turnos | `aoAplicar` soma no `Atributos`, `aoExpirar` subtrai |
| `BuffDanoPorTurno` | turnos | dano com tipo em `aoIniciarTurno` |
| `BuffDanoExtra` | usos | `bonusAoCausarDano()` rola e gasta um uso; `tipoDanoAdicional()` devolve o tipo |

`Ator.iniciarTurno()`: percorre os buffs chamando `aoIniciarTurno(this)`, depois `removeIf` chama `aoExpirar` nos inativos e os remove (duas fases para evitar `ConcurrentModificationException`). Sem o `aoExpirar`, um bônus de atributo ficaria para sempre.

## 5. Decisões técnicas e motivos

| # | Decisão | Motivo |
|---|---|---|
| 1 | Pacotes em português (`atores`, `carta`, `dado`, `stats`, `efeitos`) | consistência com o código; diverge do GDD original (`card`, `dice`) |
| 2 | `Ator` no lugar de `Combatente` | nome escolhido pela dupla |
| 3 | Hierarquia `Ator → Heroi/Vilao → subtipos` | só heróis têm deck, mão e PA |
| 4 | `Vida` e `ClasseArmadura` não viram classes | `int` e método bastam; CA é calculada na hora |
| 5 | Carta = dados + lista de `Efeito` | evita um campo por efeito; permite exibir e balancear |
| 6 | Builder na `Carta` | construtor grande e campos opcionais |
| 7 | Atributo só na carta, não nos efeitos | uma fonte única; serve ao acerto, dano, cura e CD |
| 8 | `copias` removido da carta | cada herói decide a quantidade em `deck.adicionar(carta, n)` |
| 9 | `bonusCarta` removido | valores fixos vivem nos efeitos/buffs (confirmar se a CD da salvaguarda vai precisar dele) |
| 10 | Sem campo `dono` na carta | o herói monta o próprio deck |
| 11 | Buff novo a cada aplicação | contador compartilhado entre alvos seria um bug |
| 12 | Todo dano extra de buff tem tipo | simplifica o cálculo; passa pela afinidade à parte |
| 13 | `receberDano(int, TipoDano)` como ponto único | afinidade entra num lugar só |
| 14 | Cartas em código, JSON adiado | simplicidade; o `CatalogoCartas` isola a troca |
| 15 | Vilões ignoram PA, deck e mão | agem por lista de ações + IA |
| 16 | Buffs só entram na luta depois dos turnos | dependem de `iniciarTurno()` |

## 6. Pendências técnicas

- [ ] `EfeitoBuffAtributo` e `EfeitoDanoExtra` (criar buff novo na aplicação; hoje `EfeitoAplicarBuff` com buff pronto compartilha contador)
- [ ] `PontosAcao` (teto 10, +5 por turno, `gastar`/`podeGastar`)
- [ ] `Heroi` completo (deck, mão, PA); `Vilao` e um inimigo simples
- [ ] `ResolvedorAcao` (PA, d20 vs CA, efeitos) e escolha de alvo
- [ ] Crítico: dobrar dados (efeito e buffs)
- [ ] Afinidades: enum `Afinidade` e mapa no vilão; implementar em `receberDano(int, TipoDano)`
- [ ] Refazer `Resultado` sem `println`, devolvendo objeto
- [ ] Semente fixa no `Random` para testes
- [ ] `Heroi.iniciarTurno()` deve chamar `super.iniciarTurno()`; checar `estaVivo()` depois (veneno)
- [ ] Regra de não acumular buffs da mesma fonte
- [ ] Cura em alvo diferente do usuário
- [ ] Sinalizar quando o deck estoura o limite
- [ ] Ataque básico de reserva (`PoolAtaqueBasico`)
- [ ] Validação no `build()` da carta (efeito de dano/cura exige atributo)
- [ ] Padronizar nomes (`tipoDanoAdicional`, constantes em MAIÚSCULAS)
- [ ] Batalha, iniciativa, turnos, salvaguarda/CD e condições (Vinicius, em andamento)
- [ ] Níveis, XP, loot, torre, JSON, interface

## 7. Divisão de trabalho

- **Rafael:** cartas, efeitos, buffs, heróis, vilões, `Heroi` e `Vilao`.
- **Vinicius:** turnos, jogadas, iniciativa, salvaguarda e condições.
- **Dados:** iniciada pelo Vinicius e aprimorada pelos dois.

## 8. Convenções

- Pacotes minúsculos, classes `PascalCase`, constantes `MAIUSCULAS_COM_UNDERSCORE`, métodos e variáveis `camelCase`, sem acentos em identificadores.
- Mudou `package` ou moveu arquivo? Ajuste os `import` dos outros.
- Declarar com a interface, criar com a implementação (`List`/`ArrayList`, `Map`/`EnumMap`).
- Branches no Git; combinar quem altera `Ator` e `Heroi`.