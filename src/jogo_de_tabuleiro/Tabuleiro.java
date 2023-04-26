package jogo_de_tabuleiro;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Peca[][] pecas; // Uma matriz do tipo Peca que chamaremos de pecas.

	public Tabuleiro(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca [linhas][colunas]; // Uma nova matriz pecas do tipo Peca com os valores de [linhas] e [colunas]. 
	}
	
	

}
