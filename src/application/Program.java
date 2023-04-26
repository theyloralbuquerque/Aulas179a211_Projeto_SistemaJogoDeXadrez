package application;

import xadrez.PartidaDeXadrez;

public class Program {

	public static void main(String[] args) {

		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez(); // Instancia��o do objeto partidaDeXadrez do tipo PartidaDeXadrez.

		/*
		 * O m�todo printTabuleiro da classe UI � chamado 
		 * passando como argumento o objeto partidaDexadrez 
		 * do tipo PartidaDeXadrez que chama o m�todo obterPecas()
		 * da classe PartidaDeXadreze passa o resultado do m�todo 
		 * obterPecas() para o m�todo printTabuleiro().
		 */
		UI.printTabuleiro(partidaDeXadrez.obterPecas());
	}
}