import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ControleDeJogadas {
    public static final int NUMPARES = 9;
    public static final int NLIN = 6;
    public static final int NCOL = 3;
    private GameState state; // Estado do jogo
    private List<Carta> cartas; // Cartas do jogo
    private MemoriaComputador memoriaComputador;
    private Carta carta1,carta2; // Cartas da jogada do humano
    private Carta cartaC1,cartaC2; // cartas da jogada do computador
    private int pontosHumano,pontosComputador;
    private int qtdadePares;

    public ControleDeJogadas(){
        carta1 = null;
        carta2 = null;
        pontosHumano = 0;
        pontosComputador = 0;
        qtdadePares = NUMPARES;

        // Cria os pares de cartas em uma lista temporaria
        cartas = new ArrayList<>();
        for(int i=1;i<=NUMPARES;i++){
            cartas.add(new BotaoCarta("img"+i));
            cartas.add(new BotaoCarta("img"+i));
        }
        // Embaralha as cartas
        Collections.shuffle(cartas);

        // Cria a memoria do computador
        memoriaComputador = new MemoriaCurta(this);
    }

    public Carta getCarta(int nLin,int nCol){
        int pos = (nLin*NCOL)+nCol;
        return cartas.get(pos);
    }

    public GameState gState(){
        return state;
    }

    public int getPontosHumano(){
        return pontosHumano;
    }

    public int getPontosComputador(){
        return pontosComputador;
    }

    public GameState setCarta(Carta carta){
        if (carta1 == null){
            this.carta1 = carta;
            memoriaComputador.memoriza(carta);
            return GameState.ABRIU_CARTA1;
        }else{
            this.carta2 = carta;
            if (carta1.matches(carta2)){
                memoriaComputador.removeDaMemoria(carta1);
                carta1.tiraDoJogo();
                carta2.tiraDoJogo();
                pontosHumano += carta1.calculaPontosPro(carta2);
                pontosComputador -= carta1.calculaPontosContra(carta2);
                carta1 = null;
                carta2 = null;
                qtdadePares--;
                if (qtdadePares == 0){
                    return GameState.FIMDEPARTIDA;
                }else{
                    return GameState.ACHOU_PAR;
                }
            }else{
                memoriaComputador.memoriza(carta);
                carta1.fecha();
                carta2.fecha();
                carta1 = null;
                carta2 = null;
                return GameState.NAO_ACHOU_PAR;
            }
        }
    } 

    public Carta primeiraCartaComputador(){
        cartaC1 =  memoriaComputador.getPrimeiraCarta();
        cartaC1.abre();
        return cartaC1;
    }

    public Carta segundaCartaComputador(){
        cartaC2 =  memoriaComputador.getSegundaCarta();
        cartaC2.abre();
        return cartaC2;
    }

    public GameState completaJogadaComputador(){
        if (cartaC1.matches(cartaC2)){
            memoriaComputador.removeDaMemoria(cartaC1);
            memoriaComputador.removeDaMemoria(cartaC2);
            cartaC1.tiraDoJogo();
            cartaC2.tiraDoJogo();
            pontosComputador += cartaC1.calculaPontosPro(cartaC2);
            pontosHumano -= cartaC1.calculaPontosContra(cartaC2);
            cartaC1 = null;
            cartaC2 = null;
            qtdadePares--;
            if (qtdadePares == 0){
                return GameState.FIMDEPARTIDA;
            }else{
                return GameState.ACHOU_PAR;
            }
        }else{
            memoriaComputador.memoriza(cartaC1);
            memoriaComputador.memoriza(cartaC2);
            cartaC1.fecha();
            cartaC2.fecha();
            cartaC1 = null;
            cartaC2 = null;
            return GameState.NAO_ACHOU_PAR;
        }
    }
}