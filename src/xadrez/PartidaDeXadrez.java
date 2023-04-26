package xadrez;

import jogo_de_tabuleiro.Tabuleiro;

public class PartidaDeXadrez {

    private Tabuleiro tabuleiro; // Atributo tabuleiro do tipo Tabuleiro.

    public PartidaDeXadrez() {
        tabuleiro = new Tabuleiro(8, 8); // Instancia��o do objeto tabuleiro passando como argumento (8, 8).
    }

    public PecaDeXadrez[][] obterPecas() { // M�todo que retorna uma matriz do tipo PecaDeXadrez quando for chamado o obterPecas().
        
    	// Instancia��o de uma matriz mat do tipo PecaDeXadrez, passando como argumento as linhas do tabuleiro e as colunas do tabuleiro.
        PecaDeXadrez[][] mat = new PecaDeXadrez [tabuleiro.getLinhas()][tabuleiro.getColunas()];
        
        // Enquanto o i for menor que as linhas do tabuleiro, fa�a:
        for (int i=0; i<tabuleiro.getLinhas(); i++) {
            
        	// Enquanto o j for menor que as colunas do tabuleiro, fa�a:
            for (int j=0; j<tabuleiro.getColunas(); j++) {
               
            	/* a matriz mat na posi��o [i][j] recebe o retorno do m�todo peca da o objeto tabuleiro (classe Tabuleiro),
                 * tudo isso feito downcasting para a classe PecaDeXadrez, para que o compilador interprete isso como uma pe�a
                 * de xadrez e n�o uma pe�a comum.
                 */
                mat[i][j] = (PecaDeXadrez)tabuleiro.peca(i, j);
            }
        }
        return mat;
    }
 }