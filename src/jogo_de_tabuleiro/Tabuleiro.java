package jogo_de_tabuleiro;

public class Tabuleiro {

    private int linhas;
    private int colunas;
    private Peca[][] pecas; // Uma matriz do tipo Peca que chamaremos de pecas.

    public Tabuleiro(int linhas, int colunas) {
        if (linhas < 1 || colunas < 1) {
            throw new TabuleiroException("Erro na criacao do tabuleiro: deve haver pelo menos 1 linha e 1 coluna.");
        }

        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Peca [linhas][colunas]; // Uma nova matriz pecas do tipo Peca com os valores de [linhas] e [colunas].
    }

    
    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    
     // M�todo peca do tipo Peca, que recebe como argumento linha e coluna e retorna a matriz pecas com os valores de linha e coluna.
    public Peca peca(int linha, int coluna) {
        // Se o m�todo posi�aoExiste n�o retornar true.
        if (!posicaoExiste(linha, coluna)) {
            throw new TabuleiroException("Posicaoo nao encontrada no tabuleiro.");
        }
        return pecas[linha][coluna];
    }

    /*
     * Sobrecarga do m�todo peca do tipo Peca, que recebe como argumento o objeto posicao do tipo Posicao
     * e retorna a matriz pecas com os valores de linha e coluna.
     */
    public Peca peca(Posicao posicao) {
        
    	// Se o m�todo posi�aoExiste n�o retornar true.
        if (!posicaoExiste(posicao)) {
            throw new TabuleiroException("Posi��o n�o encontrada no tabuleiro.");
        }
        return pecas[posicao.getLinha()][posicao.getColuna()];
    }
    

    // M�todo que aloca uma pe�a no tabuleiro
    public void colocarPeca(Peca peca, Posicao posicao) {
        
    	// Se o m�todo retornar true (o valor da posicao diferente de null), quer dizer que tem uma pe�a nessa posi��o.
        if (haUmaPeca(posicao)) {
            throw new TabuleiroException("J� existe uma pe�a na posi��o" + posicao);
        }
        
        // A matriz na posi��o[posicao.getLinha()][posicao.getColuna()] vai receber o valor de peca.
        pecas[posicao.getLinha()][posicao.getColuna()] = peca;
     
        // O atributo posicao da classe Peca recebe o valor de posicao, portanto o valor de peca n�o � mais null.
        peca.posicao = posicao;
    }
    
    
    // M�todo que remove uma pe�a.
    public Peca removerPeca(Posicao posicao) {
    	
    	// Programa��o defensiva. Testa se posi��o passada como argumento existe.
    	if (!posicaoExiste(posicao)) {
    		throw new TabuleiroException("Posicao nao encontrada no tabuleiro.");
    	}
    	
    	// Se a pe�a do tabuleiro nessa posi��o for null, retorne null.
    	if (peca(posicao) == null) {
    		return null;
    	}
    	Peca aux = peca(posicao);
    	aux.posicao = null;
    	pecas[posicao.getLinha()][posicao.getColuna()] = null;
    	return aux;
    }
    
    

    // M�todo auxiliar, que retorna se a posi��o existe ou n�o, retornando um True ou False.
    private boolean posicaoExiste(int linha, int coluna) {
        
    	// Se a condi��o abaixo for verdadeira retorna true sen�o retorna false.
        return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }

    
    // M�todo ir� retonar a chamada do m�todo auxiliar acima.
    public boolean posicaoExiste(Posicao posicao) {
        return posicaoExiste(posicao.getLinha(), posicao.getColuna());
    }

    
    // M�todo ir� retornar o resultado da chamada do m�todo peca, se a posi��o da pe�a � ou n�o diferente de null.
    public boolean haUmaPeca(Posicao posicao) {
       
    	// Se o m�todo posi�aoExiste n�o retornar true.
        if (!posicaoExiste(posicao)) {
            throw new TabuleiroException("Posi��o n�o encontrada no tabuleiro.");
        }
 
        return peca(posicao) != null; // Se o retorno do m�todo for diferente de null retorna um true, sen�o retorna um false.
    }
}