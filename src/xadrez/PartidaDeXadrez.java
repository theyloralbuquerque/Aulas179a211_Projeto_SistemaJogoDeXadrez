package xadrez;

import jogo_de_tabuleiro.Peca;
import jogo_de_tabuleiro.Posicao;
import jogo_de_tabuleiro.Tabuleiro;
import xadrez.pecas.King;
import xadrez.pecas.Rook;

public class PartidaDeXadrez {

    private Tabuleiro tabuleiro; // Atributo tabuleiro do tipo Tabuleiro.

    public PartidaDeXadrez() {
        tabuleiro = new Tabuleiro(8, 8); // Instanciação do objeto tabuleiro passando como argumento (8, 8).
        configuracaoInicial(); // Chamada do método configuracaoInicial().
    }

    /*
     * Método que retorna uma matriz
     *
     */
    public PecaDeXadrez[][] getPecas() { // Método que retorna uma matriz do tipo PecaDeXadrez quando for chamado o getPecas().
       
    	// Instanciação de uma matriz mat do tipo PecaDeXadrez, passando como argumento as linhas do tabuleiro e as colunas do tabuleiro.
        PecaDeXadrez [][] mat = new PecaDeXadrez [tabuleiro.getLinhas()][tabuleiro.getColunas()];
       
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

    // Método resposável por iniciar a partida de xadrez colocando as peças no tabuleiro.
    private void configuracaoInicial() {
        /*
         *  O objeto tabuleiro (classe tabuleiro) chama o método .colocarPeca()
         *  que temos que passar dois argumentos (peca, posicao) para o método.
         *  No atributo peca, instancia-se uma nova peça diretamente pelo nome da
         *  peça, passando como argumentos os atributos que a classe da peça pede.
         *  No atributo posicao, instancia-se uma nova posição passando para o
         *  construtor da classe Posicao o número de linhas e colunas.
         */
        tabuleiro.colocarPeca(new Rook(tabuleiro, Cor.BRANCA), new Posicao(2, 1));
        tabuleiro.colocarPeca(new King(tabuleiro, Cor.PRETA), new Posicao(0, 4));
        tabuleiro.colocarPeca(new King(tabuleiro, Cor.BRANCA), new Posicao(7, 4));
    }
 }