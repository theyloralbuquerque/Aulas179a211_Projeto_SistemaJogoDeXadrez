package application;

import xadrez.PecaDeXadrez;
public class UI {

    // Método que irá imprimir o tabuleiro.
	// Recebe uma matriz do tipo PecaDeXadrez que chamaremos de pecas.
    public static void printTabuleiro(PecaDeXadrez[][] pecas) {
    	
        // Enquanto i for menor que o número de linhas da matriz pecas.
        for (int i=0; i<pecas.length; i++) {
        	
            System.out.print((8 - i) + " "); // 8 menos o valor de i seguido de um espaço.
            
            // Enquanto j for menor que o número de linhas da matriz pecas.
            for (int j=0; j<pecas.length; j++) {
                printPeca(pecas[i][j]); // Chamada do método printPeca passando como parâmentros o valor da matriz pecas.
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h"); // Impressão da linha de orientação das colunas do xadrez.
    }

    private static void printPeca(PecaDeXadrez peca) {
        if (peca == null) { // Se peca foi igual a null, significa que não tinha peca nessa posição do tabuleiro.
            System.out.print("-");
        }
        else { // Senão, eu imprimo a peca.
            System.out.print(peca);
        }
        System.out.print(" "); // Espaço para evitar que as pecas ou os traços (-) fiquem grudados na exibição do tabuleiro.
    }
}