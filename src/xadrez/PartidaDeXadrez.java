package xadrez;

import jogo_de_tabuleiro.Peca;
import jogo_de_tabuleiro.Posicao;
import jogo_de_tabuleiro.Tabuleiro;
import xadrez.pecas.King;
import xadrez.pecas.Rook;

public class PartidaDeXadrez {

    private Tabuleiro tabuleiro; // Atributo tabuleiro do tipo Tabuleiro.

    public PartidaDeXadrez() {
        tabuleiro = new Tabuleiro(8, 8); // Instancia��o do objeto tabuleiro passando como argumento (8, 8).
        configuracaoInicial(); // Chamada do m�todo configuracaoInicial().
    }

    /*
     * M�todo que retorna uma matriz
     *
     */
    public PecaDeXadrez[][] getPecas() { // M�todo que retorna uma matriz do tipo PecaDeXadrez quando for chamado o getPecas().
       
    	// Instancia��o de uma matriz mat do tipo PecaDeXadrez, passando como argumento as linhas do tabuleiro e as colunas do tabuleiro.
        PecaDeXadrez [][] mat = new PecaDeXadrez [tabuleiro.getLinhas()][tabuleiro.getColunas()];
       
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

    // M�todo respos�vel por iniciar a partida de xadrez colocando as pe�as no tabuleiro.
    private void configuracaoInicial() {
        /*
         *  O objeto tabuleiro (classe tabuleiro) chama o m�todo .colocarPeca()
         *  que temos que passar dois argumentos (peca, posicao) para o m�todo.
         *  No atributo peca, instancia-se uma nova pe�a diretamente pelo nome da
         *  pe�a, passando como argumentos os atributos que a classe da pe�a pede.
         *  No atributo posicao, instancia-se uma nova posi��o passando para o
         *  construtor da classe Posicao o n�mero de linhas e colunas.
         */
        tabuleiro.colocarPeca(new Rook(tabuleiro, Cor.BRANCA), new Posicao(2, 1));
        tabuleiro.colocarPeca(new King(tabuleiro, Cor.PRETA), new Posicao(0, 4));
        tabuleiro.colocarPeca(new King(tabuleiro, Cor.BRANCA), new Posicao(7, 4));
    }
 }