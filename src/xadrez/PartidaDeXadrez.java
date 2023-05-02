package xadrez;

import jogo_de_tabuleiro.Peca;
import jogo_de_tabuleiro.Posicao;
import jogo_de_tabuleiro.Tabuleiro;
import xadrez.pecas.King;
import xadrez.pecas.Rook;

public class PartidaDeXadrez {

    private Tabuleiro tabuleiro; // Atributo tabuleiro do tipo Tabuleiro.

    public PartidaDeXadrez() {
        tabuleiro = new Tabuleiro(8, 8); // Instancia��o do objeto tabuleiro passando como argumento (8, 8).
        configuracaoInicial(); // Chamada do m�todo configuracaoInicial().
    }

    /*
     * M�todo que retorna uma matriz
     *
     */
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
    	return (PecaDeXadrez)pecaCapturada;
    }


    // M�todo que faz mover uma pe�a de xadrez.
    private Peca fazerMover(Posicao origem, Posicao destino) {
    	Peca p = tabuleiro.removerPeca(origem); // Retira a pe�a da posi��o de origem.
    	Peca pecaCapturada = tabuleiro.removerPeca(destino); // Retira a pe�a que est� na posi��o de destino e armazena na vari�vel pecaCapturada.
    	tabuleiro.colocarPeca(p, destino); // Chamada do m�todo colocarPeca, colocando a pe�a p na posi��o de destino.
    	return pecaCapturada;
    }


    // M�todo que valida se na posi��o de origem de uma pe�a realemente h� uma pe�a l�.
    private void validarPosicaoDeOrigem(Posicao posicao) {
    	if (!tabuleiro.haUmaPeca(posicao)) {
    		throw new XadrezException("Nao ha peca na posicao de origem.");
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


    // M�todo que recebe as coordenas da PosicaoDoXadrez.
    private void colocarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {

    	/*
    	 * O objeto tabuleiro chama o m�todo colocarPeca, passando como argumentos
    	 * um objeto peca e as coordenadas de xadrez que o usu�rio digitar convertidas
    	 * em coordenadas de matriz.
    	 */
    	tabuleiro.colocarPeca(peca, new PosicaoDoXadrez(coluna, linha).paraPosicaoM());
    }


    // M�todo respos�vel por iniciar a partida de xadrez colocando as pe�as no tabuleiro.
    private void configuracaoInicial() {
        /*
         *  Chama o m�todo .colocarNovaPeca() passando tr�s argumentos
         *  (coluna, linha, peca) para o m�todo.
         *  No atributo peca, instancia-se uma nova pe�a diretamente pelo nome da
         *  pe�a, passando como argumentos os atributos que a classe da pe�a pede.
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