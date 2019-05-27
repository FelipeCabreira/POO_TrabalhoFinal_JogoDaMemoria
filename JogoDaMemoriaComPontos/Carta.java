import javafx.scene.control.Button;

public abstract class Carta extends Button{
    // Retorna string de identificacao da figura
    public abstract String getNomeFigura();
    // Retorna o estado da carta
    public abstract CardState getState();
    // Define a posicao da carta no tabuleiro
    public abstract void setPosicao(Posicao p);
    // Retorna a posicao da carta no tabuleiro
    public abstract Posicao getPosicao();
    // Abre a carta se fechada; 
    public abstract boolean abre();
    // Fecha a carta se aberta
    public abstract boolean fecha();
    // Tira do jogo se aberta
    public abstract boolean tiraDoJogo();
    // Retorna true se a outra carta pode ser par desta
    public abstract boolean matches(Carta outra);
    // Retorna quantos pontos o jogador que obteve o match ganha
    public abstract int calculaPontosPro(Carta outra);
    // Retorna quantos pontos o adversário perde
    public abstract int calculaPontosContra(Carta outra);
    // Define a imagem da carta conforme seu estado interno
    public abstract void defineImagem();
}

// Sugestões de tipos de cartas
// Carta normal de memória: 1 ponto para quem faz o match
// Carta bonus: 2 pontos para quem faz o match
// Carta especial: 1 ponto para quem faz o match e retira um 1 ponto do adversario
// Carta aleatório: o número de pontos pode ser sorteado (0, 1 ou 2)
// Carta com ordem: abrindo na ordem certa vale 2 pontos, senão 1 ponto
