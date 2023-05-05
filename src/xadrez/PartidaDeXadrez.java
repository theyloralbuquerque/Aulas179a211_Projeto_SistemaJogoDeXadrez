package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jogo_de_tabuleiro.Peca;
import jogo_de_tabuleiro.Posicao;
import jogo_de_tabuleiro.Tabuleiro;
import xadrez.pecas.King;
import xadrez.pecas.Pawn;
import xadrez.pecas.Rook;

public class PartidaDeXadrez {

    private int turno;
    private Cor jogadorAtual;
    private Tabuleiro tabuleiro; // Atributo tabuleiro do tipo Tabuleiro.
    private boolean xeque;
    private boolean xequeMate;

    private List<Peca> pecasNoTabuleiro = new ArrayList<>();
    private List<Peca> pecasCapturadas = new ArrayList<>();

    public PartidaDeXadrez() {
        tabuleiro = new Tabuleiro(8, 8); // Instancia��o do objeto tabuleiro passando como argumento (8, 8).
        turno = 1;
        jogadorAtual = Cor.BRANCA;
        configuracaoInicial(); // Chamada do m�todo configuracaoInicial().
    }

    public int getTurno() {
        return turno;
    }

    public Cor getjogadorAtual() {
        return jogadorAtual;
    }

    public boolean getXeque() {
        return xeque;
    }

    public boolean getXequeMate() {
        return xequeMate;
    }



    // M�todo que retorna uma matriz de pe�as de xadrez.
    public PecaDeXadrez[][] obterPecas() { // M�todo que retorna uma matriz do tipo PecaDeXadrez quando for chamado o getPecas().

        // Instancia��o de uma matriz mat do tipo PecaDeXadrez, passando como argumento as linhas do tabuleiro e as colunas do tabuleiro.
        PecaDeXadrez [][] mat = new PecaDeXadrez [tabuleiro.getLinhas()][tabuleiro.getColunas()];

        // Enquanto o i for menor que as linhas do tabuleiro, fa�a:
        for (int i=0; i<tabuleiro.getLinhas(); i++) {

            // Enquanto o j for menor que as colunas do tabuleiro, fa�a:
            for (int j=0; j<tabuleiro.getColunas(); j++) {

                /* a matriz mat na posi��o [i][j] recebe o retorno do m�todo peca da o objeto tabuleiro (classe Tabuleiro),
                 * tudo isso feito downcasting para a classe PecaDeXadrez, para que o compilador interprete isso como uma pe�a
                 * de xadrez e n�o uma pe�a comum.
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


    // M�todo que executa uma jogada de xadrez.
    public PecaDeXadrez executarJogadaDeXadrez(PosicaoDoXadrez posicaoDeOrigem, PosicaoDoXadrez posicaoDeDestino) {

        /* A vari�vel origem do tipo Posicao recebe o retorno do m�todo paraPosicaoM(),
         * que ir� converter o valor de posicaoDeOrigem para posi��o da matriz.
         */
        Posicao origem = posicaoDeOrigem.paraPosicaoM();

        /* A vari�vel destino do tipo Posicao recebe o retorno do m�todo paraPosicaoM(),
         * que ir� converter o valor de posicaoDeOrigem para posi��o da matriz.
         */
        Posicao destino = posicaoDeDestino.paraPosicaoM();

        validarPosicaoDeOrigem(origem);
        validarPosicaoDeDestino(origem, destino);
        Peca pecaCapturada = fazerMover(origem, destino);

        // Essa condi��o testa se o jogador se colocou em xeque.
        if (testeXeque(jogadorAtual)) {
            desfazerMovimento(origem, destino, pecaCapturada);
            throw new XadrezException("Voce nao pode se colocar em xaque!");
        }

        xeque = (testeXeque(oponente(jogadorAtual))) ? true : false;

        if (testeXequeMate(oponente(jogadorAtual))) {
            xequeMate = true;
        }
        else {
            proximoTurno();
        }

        return (PecaDeXadrez)pecaCapturada;
    }


    // M�todo que faz mover uma pe�a de xadrez.
    private Peca fazerMover(Posicao origem, Posicao destino) {
        PecaDeXadrez p = (PecaDeXadrez)tabuleiro.removerPeca(origem); // Retira a pe�a da posi��o de origem.
        p.incrementarNoContadorDeMovimentos();
        Peca pecaCapturada = tabuleiro.removerPeca(destino); // Retira a pe�a que est� na posi��o de destino e armazena na vari�vel pecaCapturada.
        tabuleiro.colocarPeca(p, destino); // Chamada do m�todo colocarPeca, colocando a pe�a p na posi��o de destino.

        // Se a pecaCapturada for diferente de null, ent�o.
        if (pecaCapturada != null) {
            pecasNoTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }
        return pecaCapturada;
    }


    // M�todo que desfaz o movimento feito, quando necess�rio.
    public void desfazerMovimento(Posicao posicaoDeOrigem, Posicao posicaoDeDestino, Peca pecaCapturada) {
        PecaDeXadrez p = (PecaDeXadrez)tabuleiro.removerPeca(posicaoDeDestino);
        p.decrementarNoContadorDeMovimentos();
        tabuleiro.colocarPeca(p, posicaoDeOrigem);

        // Essa condi��o, se verdadeira, ir� colocar a pecaCapturada de volta no lugar onde ela foi tirada.
        if (pecaCapturada != null) {
            tabuleiro.colocarPeca(pecaCapturada, posicaoDeDestino);
            pecasCapturadas.remove(pecaCapturada);
            pecasNoTabuleiro.add(pecaCapturada);
        }
    }


    // M�todo que valida se na posi��o de origem de uma pe�a realemente h� uma pe�a l�.
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

    // M�todo que valida se a posi��o de destino de uma pe�a � um movimento poss�vel, com base na posi��o de origem.
    private void validarPosicaoDeDestino(Posicao origem, Posicao destino) {

        // Se para a pe�a de origem a posi��o de destino n�o � um movimento poss�vel.
        if (!tabuleiro.peca(origem).movimentoPossivel(destino)) {
            throw new XadrezException("A peca escolhida nao pode se mover para a posicao de destino.");
        }
    }


    // M�todo que troca o turno de jogada.
    private void proximoTurno() {
        turno++;

        // Se o jogadorAtual tiver a cor Branca, ent�o a cor dele ser� Preta, sen�o, ser� branca.
        jogadorAtual = (jogadorAtual == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
    }


    private Cor oponente(Cor cor) {

        //Se a cor recebida como argumento for BRANCA, eu retorno Cor.PRETA, sen�o, retorno Cor.BRANCA.
        return (cor == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
    }


    private PecaDeXadrez king(Cor cor) {
        // Cria��o de uma lista que ir� filtrar a lista pecasNoTabuleiro pela cor do king, utilizando uma express�o lambda.
        List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
        for (Peca p : list) {
            if (p instanceof King) {
                return (PecaDeXadrez)p;
            }
        }
        throw new IllegalStateException("Nao existe rei da cor " + cor + " no tabuleiro.");
    }


    // M�otodo que verifica que se o king pode estar em xeque.
    private boolean testeXeque(Cor cor) {
        Posicao kingPosicao = king(cor).obterPosicaoDoXadrez().paraPosicaoM();
        List<Peca> pecasDoOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
        for (Peca p : pecasDoOponente) {
            boolean[][] mat = p.movimentosPossiveis();
            if (mat[kingPosicao.getLinha()][kingPosicao.getColuna()]) {
                return true;
            }
        }
        return false;
    }


    // M�todo que testa se o rei est� em Xeque mate.
    public boolean testeXequeMate(Cor cor) {
        // Se testeXeque() retornar false, retorne false.
        if (!testeXeque(cor)) {
            return false;
        }
        List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
        for (Peca p : list) {
            boolean[][] mat = p.movimentosPossiveis();
            for (int i=0; i<tabuleiro.getLinhas(); i++) {
                for (int j=0; j<tabuleiro.getColunas(); j++) {
                    if (mat[i][j]) {
                        // O objeto origem recebe p (Peca) convertido em PecaDeXadrez, chamando o m�todo obterPosicaoDoXadrez(), chamando o m�todo paraPosicaoM().
                        Posicao origem = ((PecaDeXadrez)p).obterPosicaoDoXadrez().paraPosicaoM();
                        Posicao destino = new Posicao(i, j);
                        Peca pecaCapturada = fazerMover(origem, destino);
                        boolean testeXeque = testeXeque(cor);
                        desfazerMovimento(origem, destino, pecaCapturada);
                        if (!testeXeque) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


    // M�todo que recebe as coordenas da PosicaoDoXadrez.
    private void colocarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
        /*
         * O objeto tabuleiro chama o m�todo colocarPeca, passando como argumentos
         * um objeto peca e as coordenadas de xadrez que o usu�rio digitar convertidas
         * em coordenadas de matriz.
         */
        tabuleiro.colocarPeca(peca, new PosicaoDoXadrez(coluna, linha).paraPosicaoM());

        pecasNoTabuleiro.add(peca);
    }


    // M�todo respos�vel por iniciar a partida de xadrez colocando as pe�as no tabuleiro.
    private void configuracaoInicial() {
        /*
         *  Chama o m�todo .colocarNovaPeca() passando tr�s argumentos
         *  (coluna, linha, peca) para o m�todo.
         *  No atributo peca, instancia-se uma nova pe�a diretamente pelo nome da
         *  pe�a, passando como argumentos os atributos que a classe da pe�a pede.
         */
        colocarNovaPeca('a', 1, new Rook(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('e', 1, new King(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('h', 1, new Rook(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('a', 2, new Pawn(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('b', 2, new Pawn(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('c', 2, new Pawn(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('d', 2, new Pawn(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('e', 2, new Pawn(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('f', 2, new Pawn(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('g', 2, new Pawn(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('h', 2, new Pawn(tabuleiro, Cor.BRANCA));        

        colocarNovaPeca('a', 8, new Rook(tabuleiro, Cor.PRETA));
        colocarNovaPeca('e', 8, new King(tabuleiro, Cor.PRETA));
        colocarNovaPeca('h', 8, new Rook(tabuleiro, Cor.PRETA));
        colocarNovaPeca('a', 7, new Pawn(tabuleiro, Cor.PRETA));
        colocarNovaPeca('b', 7, new Pawn(tabuleiro, Cor.PRETA));
        colocarNovaPeca('c', 7, new Pawn(tabuleiro, Cor.PRETA));
        colocarNovaPeca('d', 7, new Pawn(tabuleiro, Cor.PRETA));
        colocarNovaPeca('e', 7, new Pawn(tabuleiro, Cor.PRETA));
        colocarNovaPeca('f', 7, new Pawn(tabuleiro, Cor.PRETA));
        colocarNovaPeca('g', 7, new Pawn(tabuleiro, Cor.PRETA));
        colocarNovaPeca('h', 7, new Pawn(tabuleiro, Cor.PRETA));

    }
 }