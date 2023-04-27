package jogo_de_tabuleiro;

public class TabuleiroException extends RuntimeException {
    private static final long serialVersionUID = 1L; // serialized.

    public TabuleiroException(String msg) {
        super(msg);
    }
}