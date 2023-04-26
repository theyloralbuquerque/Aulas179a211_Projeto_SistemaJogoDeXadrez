package application;

import xadrez.PartidaDeXadrez;

public class Program {

	public static void main(String[] args) {

		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez(); // Instanciação do objeto partidaDeXadrez do tipo PartidaDeXadrez.

		/*
		 * O método printTabuleiro da classe UI é chamado 
		 * passando como argumento o objeto partidaDexadrez 
		 * do tipo PartidaDeXadrez que chama o método obterPecas()
		 * da classe PartidaDeXadreze passa o resultado do método 
		 * obterPecas() para o método printTabuleiro().
		 */
		UI.printTabuleiro(partidaDeXadrez.obterPecas());
	}
}