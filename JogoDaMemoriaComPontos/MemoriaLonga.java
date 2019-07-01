import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MemoriaLonga extends MemoriaCurta {
    public static final int MEMORY_SIZE = 12;

    private List<Carta> memoria;
    private ControleDeJogadas controlGame;
    private Carta primeiraCarta;
    private Random random;

    public MemoriaLonga(ControleDeJogadas controlGame) {
        super(controlGame);
        System.out.println("Instanciado " + getClass().getName());
        random = new Random();
        memoria = new LinkedList<>();
//        this.controlGame = controlGame;
    }


    @Override
    public int getMemorySize() {
        return MEMORY_SIZE;
    }

    @Override
    public void memoriza(Carta card) {
        // Memoriza no máximo MEMORY_SIZE cartas
        if (memoria.contains(card)) {
            return;
        }
        memoria.add(card);
        if (memoria.size() >= MEMORY_SIZE) {
            memoria.remove(0);
        }
    }

    @Override
    public Carta getPrimeiraCarta() {
//        primeiraCarta = chutaCarta();
//        return primeiraCarta;
        return super.getPrimeiraCarta();
    }

    @Override
    public Carta getSegundaCarta() {
        // Verifica se lembra dela
        try{

            for (Carta card : memoria) {
                // Se lembrou retorna a carta
                if (card.getNomeFigura().equals(primeiraCarta.getNomeFigura()) &&
                        card.getPosicao() != primeiraCarta.getPosicao()) {
                    return card;
                }
            }
        } catch(RuntimeException e) {
            System.out.println("Thread error: " + e.getMessage());
            System.out.println("Thread Stack Trace: " + e.getStackTrace());
            System.out.println("Cause: " + e.getCause());
        }
        // Se nao lembrou chuta uma
        return throwCard();
    }


    @Override
    public void removeDaMemoria(Carta card) {
        memoria.remove(card);
    }


    private Carta throwCard() {
        return super.chutaCarta();
    }
}
