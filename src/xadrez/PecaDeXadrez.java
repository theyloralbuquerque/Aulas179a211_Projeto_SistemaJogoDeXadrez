package xadrez;

import jogo_de_tabuleiro.Peca;
import jogo_de_tabuleiro.Tabuleiro;

public class PecaDeXadrez extends Peca{ // Herda da classe Peca. 

    private Cor cor;

    public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) { // Construtor recebendo como argumento tabuleiro e cor.
        super(tabuleiro);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }
}