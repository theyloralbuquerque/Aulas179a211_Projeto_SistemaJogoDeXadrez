package xadrez.pecas;

import jogo_de_tabuleiro.Posicao;
import jogo_de_tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;

public class King extends PecaDeXadrez{

    private PartidaDeXadrez partidaDeXadrez;

    public King(Tabuleiro tabuleiro, Cor cor, PartidaDeXadrez partidaDeXadrez) {
        super(tabuleiro, cor);
        this.partidaDeXadrez = partidaDeXadrez;
    }

   @Override
   public String toString() {
       return "K";
   }

   public boolean podeMover(Posicao posicao) {
	   PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(posicao);
	   return p == null || p.getCor() != getCor();
   }

   //Metodo que testa se a Rook está apta a fazer um roque.
   private boolean testeRoqueRook(Posicao posicao) {
       PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(posicao);
       return p != null && p instanceof Rook && p.getCor() == getCor() && p.getContadorDeMovimentos() == 0;
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

        // Movimento especial roque.
        if (getContadorDeMovimentos() == 0 && !partidaDeXadrez.getXeque()) {
            // Movimento especial roque da torre do lado do rei (roque pequeno).
            Posicao posT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
            if (testeRoqueRook(posT1)) {
                Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
                if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
                    mat[posicao.getLinha()][posicao.getColuna() + 2] = true;
                }
            }
            // Movimento especial roque da torre do lado da rainha (roque grande).
            Posicao posT2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
            if (testeRoqueRook(posT2)) {
                Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
                Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
                if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
                    mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
                }
            }
        }

        return mat;
    }
}