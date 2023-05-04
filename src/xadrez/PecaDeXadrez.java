package xadrez;

import jogo_de_tabuleiro.Peca;
import jogo_de_tabuleiro.Posicao;
import jogo_de_tabuleiro.Tabuleiro;

public abstract class PecaDeXadrez extends Peca{ // Herda da classe Peca.

    private Cor cor;
    private int contadorDeMovimentos;

    public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) { // Construtor recebendo como argumento tabuleiro e cor.
        super(tabuleiro);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }

    public int getContadorDeMovimentos() {
        return contadorDeMovimentos;
    }

    public void incrementarNoContadorDeMovimentos() {
        contadorDeMovimentos++;
    }

    public void decrementarNoContadorDeMovimentos() {
        contadorDeMovimentos--;
    }

    // Método que retorna a posição do xadrez.
    public PosicaoDoXadrez obterPosicaoDoXadrez() {
    	return PosicaoDoXadrez.paraPosicaoX(posicao);
    }

    protected boolean existeUmaPecaAdversaria(Posicao posicao) {
    	PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(posicao);
    	return p != null && p.getCor() != cor; // Se p é diferente de null e a cor de p é diferente da cor da minha peça.
    }
}