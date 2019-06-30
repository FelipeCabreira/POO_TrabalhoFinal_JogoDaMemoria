public class BotaoBonus extends BotaoCarta {
    private String nomeFigura;
    private CardState stateBonus;
    private Posicao posicaoBonus;

    public BotaoBonus(String nomeFigura) {
        super(nomeFigura);
        this.nomeFigura = nomeFigura;
        this.stateBonus = CardState.FECHADA;
        this.posicaoBonus = null;
        defineImagem();
    }

    @Override
    public String getNomeFigura() {
        return nomeFigura;
    }

    @Override
    public CardState getState() {
        return stateBonus;
    }

    @Override
    public void setPosicao(Posicao p) {
        posicaoBonus = p;
    }

    @Override
    public Posicao getPosicao() {
        return posicaoBonus;
    }

    @Override
    public boolean matches(Carta card) {
        return getNomeFigura().equals(card.getNomeFigura());
    }

    @Override
    public int calculaPontosPro(Carta card) {
        return 2;
    }
}
