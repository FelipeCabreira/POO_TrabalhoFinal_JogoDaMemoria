import javafx.scene.image.ImageView;

public class BotaoCarta extends Carta {
    private String nomeFigura;
    private CardState state;
    private Posicao posicao;

    public BotaoCarta(String nomeFigura){
        this.nomeFigura = nomeFigura;
        this.state = CardState.FECHADA;
        posicao = null;
        defineImagem();
    }

    @Override
    public String getNomeFigura() {
        return nomeFigura;
    }

    @Override
    public CardState getState() {
        return state;
    }

    @Override
    public void setPosicao(Posicao p){
        posicao = p;
    }

    @Override
    public Posicao getPosicao(){
        return posicao;
    }

    @Override
    public boolean abre() {
        if (state == CardState.FECHADA){
            state = CardState.ABERTA;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean fecha() {
        if (state == CardState.ABERTA){
            state = CardState.FECHADA;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean tiraDoJogo() {
        if (state == CardState.ABERTA){
            state = CardState.USADA;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean matches(Carta outra) {
        return getNomeFigura().equals(outra.getNomeFigura());
    }

    @Override
    public int calculaPontosPro(Carta outra) {
        return 1;
    }

    @Override
    public int calculaPontosContra(Carta outra) {
        return 0;
    }

    public void defineImagem(){
        ImageView iVaux = null;
        String imgName = null;
        switch(getState()){
            case FECHADA:
                imgName = "back";
                break;
            case ABERTA:
                imgName = getNomeFigura();
                break;
            case USADA:
            imgName = "";
            break;
        }
        iVaux = new ImageView(Jogo.getImage(imgName));
        iVaux.setFitWidth(Jogo.CARD_WIDTH);
        iVaux.setFitHeight(Jogo.CARD_HEIGHT);
        setGraphic(iVaux);
    }
}