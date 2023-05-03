package xadrez;

import java.util.ArrayList;
import java.util.List;

import jogo_de_tabuleiro.Peca;
import jogo_de_tabuleiro.Posicao;
import jogo_de_tabuleiro.Tabuleiro;
import xadrez.pecas.King;
import xadrez.pecas.Rook;

public class PartidaDeXadrez {

    private int turno;
    private Cor jogadorAtual;
    private Tabuleiro tabuleiro; // Atributo tabuleiro do tipo Tabuleiro.

    private List<Peca> pecasNoTabuleiro = new ArrayList<>();
    private List<Peca> pecasCapturadas = new ArrayList<>();

    public PartidaDeXadrez() {
        tabuleiro = new Tabuleiro(8, 8); // Instanciação do objeto tabuleiro passando como argumento (8, 8).
        turno = 1;
        jogadorAtual = Cor.BRANCA;
        configuracaoInicial(); // Chamada do método configuracaoInicial().
    }


    public int getTurno() {
        return turno;
    }

    public Cor getjogadorAtual() {
        return jogadorAtual;
    }


    /*
     * Método que retorna uma matriz
     *
     */
    public PecaDeXadrez[][] obterPecas() { // Método que retorna uma matriz do tipo PecaDeXadrez quando for chamado o getPecas().

        // Instanciação de uma matriz mat do tipo PecaDeXadrez, passando como argumento as linhas do tabuleiro e as colunas do tabuleiro.
        PecaDeXadrez [][] mat = new PecaDeXadrez [tabuleiro.getLinhas()][tabuleiro.getColunas()];

        // Enquanto o i for menor que as linhas do tabuleiro, faça:
        for (int i=0; i<tabuleiro.getLinhas(); i++) {

            // Enquanto o j for menor que as colunas do tabuleiro, faça:
            for (int j=0; j<tabuleiro.getColunas(); j++) {

                /* a matriz mat na posição [i][j] recebe o retorno do método peca da o objeto tabuleiro (classe Tabuleiro),
                 * tudo isso feito downcasting para a classe PecaDeXadrez, para que o compilador interprete isso como uma peça
                 * de xadrez e não uma peça comum.
                 */
                mat[i][j] = (PecaDeXadrez)tabuleiro.peca(i, j);
            }
        }
        return mat;
    }


    public boolean[][] movimentosPossiveis(PosicaoDoXadrez posicaoDeOrigem) {
        Posicao posicao = posicaoDeOrigem.paraPosicaoM();
        validarPosicaoDeOrigem(posicao);
        return tabuleiro.peca(posicao).movimentosPossiveis();
    }


    // Método que executa uma jogada de xadrez.
    public PecaDeXadrez executarJogadaDeXadrez(PosicaoDoXadrez posicaoDeOrigem, PosicaoDoXadrez posicaoDeDestino) {

        /* A variável origem do tipo Posicao recebe o retorno do método paraPosicaoM(),
         * que irá converter o valor de posicaoDeOrigem para posição da matriz.
         */
        Posicao origem = posicaoDeOrigem.paraPosicaoM();

        /* A variável destino do tipo Posicao recebe o retorno do método paraPosicaoM(),
         * que irá converter o valor de posicaoDeOrigem para posição da matriz.
         */
        Posicao destino = posicaoDeDestino.paraPosicaoM();

        validarPosicaoDeOrigem(origem);
        validarPosicaoDeDestino(origem, destino);
        Peca pecaCapturada = fazerMover(origem, destino);
        proximoTurno();
        return (PecaDeXadrez)pecaCapturada;
    }


    // Método que faz mover uma peça de xadrez.
    private Peca fazerMover(Posicao origem, Posicao destino) {
        Peca p = tabuleiro.removerPeca(origem); // Retira a peça da posição de origem.
        Peca pecaCapturada = tabuleiro.removerPeca(destino); // Retira a peça que está na posição de destino e armazena na variável pecaCapturada.
        tabuleiro.colocarPeca(p, destino); // Chamada do método colocarPeca, colocando a peça p na posição de destino.

        // Se a pecaCapturada for diferente de null, então.
        if (pecaCapturada != null) {
            pecasNoTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }
        return pecaCapturada;
    }


    // Método que valida se na posição de origem de uma peça realemente há uma peça lá.
    private void validarPosicaoDeOrigem(Posicao posicao) {
        if (!tabuleiro.haUmaPeca(posicao)) {
            throw new XadrezException("Nao ha peca na posicao de origem.");
        }

        // Se o jogadorAtual tiver cor diferente da peca na posicao informada.
        if (jogadorAtual != ((PecaDeXadrez)tabuleiro.peca(posicao)).getCor()) {
            throw new XadrezException("A peca escolhida nao e sua.");
        }
        if (!tabuleiro.peca(posicao).existeAlgumMovimentoPossivel()) {
            throw new XadrezException("Nao existem movimentos possiveis para a peca.");
        }
    }

    // Método que valida se a posição de destino de uma peça é um movimento possível, com base na posição de origem.
    private void validarPosicaoDeDestino(Posicao origem, Posicao destino) {

        // Se para a peça de origem a posição de destino não é um movimento possível.
        if (!tabuleiro.peca(origem).movimentoPossivel(destino)) {
            throw new XadrezException("A peca escolhida nao pode se mover para a posicao de destino.");
        }
    }


    // Método que troca o turno de jogada.
    private void proximoTurno() {
        turno++;

        // Se o jogadorAtual tiver a cor Branca, então a cor dele será Preta, senão, será branca.
        jogadorAtual = (jogadorAtual == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
    }

    // Método que recebe as coordenas da PosicaoDoXadrez.
    private void colocarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {

        /*
         * O objeto tabuleiro chama o método colocarPeca, passando como argumentos
         * um objeto peca e as coordenadas de xadrez que o usuário digitar convertidas
         * em coordenadas de matriz.
         */
        tabuleiro.colocarPeca(peca, new PosicaoDoXadrez(coluna, linha).paraPosicaoM());

        pecasNoTabuleiro.add(peca);
    }


    // Método resposável por iniciar a partida de xadrez colocando as peças no tabuleiro.
    private void configuracaoInicial() {
        /*
         *  Chama o método .colocarNovaPeca() passando três argumentos
         *  (coluna, linha, peca) para o método.
         *  No atributo peca, instancia-se uma nova peça diretamente pelo nome da
         *  peça, passando como argumentos os atributos que a classe da peça pede.
         */
        colocarNovaPeca('c', 1, new Rook(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('c', 2, new Rook(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('d', 2, new Rook(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('e', 2, new Rook(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('e', 1, new Rook(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('d', 1, new King(tabuleiro, Cor.BRANCA));

        colocarNovaPeca('c', 7, new Rook(tabuleiro, Cor.PRETA));
        colocarNovaPeca('c', 8, new Rook(tabuleiro, Cor.PRETA));
        colocarNovaPeca('d', 7, new Rook(tabuleiro, Cor.PRETA));
        colocarNovaPeca('e', 7, new Rook(tabuleiro, Cor.PRETA));
        colocarNovaPeca('e', 8, new Rook(tabuleiro, Cor.PRETA));
        colocarNovaPeca('d', 8, new King(tabuleiro, Cor.PRETA));
    }
 }