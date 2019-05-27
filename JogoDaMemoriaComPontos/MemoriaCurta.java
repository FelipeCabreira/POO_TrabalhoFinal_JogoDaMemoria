import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MemoriaCurta implements MemoriaComputador {
    public static final int MEMORY_SIZE = 6;

    private List<Carta> memoria;
    private ControleDeJogadas cJog;
    private Carta primeiraCarta;
    private Random r;
    
    public MemoriaCurta(ControleDeJogadas cJog){
        r = new Random();
        memoria = new LinkedList<>();
        this.cJog = cJog;
    }

    @Override
    public void memoriza(Carta carta) {
        // Memoriza no máximo MEMORY_SIZE cartas
        if (memoria.contains(carta)){
            return;
        }
        memoria.add(carta);
        if (memoria.size() >= MEMORY_SIZE){
            memoria.remove(0);
        }
    }

    private Carta chutaCarta(){
        // Enquanto não "pegar" uma carta valida, repete
        Carta carta = null;
        while(carta == null){
            int nLin = r.nextInt(ControleDeJogadas.NLIN);
            int nCol = r.nextInt(ControleDeJogadas.NCOL);
            carta = cJog.getCarta(nLin, nCol);
            // Se a carta esta aberta ou é usada, anula
            if (carta.getState() == CardState.ABERTA ||
                carta.getState() == CardState.USADA){
                    carta = null;
                }
        }
        return carta;
    }

    @Override
    public Carta getPrimeiraCarta() {
        // Chuta uma carta para abrir
        primeiraCarta = chutaCarta();
        return primeiraCarta;
    }

    @Override
    public Carta getSegundaCarta() {
        // Verifica se lembra dela
        for(Carta c:memoria){
            // Se lembrou retorna a carta
            if (c.getNomeFigura().equals(primeiraCarta.getNomeFigura())&&
                c.getPosicao() != primeiraCarta.getPosicao()){
                return c;
            }
        }
        // Se nao lembrou chuta uma
        return chutaCarta(); 
    }

    @Override
    public void removeDaMemoria(Carta carta) {
        memoria.remove(carta);
    }
}