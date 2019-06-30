public class BotaoEspecial extends BotaoCarta {
    private String nomeFigura;
    private CardState stateEspecial;
    private Posicao posicaoEspecial;

    public BotaoEspecial(String nomeFigura) {
        super(nomeFigura);
        this.stateEspecial = CardState.FECHADA;
        this.posicaoEspecial = null;
        defineImagem();
    }

    @Override
    public String getNomeFigura() {
        return nomeFigura;
    }

    @Override
    public CardState getState() {
        return stateEspecial;
    }

    @Override
    public void setPosicao(Posicao p) {
        posicaoEspecial = p;
    }

    @Override
    public Posicao getPosicao() {
        return posicaoEspecial;
    }

    @Override
    public boolean matches(Carta card) {
        return getNomeFigura().equals(card.getNomeFigura());
    }

    @Override
    public int calculaPontosPro(Carta card) {
        return 1;
    }

    @Override
    public int calculaPontosContra(Carta card) {
        return -1;
    }
}
