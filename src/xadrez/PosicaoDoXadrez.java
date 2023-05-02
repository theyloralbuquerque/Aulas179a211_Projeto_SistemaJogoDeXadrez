package xadrez;

import jogo_de_tabuleiro.Posicao;

public class PosicaoDoXadrez {

	private char coluna;
	private int linha;
	
	public PosicaoDoXadrez(char coluna, int linha) { 
		if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) { // Programção defensiva aqui.
			throw new XadrezException("Erro de instanciacaoo da posicao do xadrez. Valores validos sao de a1 - h8.");
		}	
		this.coluna = coluna;
		this.linha = linha;
	}

	public char getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}
	
	// Método que converte as coordenadas que o usuário digitou em coordenadas de matriz.
	protected Posicao paraPosicaoM() {
		return new Posicao(8 - linha, coluna - 'a');
	}
	
	// Método que converte as coordenadas de matriz armazenadas em coordenadas de xadrez.
	protected static PosicaoDoXadrez paraPosicaoX(Posicao posicao) {
		return new PosicaoDoXadrez((char)('a' + posicao.getColuna()), 8 - posicao.getLinha());
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha;
	}
	
}
