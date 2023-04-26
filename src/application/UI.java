package application;

import xadrez.PecaDeXadrez;
public class UI {

    // M�todo que ir� imprimir o tabuleiro.
	// Recebe uma matriz do tipo PecaDeXadrez que chamaremos de pecas.
    public static void printTabuleiro(PecaDeXadrez[][] pecas) {
    	
        // Enquanto i for menor que o n�mero de linhas da matriz pecas.
        for (int i=0; i<pecas.length; i++) {
        	
            System.out.print((8 - i) + " "); // 8 menos o valor de i seguido de um espa�o.
            
            // Enquanto j for menor que o n�mero de linhas da matriz pecas.
            for (int j=0; j<pecas.length; j++) {
                printPeca(pecas[i][j]); // Chamada do m�todo printPeca passando como par�mentros o valor da matriz pecas.
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h"); // Impress�o da linha de orienta��o das colunas do xadrez.
    }

    private static void printPeca(PecaDeXadrez peca) {
        if (peca == null) { // Se peca foi igual a null, significa que n�o tinha peca nessa posi��o do tabuleiro.
            System.out.print("-");
        }
        else { // Sen�o, eu imprimo a peca.
            System.out.print(peca);
        }
        System.out.print(" "); // Espa�o para evitar que as pecas ou os tra�os (-) fiquem grudados na exibi��o do tabuleiro.
    }
}