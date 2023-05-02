package xadrez.pecas;

import jogo_de_tabuleiro.Posicao;
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

   public boolean podeMover(Posicao posicao) {
	   PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(posicao);
	   return p == null || p.getCor() != getCor();
   }

    @Override
    public boolean[][] movimentosPossiveis() {
        // Criação de uma matriz de booleanos da mesma dimensão do tabuleiro.
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        
        Posicao p = new Posicao(0, 0);
        
        // acima
        p.definirValores(posicao.getLinha() - 1, posicao.getColuna());
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
        	mat[p.getLinha()][p.getColuna()] = true;
        }
        
        // abaixo
        p.definirValores(posicao.getLinha() + 1, posicao.getColuna());
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
        	mat[p.getLinha()][p.getColuna()] = true;
        }
        
        // esquerda
        p.definirValores(posicao.getLinha(), posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
        	mat[p.getLinha()][p.getColuna()] = true;
        }
        
        // direita
        p.definirValores(posicao.getLinha(), posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
        	mat[p.getLinha()][p.getColuna()] = true;
        }
        
        // diagonal superior esquerda
        p.definirValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
        	mat[p.getLinha()][p.getColuna()] = true;
        }
        
        // diagonal superior direita
        p.definirValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
        	mat[p.getLinha()][p.getColuna()] = true;
        }
        
        // diagonal inferior esquerda
        p.definirValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
        	mat[p.getLinha()][p.getColuna()] = true;
        }
        
        // diagonal inferior direita
        p.definirValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
        	mat[p.getLinha()][p.getColuna()] = true;
        }
        
        return mat;
    }
}