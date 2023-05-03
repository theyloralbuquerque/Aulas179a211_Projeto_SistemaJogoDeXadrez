package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.Cor;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoDoXadrez;

public class UI {

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    // M�todo para limpar a tela a cada jogada.
    // https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static PosicaoDoXadrez lerPosicaoXadrez(Scanner sc) {
        try {
            String s = sc.nextLine();
            char coluna = s.charAt(0);
            int linha = Integer.parseInt(s.substring(1)); // linha recebe a String s cortada a partir da posi��o de caractere 1, convertido para Integer.
            return new PosicaoDoXadrez(coluna, linha);
        }
        catch (RuntimeException e) {
            throw new InputMismatchException("Erro ao ler posicao do xadrez. Valores validos vao de a1 a h8.");
        }
    }


    // M�todo que ir� imprimir informa��es da partida, como turno e jogador da vez.
    public static void printPartida(PartidaDeXadrez partidaDeXadrez) {
        printTabuleiro(partidaDeXadrez.obterPecas());
        System.out.println();
        System.out.println("Turno: " + partidaDeXadrez.getTurno());
        System.out.println("Espeando o jogador: " + partidaDeXadrez.getjogadorAtual());
    }


    // M�todo que ir� imprimir o tabuleiro.
    // Recebe uma matriz do tipo PecaDeXadrez que chamaremos de pecas.
    public static void printTabuleiro(PecaDeXadrez[][] pecas) {

        // Enquanto i for menor que o n�mero de linhas da matriz pecas.
        for (int i = 0; i < pecas.length; i++) {

            System.out.print((8 - i) + " "); // 8 menos o valor de i seguido de um espa�o.

            // Enquanto j for menor que o n�mero de linhas da matriz pecas.
            for (int j = 0; j < pecas.length; j++) {
                printPeca(pecas[i][j], false); // Chamada do m�todo printPeca passando como par�mentros o valor da matriz
                                        // pecas.
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h"); // Impress�o da linha de orienta��o das colunas do xadrez.
    }


    // M�todo que ir� imprimir o tabuleiro considerando os movimentos poss�veis.
    // Recebe uma matriz do tipo PecaDeXadrez que chamaremos de pecas e a matriz boolean movimentosPossiveis.
    public static void printTabuleiro(PecaDeXadrez[][] pecas, boolean[][] movimentosPossiveis) {

        // Enquanto i for menor que o n�mero de linhas da matriz pecas.
        for (int i = 0; i < pecas.length; i++) {

            System.out.print((8 - i) + " "); // 8 menos o valor de i seguido de um espa�o.

            // Enquanto j for menor que o n�mero de linhas da matriz pecas.
            for (int j = 0; j < pecas.length; j++) {
                printPeca(pecas[i][j], movimentosPossiveis[i][j]); // Chamada do m�todo printPeca passando como par�mentros o valor da matriz pecas.

            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h"); // Impress�o da linha de orienta��o das colunas do xadrez.
    }


    // M�todo que exibe a pe�a no tabuleiro de xadrez.
    private static void printPeca(PecaDeXadrez peca, boolean fundo) {
        if (fundo) {
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (peca == null) {
            System.out.print("-" + ANSI_RESET);
        } else {
            if (peca.getCor() == Cor.BRANCA) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            } else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" "); // Espa�o para evitar que as pecas ou os tra�os (-) fiquem grudados na exibi��o do tabuleiro.
    }
}
