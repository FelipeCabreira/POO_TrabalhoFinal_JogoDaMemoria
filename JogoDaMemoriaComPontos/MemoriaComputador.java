public interface MemoriaComputador {
    void memoriza(Carta carta);
    Carta getPrimeiraCarta();
    Carta getSegundaCarta();
    void removeDaMemoria(Carta carta);
}