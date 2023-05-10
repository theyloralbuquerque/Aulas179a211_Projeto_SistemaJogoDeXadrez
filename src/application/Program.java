package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoDoXadrez;
import xadrez.XadrezException;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez(); // Instancia��o do objeto partidaDeXadrez do tipo PartidaDeXadrez.
        List<PecaDeXadrez> capturadas = new ArrayList<>(); // Instancia��o da lista de pe�as capturadas.

        // Enquanto getXequeMate() for falso, fa�a.
        while (!partidaDeXadrez.getXequeMate()) {
            try {
                UI.limparTela(); // Chamada do m�todo que limpa a tela cada turno. 

                UI.printPartida(partidaDeXadrez, capturadas); // Chamada do m�todo que imprime a a partida.
                System.out.println();
                System.out.print("Origem: ");
                PosicaoDoXadrez origem = UI.lerPosicaoXadrez(sc); // O retorno do m�todo lerPosicaoXadrez() ser� armazenado em origem.

                boolean[][] movimentosPossiveis = partidaDeXadrez.movimentosPossiveis(origem);
                UI.limparTela();
                UI.printTabuleiro(partidaDeXadrez.obterPecas(), movimentosPossiveis);
                System.out.println();

                System.out.print("Destino: ");
                PosicaoDoXadrez destino = UI.lerPosicaoXadrez(sc);
                PecaDeXadrez pecaCapturada = partidaDeXadrez.executarJogadaDeXadrez(origem, destino);
                
                // Se pecaCapturada fr diferente de nulo, fa�a:
                if (pecaCapturada != null) {
                    // Ser� adicionado � Lista capturadas a pecaCapturada.
                    capturadas.add(pecaCapturada);
                }

                if (partidaDeXadrez.getPromovida() != null) {
                    System.out.print("Digite para qual peca deseja promover (B/N/R/Q): ");
                    String tipo = sc.nextLine().toUpperCase();
                    while (!tipo.equals("B") && !tipo.equals("N") && !tipo.equals("R") && !tipo.equals("Q")) {
                        System.out.print("Valor invalido! Digite para qual peca deseja promover (B/N/R/Q): ");
                        tipo = sc.nextLine().toUpperCase();
                    }
                    partidaDeXadrez.substituirPecaPromovida(tipo);
                }
            }
            catch (XadrezException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        UI.limparTela();
        UI.printPartida(partidaDeXadrez, capturadas);
    }
}