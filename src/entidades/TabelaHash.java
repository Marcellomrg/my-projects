package entidades;

public class TabelaHash {
	
    private class Elemento {
        String chave;//chave que indica o indice para onde vai o elemento;
        ListaEncadeada linhas;//linha que esta o elemento;

        Elemento(String chave, int linha) {
            this.chave = chave;
            this.linhas = new ListaEncadeada();
            this.linhas.adicionar(linha);
        }
    }

    private Elemento[] tabela;
    private int capacidade;


    public TabelaHash(int capacidade) { 
        this.capacidade = capacidade;
        this.tabela = new Elemento[capacidade];
    }

    private int hash(String chave) { //metodo para gerar a chave onde aponta para qual indice o elemento vai;
        int hash = 1;
        for (int i = 0; i < chave.length(); i++) {
            hash = (31 * hash + chave.charAt(i)) % capacidade;
        }
        return hash;
    }

    public void insere(String chave) {
        int indice = hash(chave);//indice temporario recebe a chave do elemento;
        while (tabela[indice] != null && !tabela[indice].chave.equals(chave)) {//tratamento de colisao
            indice = (indice + 1) % capacidade;
        }
        if (tabela[indice] == null) {//se for nulo adiciona um novo elemento
            tabela[indice] = new Elemento(chave, -1);
        }
    }

    public void adicionarLinha(String chave, int linha) {
        int indice = hash(chave);
        while (tabela[indice] != null && !tabela[indice].chave.equals(chave)) {
            indice = (indice + 1) % capacidade;//incrementa o indice para manter o elemento dentro dos limites da tabela
        }
        if (tabela[indice] != null) {//quando o elemento for achado ele vai ser diferente de nulo 
            tabela[indice].linhas.adicionar(linha);//assim adiciona a linha relacionada a ele
        }
    }

    public boolean contem(String chave) {//metodo para ver se existe um elemento dentro da tabela hash
        return busca(chave) != null;
    }

    private Elemento busca(String chave) {//metodo de busca ,ele retorna o elemento caso encontre 
        int indice = hash(chave);
        while (tabela[indice] != null && !tabela[indice].chave.equals(chave)) {
            indice = (indice + 1) % capacidade;
        }
        return tabela[indice];
    }
}