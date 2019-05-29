# POO_TrabalhoFinal_JogoDaMemoria
Trabalho final da disciplina de programação orientada a objetos

# Enunciado
## Objetivo geral
O obejtivo deste trabalho é explorar os conceitos de programação orientada a objetos em especial herança e polimorfismo. Para tanto é fornecida a versão preliminar de um "jogo de memória" que deve ter seu "comportamento" extendido a partir da derivação de novas classes a partir das já existentes fornecidas neste repositório. Conceitos de programação orientada a eventos também são explorados na medida que a interface com o usuário também deve ser ampliada a modificada.

## Regras do Jogo

Neste jogo compete-se com o "computador" que tem uma "inteligencia" própria. O objetivo do jogo é encontrar um número de pares de cartas iguais maior que o número de pares de cartas encontrados pelo "computador". Em sua vez tanto o "humano" como o computador podem virar duas cartas. Se elas forem iguais ou "combinarem" o jogador ganha os pontos correspondentes aquele par. Quando o jogo se encerra vence o jogador que tiver mais pontos.

Na versão fornecidas cada par encontrado vale apenas um ponto, de maneira que vence quem tiver mais pares quando as cartas se esgotarem.

## Estrutura de classes
O diagrama de classes pode ser visto na figura disponibilizada neste repositório. São 6 classes ou interfaces principais:
- Jogo: implementa a interface com o usuário. Transmite as demandas do usuário para o controle de jogadas e exibe o resultado destas ações.
- ControleDeJogadas: mantém a lógica central do jogo a partir das demandas da interface com o usuário. Processa as solicitações e retorna para a interface as mudanças no estado do jogo de maneira que estas possam ser refletidas para o usuário.
- Carta: classe abstrata que define a interface de comunicação das "cartas" do jogo. Novos tipos de cartas podem ser derivados a partir desta classe.
- CartaComum: uma implementação concreta de "Carta". Implementa uma carta simples. Cada par descoberto vale um ponto e não desconta pontos do adversário.
- MemoriaComputador: interface que define a "inteligência" ou "memória" do computador, isto é, a lógica que define como ele irá memorizar as cartas e suas estratégias de jogo.
- MemoriaCurta: uma implementação para "MemoriaComputador". Nesta implementação o computador "memoriza" no máximo 6 cartas. A primeira carta que ele abre é sempre "chutada". Se ele tiver menos de 3 cartas na memória a segunda também é chute. Se ele já tiver mais de 3 cartas na memória então ele verifica na memória se "sabe" onde está o par da carta que abriu.

## Objetivos específicos
Os objetivos específicos do trabalho são:
- C