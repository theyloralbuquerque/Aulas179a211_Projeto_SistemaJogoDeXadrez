package xadrez;

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
    public PecaDeXadrez[][] obterPecas() { // Método que retorna uma matriz do tipo PecaDeXadrez quando for chamado o getPecas().
       
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
    
    // Método que recebe as coordenas da PosicaoDoXadrez.
    private void colocarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
    	
    	/*
    	 * O objeto tabuleiro chama o método colocarPeca, passando como argumentos 
    	 * um objeto peca e as coordenadas de xadrez que o usuário digitar convertidas
    	 * em coordenadas de matriz.
    	 */
    	tabuleiro.colocarPeca(peca, new PosicaoDoXadrez(coluna, linha).paraPosicaoM());
    }

    // Método resposável por iniciar a partida de xadrez colocando as peças no tabuleiro.
    private void configuracaoInicial() {
        /*
         *  Chama o método .colocarNovaPeca() passando três argumentos 
         *  (coluna, linha, peca) para o método.
         *  No atributo peca, instancia-se uma nova peça diretamente pelo nome da
         *  peça, passando como argumentos os atributos que a classe da peça pede.
         */
    	colocarNovaPeca('c', 1, new Rook(tabuleiro, Cor.BRANCA));
    	colocarNovaPeca('c', 2, new Rook(tabuleiro, Cor.BRANCA));
    	colocarNovaPeca('d', 2, new Rook(tabuleiro, Cor.BRANCA));
    	colocarNovaPeca('e', 2, new Rook(tabuleiro, Cor.BRANCA));
    	colocarNovaPeca('e', 1, new Rook(tabuleiro, Cor.BRANCA));
    	colocarNovaPeca('d', 1, new King(tabuleiro, Cor.BRANCA));

    	colocarNovaPeca('c', 7, new Rook(tabuleiro, Cor.PRETA));
    	colocarNovaPeca('c', 8, new Rook(tabuleiro, Cor.PRETA));
    	colocarNovaPeca('d', 7, new Rook(tabuleiro, Cor.PRETA));
    	colocarNovaPeca('e', 7, new Rook(tabuleiro, Cor.PRETA));
    	colocarNovaPeca('e', 8, new Rook(tabuleiro, Cor.PRETA));
    	colocarNovaPeca('d', 8, new King(tabuleiro, Cor.PRETA));
    }
 }