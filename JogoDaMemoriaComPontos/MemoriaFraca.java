import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MemoriaFraca extends MemoriaCurta {
    public static final int MEMORY_SIZE = 3;

    private List<Carta> memoria;
    private ControleDeJogadas controlGame;
    private Carta primeiraCarta;
    private Random random;

    public MemoriaFraca(ControleDeJogadas controlGame) {
        super(controlGame);
        System.out.println("Instanciado " + getClass().getName());
        random = new Random();
        memoria = new LinkedList<>();
//        this.controlGame = controlGame;
    }

    @Override
    public int getMemorySize(){
        return MEMORY_SIZE;
    }


    @Override
    public void memoriza(Carta card) {
        // Memoriza no máximo MEMORY_SIZE cartas
        if (memoria.contains(card)) {
            System.out.println("Card Existe");
            return;
        }
        memoria.add(card);
        if (memoria.size() >= MEMORY_SIZE) {
            memoria.remove(0);
        }
    }

    @Override
    public Carta getPrimeiraCarta() {
        // Chuta uma carta para abrir
        primeiraCarta = throwCard();
        return primeiraCarta;
    }

    @Override
    public Carta getSegundaCarta() {
        try{
            // Verifica se lembra dela
            for (Carta card : memoria) {
                // Se lembrou retorna a carta
                if (card.getNomeFigura().equals(primeiraCarta.getNomeFigura()) &&
                        card.getPosicao() != primeiraCarta.getPosicao()) {
                    return card;
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Thread error:" + e.getMessage());
            System.out.println("Thread Stack Trace:" + e.getStackTrace());
        }
        // Se nao lembrou chuta uma
        return throwCard();
    }

    @Override
    public void removeDaMemoria(Carta carta) {
        memoria.remove(carta);
    }

    private Carta throwCard() {
        return super.chutaCarta();
    }
}
