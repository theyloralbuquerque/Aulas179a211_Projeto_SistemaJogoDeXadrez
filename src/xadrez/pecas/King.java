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
}