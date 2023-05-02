package xadrez.pecas;

import jogo_de_tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

public class King extends PecaDeXadrez{

    public King(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

   @Override
   public String toString() {
       return "K";
   }


    @Override
    public boolean[][] movimentosPossiveis() {
        // Criação de uma matriz de booleanos da mesma dimensão do tabuleiro.
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        return mat;
    }
}