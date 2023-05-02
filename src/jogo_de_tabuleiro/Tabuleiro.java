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

    
     // Método peca do tipo Peca, que recebe como argumento linha e coluna e retorna a matriz pecas com os valores de linha e coluna.
    public Peca peca(int linha, int coluna) {
        // Se o método posiçaoExiste não retornar true.
        if (!posicaoExiste(linha, coluna)) {
            throw new TabuleiroException("Posicaoo nao encontrada no tabuleiro.");
        }
        return pecas[linha][coluna];
    }

    /*
     * Sobrecarga do método peca do tipo Peca, que recebe como argumento o objeto posicao do tipo Posicao
     * e retorna a matriz pecas com os valores de linha e coluna.
     */
    public Peca peca(Posicao posicao) {
        
    	// Se o método posiçaoExiste não retornar true.
        if (!posicaoExiste(posicao)) {
            throw new TabuleiroException("Posição não encontrada no tabuleiro.");
        }
        return pecas[posicao.getLinha()][posicao.getColuna()];
    }
    

    // Método que aloca uma peça no tabuleiro
    public void colocarPeca(Peca peca, Posicao posicao) {
        
    	// Se o método retornar true (o valor da posicao diferente de null), quer dizer que tem uma peça nessa posição.
        if (haUmaPeca(posicao)) {
            throw new TabuleiroException("Já existe uma peça na posição" + posicao);
        }
        
        // A matriz na posição[posicao.getLinha()][posicao.getColuna()] vai receber o valor de peca.
        pecas[posicao.getLinha()][posicao.getColuna()] = peca;
     
        // O atributo posicao da classe Peca recebe o valor de posicao, portanto o valor de peca não é mais null.
        peca.posicao = posicao;
    }
    
    
    // Método que remove uma peça.
    public Peca removerPeca(Posicao posicao) {
    	
    	// Programação defensiva. Testa se posição passada como argumento existe.
    	if (!posicaoExiste(posicao)) {
    		throw new TabuleiroException("Posicao nao encontrada no tabuleiro.");
    	}
    	
    	// Se a peça do tabuleiro nessa posição for null, retorne null.
    	if (peca(posicao) == null) {
    		return null;
    	}
    	Peca aux = peca(posicao);
    	aux.posicao = null;
    	pecas[posicao.getLinha()][posicao.getColuna()] = null;
    	return aux;
    }
    
    

    // Método auxiliar, que retorna se a posição existe ou não, retornando um True ou False.
    private boolean posicaoExiste(int linha, int coluna) {
        
    	// Se a condição abaixo for verdadeira retorna true senão retorna false.
        return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }

    
    // Método irá retonar a chamada do método auxiliar acima.
    public boolean posicaoExiste(Posicao posicao) {
        return posicaoExiste(posicao.getLinha(), posicao.getColuna());
    }

    
    // Método irá retornar o resultado da chamada do método peca, se a posição da peça é ou não diferente de null.
    public boolean haUmaPeca(Posicao posicao) {
       
    	// Se o método posiçaoExiste não retornar true.
        if (!posicaoExiste(posicao)) {
            throw new TabuleiroException("Posição não encontrada no tabuleiro.");
        }
 
        return peca(posicao) != null; // Se o retorno do método for diferente de null retorna um true, senão retorna um false.
    }
}