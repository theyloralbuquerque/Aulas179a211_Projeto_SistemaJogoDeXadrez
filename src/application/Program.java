package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoDoXadrez;
import xadrez.XadrezException;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez(); // Instanciação do objeto partidaDeXadrez do tipo PartidaDeXadrez.

        while (true) {
            try {
                UI.limparTela();
                /*
                 * O método printTabuleiro da classe UI é chamado
                 * passando como argumento o objeto partidaDexadrez
                 * do tipo PartidaDeXadrez que chama o método obterPecas()
                 * da classe PartidaDeXadreze passa o resultado do método
                 * obterPecas() como argumento para o método printTabuleiro().
                 */
                UI.printTabuleiro(partidaDeXadrez.obterPecas());
                System.out.println();
                System.out.print("Origem: ");
                PosicaoDoXadrez origem = UI.lerPosicaoXadrez(sc);
                
                boolean[][] movimentosPossiveis = partidaDeXadrez.movimentosPossiveis(origem);
                UI.limparTela();
                UI.printTabuleiro(partidaDeXadrez.obterPecas(), movimentosPossiveis);
                System.out.println();
                
                System.out.print("Destino: ");
                PosicaoDoXadrez destino = UI.lerPosicaoXadrez(sc);
                PecaDeXadrez pecaCapturada = partidaDeXadrez.executarJogadaDeXadrez(origem, destino);
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
    }
}