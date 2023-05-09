package xadrez.pecas;

import jogo_de_tabuleiro.Posicao;
import jogo_de_tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;

public class Pawn extends PecaDeXadrez{
	
	private PartidaDeXadrez partidaDeXadrez;

    public Pawn(Tabuleiro tabuleiro, Cor cor, PartidaDeXadrez partidaDeXadrez) {
        super(tabuleiro, cor);
        this.partidaDeXadrez = partidaDeXadrez;
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
            
            // #Movimento especial en passant BRANCA.
            if (posicao.getLinha() == 3) {
                Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if (getTabuleiro().posicaoExiste(esquerda) && existeUmaPecaAdversaria(esquerda) && getTabuleiro().peca(esquerda) == partidaDeXadrez.getVulnervelEnPassant()) {
                    mat[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
                }
                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                if (getTabuleiro().posicaoExiste(direita) && existeUmaPecaAdversaria(direita) && getTabuleiro().peca(direita) == partidaDeXadrez.getVulnervelEnPassant()) {
                    mat[direita.getLinha() - 1][direita.getColuna()] = true;
                }
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
            
            // #Movimento especial en passant PRETA.
            if (posicao.getLinha() == 4) {
                Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if (getTabuleiro().posicaoExiste(esquerda) && existeUmaPecaAdversaria(esquerda) && getTabuleiro().peca(esquerda) == partidaDeXadrez.getVulnervelEnPassant()) {
                    mat[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
                }
                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                if (getTabuleiro().posicaoExiste(direita) && existeUmaPecaAdversaria(direita) && getTabuleiro().peca(direita) == partidaDeXadrez.getVulnervelEnPassant()) {
                    mat[direita.getLinha() + 1][direita.getColuna()] = true;
                }
            }

        }

        return mat;
    }

}