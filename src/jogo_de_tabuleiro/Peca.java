package jogo_de_tabuleiro;

public abstract class Peca {

	protected Posicao posicao;
	private Tabuleiro tabuleiro;

	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null;
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public abstract boolean[][] movimentosPossiveis(); // Classe abstrata.

	public boolean movimentoPossivel(Posicao posicao) {
	    // Aqui um m�todo concreto est� utilizando um m�todo abstrato (hook methods - m�todos gancho)
	    return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
	    // Retorna a matriz vindo a classe abstrata, com o valor da linha vinho de [posicao.getLinha()] e da coluna vindo de [posicao.getColuna()].
	}

	public boolean existeAlgumMovimentoPossivel() {
	    boolean[][] mat = movimentosPossiveis();
	    for (int i=0; i<mat.length; i++) {
	        for (int j=0; j<mat.length; j++) {
	            if (mat[i][j]) { // Se [i][j] for verdadeiro, fa�a:
	                return true;
	            }
	        }
	    }
	    return false;
	}
}