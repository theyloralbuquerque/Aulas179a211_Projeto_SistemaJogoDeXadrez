package xadrez;

import jogo_de_tabuleiro.Peca;
import jogo_de_tabuleiro.Posicao;
import jogo_de_tabuleiro.Tabuleiro;

public abstract class PecaDeXadrez extends Peca{ // Herda da classe Peca.

    private Cor cor;

    public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) { // Construtor recebendo como argumento tabuleiro e cor.
        super(tabuleiro);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }

    protected boolean existeUmaPecaAdversaria(Posicao posicao) {
    	PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(posicao);
    	return p != null && p.getCor() != cor; // Se p � diferente de null e a cor de p � diferente da cor da minha pe�a.
    }
}