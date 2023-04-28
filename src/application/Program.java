package application;

import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoDoXadrez;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez(); // Instanciação do objeto partidaDeXadrez do tipo PartidaDeXadrez.

		while (true) {
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
		
		System.out.println();
		System.out.print("Destino: ");
		PosicaoDoXadrez destino = UI.lerPosicaoXadrez(sc);
		
		PecaDeXadrez pecaCapturada = partidaDeXadrez.executarJogadaDeXadrez(origem, destino);
		}
	}
}