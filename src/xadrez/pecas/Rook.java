package xadrez.pecas;

import jogo_de_tabuleiro.Posicao;
import jogo_de_tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

public class Rook extends PecaDeXadrez{

    public Rook(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        // Criação de uma matriz de booleanos da mesma dimensão do tabuleiro.
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        
        Posicao p = new Posicao (0,0);
        
        // Acima.
        p.definirValores(posicao.getLinha() - 1, posicao.getColuna());
        while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().haUmaPeca(p)) {
        	mat[p.getLinha()][p.getColuna()] = true;
        	p.setLinha(p.getLinha() - 1);
        }
        if (getTabuleiro().posicaoExiste(p) && existeUmaPecaAdversaria(p)) {
        	mat[p.getLinha()][p.getColuna()] = true;
        }
        
        // Esquerda.
        p.definirValores(posicao.getLinha(), posicao.getColuna() - 1);
        while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().haUmaPeca(p)) {
        	mat[p.getLinha()][p.getColuna()] = true;
        	p.setColuna(p.getColuna() - 1);
        }
        if (getTabuleiro().posicaoExiste(p) && existeUmaPecaAdversaria(p)) {
        	mat[p.getLinha()][p.getColuna()] = true;
        }
        
        // Direita.
        p.definirValores(posicao.getLinha(), posicao.getColuna() + 1);
        while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().haUmaPeca(p)) {
        	mat[p.getLinha()][p.getColuna()] = true;
        	p.setColuna(p.getColuna() + 1);
        }
        if (getTabuleiro().posicaoExiste(p) && existeUmaPecaAdversaria(p)) {
        	mat[p.getLinha()][p.getColuna()] = true;
        }
        
        // Abaixo.
        p.definirValores(posicao.getLinha() + 1, posicao.getColuna());
        while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().haUmaPeca(p)) {
        	mat[p.getLinha()][p.getColuna()] = true;
        	p.setLinha(p.getLinha() + 1);
        }
        if (getTabuleiro().posicaoExiste(p) && existeUmaPecaAdversaria(p)) {
        	mat[p.getLinha()][p.getColuna()] = true;
        }
        
        return mat;
    }
}