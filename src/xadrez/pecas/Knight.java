package xadrez.pecas;

import jogo_de_tabuleiro.Posicao;
import jogo_de_tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

public class Knight extends PecaDeXadrez{

    public Knight(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

   @Override
   public String toString() {
       return "N";
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

        // esquerda alto.
        p.definirValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // alto esquerda.
        p.definirValores(posicao.getLinha() - 2, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // alto direita.
        p.definirValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // direita alto.
        p.definirValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // direita baixo.
        p.definirValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // baixo direita.
        p.definirValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // baixo esquerda.
        p.definirValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // esquerda baixo.
        p.definirValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        return mat;
    }
}
