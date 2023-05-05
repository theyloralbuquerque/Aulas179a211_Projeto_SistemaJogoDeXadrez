package xadrez.pecas;

import jogo_de_tabuleiro.Posicao;
import jogo_de_tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

public class Pawn extends PecaDeXadrez{

    public Pawn(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        if (getCor() == Cor.BRANCA) {
            p.definirValores(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haUmaPeca(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.definirValores(posicao.getLinha() - 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haUmaPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().haUmaPeca(p2) && getContadorDeMovimentos() == 0) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.definirValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExiste(p) && existeUmaPecaAdversaria(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.definirValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExiste(p) && existeUmaPecaAdversaria(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
        }
        else {
            p.definirValores(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haUmaPeca(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.definirValores(posicao.getLinha() + 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haUmaPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().haUmaPeca(p2) && getContadorDeMovimentos() == 0) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.definirValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExiste(p) && existeUmaPecaAdversaria(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.definirValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExiste(p) && existeUmaPecaAdversaria(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

        }

        return mat;
    }

}