import javafx.scene.image.ImageView;


public class BotaoCarta extends Carta {
    private String nomeFigura;
    private CardState state;
    private Posicao posicao;
    private CartaEspecial iconState;

    public BotaoCarta(String nomeFigura) {
        //  TODO: METODO QUE ACESSA UM ICONE, VERIFICAR SE CONTEM O NUMERO RANDOM SE TIVER DEIXA CRIAR O ICONE COM METODO defineIcone
        this.nomeFigura = nomeFigura;
        this.state = CardState.FECHADA;
        posicao = null;
        defineImagem();
        defineIcone();

    }

    // ########################### -- NEW METHODS -- ################################### //
    //TODO: METODO DEFINE ICONE
    public boolean defineIcone() {

        while (true) {
            System.out.println("CHEGOU");
            double random = Math.random();
            if (random % 1 == 0){
                System.out.println("NUMBER" + random);
                switch (getIconState()) {
                    case COM_ICONE:
                        break;
                    case SEM_ICONE:
                        break;
                }
                return true;
            }else {
                return false;
            }

        }

    }


//    public void defineImagem(){
//        ImageView iVaux = null;
//        String imgName = null;
//        switch(getState()){
//            case FECHADA:
//                imgName = "back";
//                break;
//            case ABERTA:
//                imgName = getNomeFigura();
//                break;
//            case USADA:
//                imgName = "";
//                break;
//        }
//        iVaux = new ImageView(Jogo.getImage(imgName));
//        iVaux.setFitWidth(Jogo.CARD_WIDTH);
//        iVaux.setFitHeight(Jogo.CARD_HEIGHT);
//        setGraphic(iVaux);
//    }

    //TODO: ACESSA UM ENUM PARA VALIDAR / VERIFICAR SE EXISTE UM ICONE BONUS OU NÃO
    @Override
    public CartaEspecial getIconState() {
        return iconState;
    }

    @Override
    //TODO: METODO PARA VERIFICAR SE O ICONE É IGUAL
    public boolean cardBonusMatches(Carta cBonus) {
        return getIconState().equals(cBonus.getIconState());
    }

    // ########################### --  METHODS -- ################################### //
    @Override
    public String getNomeFigura() {
        return nomeFigura;
    }

    @Override
    public CardState getState() {
        return state;
    }

    @Override
    public void setPosicao(Posicao p) {
        posicao = p;
    }

    @Override
    public Posicao getPosicao() {
        return posicao;
    }

    @Override
    public boolean abre() {
        if (state == CardState.FECHADA) {
            state = CardState.ABERTA;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean fecha() {
        if (state == CardState.ABERTA) {
            state = CardState.FECHADA;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean tiraDoJogo() {
        if (state == CardState.ABERTA) {
            state = CardState.USADA;
            return true;
        } else {
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

    public void defineImagem() {
        ImageView iVaux = null;
        String imgName = null;
        switch (getState()) {
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