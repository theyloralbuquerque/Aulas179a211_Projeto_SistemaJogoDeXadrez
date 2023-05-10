package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jogo_de_tabuleiro.Peca;
import jogo_de_tabuleiro.Posicao;
import jogo_de_tabuleiro.Tabuleiro;
import xadrez.pecas.Bishop;
import xadrez.pecas.King;
import xadrez.pecas.Knight;
import xadrez.pecas.Pawn;
import xadrez.pecas.Queen;
import xadrez.pecas.Rook;

public class PartidaDeXadrez {

    private int turno;
    private Cor jogadorAtual;
    private Tabuleiro tabuleiro; // Atributo tabuleiro do tipo Tabuleiro.
    private boolean xeque;
    private boolean xequeMate;
    private PecaDeXadrez vulneravelEnPassant;
    private PecaDeXadrez promovida;

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

    public boolean getXeque() {
        return xeque;
    }

    public boolean getXequeMate() {
        return xequeMate;
    }

    public PecaDeXadrez getVulnervelEnPassant() {
        return vulneravelEnPassant;
    }

    public PecaDeXadrez getPromovida() {
        return promovida;
    }


    // Método que retorna uma matriz de peças de xadrez.
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

        // Essa condição testa se o jogador se colocou em xeque.
        if (testeXeque(jogadorAtual)) {
            desfazerMovimento(origem, destino, pecaCapturada);
            throw new XadrezException("Voce nao pode se colocar em xeque!");
        }

        PecaDeXadrez pecaMovida = (PecaDeXadrez)tabuleiro.peca(destino);

        // #Movimento especial promoção.
        promovida = null;
        if (pecaMovida instanceof Pawn) {
            if ((pecaMovida.getCor() == Cor.BRANCA && destino.getLinha() == 0) || pecaMovida.getCor() == Cor.PRETA && destino.getLinha() == 7) {
                promovida = (PecaDeXadrez)tabuleiro.peca(destino);
                promovida = substituirPecaPromovida("Q"); // O default será "Q", mas eu vou perguntar ao usuário o para qual peça ele deseja trocar.
            }
        }

        xeque = (testeXeque(oponente(jogadorAtual))) ? true : false;

        if (testeXequeMate(oponente(jogadorAtual))) {
            xequeMate = true;
        }
        else {
            proximoTurno();
        }

        // #Movimento especial en passant.
        if (pecaMovida instanceof Pawn && (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2)) {
            vulneravelEnPassant = pecaMovida;
        }
        else {
            vulneravelEnPassant = null;
        }

        return (PecaDeXadrez)pecaCapturada;
    }


    // Método que substitui uma peça promovida pela peça que o usuário desejar.
    public PecaDeXadrez substituirPecaPromovida(String tipo) {
        if (promovida == null) {
            throw new IllegalStateException("Nao ha peca para ser promovida.");
        }
        if (!tipo.equals("B") && !tipo.equals("N") && !tipo.equals("R") && !tipo.equals("Q")) {
            return promovida;
        }
        
        Posicao pos = promovida.obterPosicaoDoXadrez().paraPosicaoM();
        Peca p = tabuleiro.removerPeca(pos);
        pecasNoTabuleiro.remove(p);

        PecaDeXadrez novaPeca = novaPeca(tipo, promovida.getCor());
        tabuleiro.colocarPeca(novaPeca, pos);
        pecasNoTabuleiro.add(novaPeca);

        return novaPeca;
    }


    private PecaDeXadrez novaPeca(String tipo, Cor cor) {
        if (tipo.equals("B")) return new Bishop(tabuleiro, cor);
        if (tipo.equals("N")) return new Knight(tabuleiro, cor);
        if (tipo.equals("Q")) return new Queen(tabuleiro, cor);
        return new Rook(tabuleiro, cor);
    }

    // Método que faz mover uma peça de xadrez.
    private Peca fazerMover(Posicao origem, Posicao destino) {
        PecaDeXadrez p = (PecaDeXadrez)tabuleiro.removerPeca(origem); // Retira a peça da posição de origem.
        p.incrementarNoContadorDeMovimentos();
        Peca pecaCapturada = tabuleiro.removerPeca(destino); // Retira a peça que está na posição de destino e armazena na variável pecaCapturada.
        tabuleiro.colocarPeca(p, destino); // Chamada do método colocarPeca, colocando a peça p na posição de destino.

        if (pecaCapturada != null) {
            pecasNoTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }

        // #Movimento especial roque pequeno, lado do rei.
        if (p instanceof King && destino.getColuna() == origem.getColuna() + 2) {
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            PecaDeXadrez rook = (PecaDeXadrez)tabuleiro.removerPeca(origemT);
            tabuleiro.colocarPeca(rook, destinoT);
            rook.incrementarNoContadorDeMovimentos();
        }

        // #Movimento especial roque grande, lado da rainha.
        if (p instanceof King && destino.getColuna() == origem.getColuna() - 2) {
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            PecaDeXadrez rook = (PecaDeXadrez)tabuleiro.removerPeca(origemT);
            tabuleiro.colocarPeca(rook, destinoT);
            rook.incrementarNoContadorDeMovimentos();
        }

        // #Movimento especial en passant.
        if (p instanceof Pawn) {
            if (origem.getColuna() != destino.getColuna() && pecaCapturada == null) {
                Posicao posicaoDoPeao;
                if (p.getCor() == Cor.BRANCA) {
                    posicaoDoPeao = new Posicao(destino.getLinha() + 1, destino.getColuna());
                }
                else{
                    posicaoDoPeao = new Posicao(destino.getLinha() - 1, destino.getColuna());
                }
                pecaCapturada = tabuleiro.removerPeca(posicaoDoPeao);
                pecasCapturadas.add(pecaCapturada);
                pecasNoTabuleiro.remove(pecaCapturada);
            }
        }
        return pecaCapturada;
    }


    // Método que desfaz o movimento feito, quando necessário.
    public void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
        PecaDeXadrez p = (PecaDeXadrez)tabuleiro.removerPeca(destino);
        p.decrementarNoContadorDeMovimentos();
        tabuleiro.colocarPeca(p, origem);

        // Essa condição, se verdadeira, irá colocar a pecaCapturada de volta no lugar onde ela foi tirada.
        if (pecaCapturada != null) {
            tabuleiro.colocarPeca(pecaCapturada, destino);
            pecasCapturadas.remove(pecaCapturada);
            pecasNoTabuleiro.add(pecaCapturada);
        }

        // #Movimento especial roque pequeno, lado do reis.
        if (p instanceof King && destino.getColuna() == origem.getColuna() + 2) {
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            PecaDeXadrez rook = (PecaDeXadrez)tabuleiro.removerPeca(destinoT);
            tabuleiro.colocarPeca(rook, origemT);
            rook.decrementarNoContadorDeMovimentos();
        }

        // #Movimento especial roque grande, lado da rainha.
        if (p instanceof King && destino.getColuna() == origem.getColuna() - 2) {
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            PecaDeXadrez rook = (PecaDeXadrez)tabuleiro.removerPeca(destinoT);
            tabuleiro.colocarPeca(rook, origemT);
            rook.decrementarNoContadorDeMovimentos();
        }

        // #Movimento especial en passant.
        if (p instanceof Pawn) {
            if (origem.getColuna() != destino.getColuna() && pecaCapturada == vulneravelEnPassant) {
                PecaDeXadrez peao = (PecaDeXadrez)tabuleiro.removerPeca(destino);
                Posicao posicaoDoPeao;
                if (p.getCor() == Cor.BRANCA) {
                    posicaoDoPeao = new Posicao(3, destino.getColuna());
                }
                else{
                    posicaoDoPeao = new Posicao(4, destino.getColuna());
                }
                tabuleiro.colocarPeca(peao, posicaoDoPeao);
            }
        }

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


    private Cor oponente(Cor cor) {
        //Se a cor recebida como argumento for BRANCA, eu retorno Cor.PRETA, senão, retorno Cor.BRANCA.
        return (cor == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
    }


    private PecaDeXadrez king(Cor cor) {
        // Criação de uma lista que irá filtrar a lista pecasNoTabuleiro pela cor do king, utilizando uma expressão lambda.
        List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
        for (Peca p : list) {
            if (p instanceof King) {
                return (PecaDeXadrez)p;
            }
        }
        throw new IllegalStateException("Nao existe rei da cor " + cor + " no tabuleiro.");
    }


    // Méotodo que verifica que se o king pode estar em xeque.
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


    // Método que testa se o rei está em Xeque mate.
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
                        // O objeto origem recebe p (Peca) convertido em PecaDeXadrez, chamando o método obterPosicaoDoXadrez(), chamando o método paraPosicaoM().
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
        colocarNovaPeca('a', 1, new Rook(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('b', 1, new Knight(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('c', 1, new Bishop(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('d', 1, new Queen(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('e', 1, new King(tabuleiro, Cor.BRANCA, this));
        colocarNovaPeca('f', 1, new Bishop(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('g', 1, new Knight(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('h', 1, new Rook(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('a', 2, new Pawn(tabuleiro, Cor.BRANCA, this));
        colocarNovaPeca('b', 2, new Pawn(tabuleiro, Cor.BRANCA, this));
        colocarNovaPeca('c', 2, new Pawn(tabuleiro, Cor.BRANCA, this));
        colocarNovaPeca('d', 2, new Pawn(tabuleiro, Cor.BRANCA, this));
        colocarNovaPeca('e', 2, new Pawn(tabuleiro, Cor.BRANCA, this));
        colocarNovaPeca('f', 2, new Pawn(tabuleiro, Cor.BRANCA, this));
        colocarNovaPeca('g', 2, new Pawn(tabuleiro, Cor.BRANCA, this));
        colocarNovaPeca('h', 2, new Pawn(tabuleiro, Cor.BRANCA, this));

        colocarNovaPeca('a', 8, new Rook(tabuleiro, Cor.PRETA));
        colocarNovaPeca('b', 8, new Knight(tabuleiro, Cor.PRETA));
        colocarNovaPeca('c', 8, new Bishop(tabuleiro, Cor.PRETA));
        colocarNovaPeca('d', 8, new Queen(tabuleiro, Cor.PRETA));
        colocarNovaPeca('e', 8, new King(tabuleiro, Cor.PRETA, this));
        colocarNovaPeca('f', 8, new Bishop(tabuleiro, Cor.PRETA));
        colocarNovaPeca('g', 8, new Knight(tabuleiro, Cor.PRETA));
        colocarNovaPeca('h', 8, new Rook(tabuleiro, Cor.PRETA));
        colocarNovaPeca('a', 7, new Pawn(tabuleiro, Cor.PRETA, this));
        colocarNovaPeca('b', 7, new Pawn(tabuleiro, Cor.PRETA, this));
        colocarNovaPeca('c', 7, new Pawn(tabuleiro, Cor.PRETA, this));
        colocarNovaPeca('d', 7, new Pawn(tabuleiro, Cor.PRETA, this));
        colocarNovaPeca('e', 7, new Pawn(tabuleiro, Cor.PRETA, this));
        colocarNovaPeca('f', 7, new Pawn(tabuleiro, Cor.PRETA, this));
        colocarNovaPeca('g', 7, new Pawn(tabuleiro, Cor.PRETA, this));
        colocarNovaPeca('h', 7, new Pawn(tabuleiro, Cor.PRETA, this));

    }
 }