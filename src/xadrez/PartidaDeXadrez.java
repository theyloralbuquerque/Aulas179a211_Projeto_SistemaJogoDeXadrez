package xadrez;

import jogo_de_tabuleiro.Tabuleiro;

public class PartidaDeXadrez {

    private Tabuleiro tabuleiro; // Atributo tabuleiro do tipo Tabuleiro.

    public PartidaDeXadrez() {
        tabuleiro = new Tabuleiro(8, 8); // Instanciação do objeto tabuleiro passando como argumento (8, 8).
    }

    public PecaDeXadrez[][] obterPecas() { // Método que retorna uma matriz do tipo PecaDeXadrez quando for chamado o obterPecas().
        
    	// Instanciação de uma matriz mat do tipo PecaDeXadrez, passando como argumento as linhas do tabuleiro e as colunas do tabuleiro.
        PecaDeXadrez[][] mat = new PecaDeXadrez [tabuleiro.getLinhas()][tabuleiro.getColunas()];
        
        // Enquanto o i for menor que as linhas do tabuleiro, faça:
        for (int i=0; i<tabuleiro.getLinhas(); i++) {
            
        	// Enquanto o j for menor que as colunas do tabuleiro, faça:
            for (int j=0; j<tabuleiro.getColunas(); j++) {
               
            	/* a matriz mat na posição [i][j] recebe o retorno do método peca da o objeto tabuleiro (classe Tabuleiro),
                 * tudo isso feito downcasting para a classe PecaDeXadrez, para que o compilador interprete isso como uma peça
                 * de xadrez e não uma peça comum.
                 */
                mat[i][j] = (PecaDeXadrez)tabuleiro.peca(i, j);
            }
        }
        return mat;
    }
 }