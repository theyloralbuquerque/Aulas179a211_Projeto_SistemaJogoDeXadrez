package application;

import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoDoXadrez;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez(); // Instancia��o do objeto partidaDeXadrez do tipo PartidaDeXadrez.

		while (true) {
		/*
		 * O m�todo printTabuleiro da classe UI � chamado 
		 * passando como argumento o objeto partidaDexadrez 
		 * do tipo PartidaDeXadrez que chama o m�todo obterPecas()
		 * da classe PartidaDeXadreze passa o resultado do m�todo 
		 * obterPecas() como argumento para o m�todo printTabuleiro().
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