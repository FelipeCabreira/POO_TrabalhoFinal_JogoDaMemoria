import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class MemoriaLonga extends MemoriaCurta {
    public static final int MEMORY_SIZE = 30;

    private List<Carta> memoria;
    private ControleDeJogadas controlGame;
    private Carta primeiraCarta;
    private Random random;

    public MemoriaLonga(ControleDeJogadas controlGame){
        super(controlGame);
        random = new Random();
        memoria = new ArrayList<>();
//        this.controlGame = controlGame;
    }

    @Override
    public void memoriza(Carta card) {
        // Memoriza no máximo MEMORY_SIZE cartas
        if (memoria.contains(card)){
            return;
        }
        memoria.add(card);
        if (memoria.size() >= MEMORY_SIZE){
            memoria.remove(0);
        }
    }

    @Override
    public Carta getPrimeiraCarta(){
//        primeiraCarta = chutaCarta();
//        return primeiraCarta;
        return super.getPrimeiraCarta();
    }

    @Override
    public Carta getSegundaCarta() {
        // Verifica se lembra dela
        for(Carta card: memoria){
            // Se lembrou retorna a carta
            if (card.getNomeFigura().equals(primeiraCarta.getNomeFigura())&&
                    card.getPosicao() != primeiraCarta.getPosicao()){
                return card;
            }
        }
        // Se nao lembrou chuta uma
        return chutaCarta();
    }



    @Override
    public void removeDaMemoria(Carta card) {
        memoria.remove(card);
    }


    protected Carta chutaCarta(){
        // Enquanto não "pegar" uma carta valida, repete
        Carta carta = null;
        while(carta == null){
            int nLin = random.nextInt(ControleDeJogadas.NLIN);
            int nCol = random.nextInt(ControleDeJogadas.NCOL);
            carta = controlGame.getCarta(nLin, nCol);
            // Se a carta esta aberta ou é usada, anula
            if (carta.getState() == CardState.ABERTA ||
                    carta.getState() == CardState.USADA){
                carta = null;
            }
        }
        return carta;
    }
}
