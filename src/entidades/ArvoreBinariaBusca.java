package entidades;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ArvoreBinariaBusca {
	
    class Nodo {
        public String elemento;
        public Nodo esquerdo;
        public Nodo direito;
        public ListaEncadeada linhas;

        public Nodo(String elemento) {
            this.elemento = elemento;
            this.esquerdo = null;
            this.direito = null;
            this.linhas = new ListaEncadeada();
        }
    }

    private Nodo raiz;
    private int nElementos;

    public ArvoreBinariaBusca() {
        this.raiz = null;
        this.nElementos = 0;
    }

    public void insere(String elemento) {
        raiz = insere(raiz, elemento);
    }

    private Nodo insere(Nodo nodo, String elemento) {
        if (nodo == null) {//se for nulo ele adiciona um novo valor 
            Nodo novoNodo = new Nodo(elemento);
            nElementos++;
            return novoNodo;
        }

        int cmp = elemento.compareTo(nodo.elemento);//compara o novo elemento com o atual,se for igual retorna 0,se for menor retorna -1 e se for maior retorna positivo
        if (cmp < 0) {
            nodo.esquerdo = insere(nodo.esquerdo, elemento);//adiciona no nodo esquerdo
        } else if (cmp > 0) {
            nodo.direito = insere(nodo.direito, elemento);//adiciona no nodo direito
        }
        return nodo;
    }

    public void adicionarLinha(String elemento, int linha) {
        Nodo nodo = buscaNodo(raiz, elemento);
        if (nodo != null) { //se for diferente de nulo ele quer dizer que o nodo existente foi encontrado
            nodo.linhas.adicionar(linha);//adiciona a linha na lista encadeada ao nodo
        }
    }

    private Nodo buscaNodo(Nodo nodo, String elemento) {//metodo de busca
        if (nodo == null) {//se for nulo retorna nulo
            return null;
        }

        int cmp = elemento.compareTo(nodo.elemento);//compara o novo nodo com o atual
        if (cmp < 0) {
            return buscaNodo(nodo.esquerdo, elemento);
        } else if (cmp > 0) {
            return buscaNodo(nodo.direito, elemento);
        } else {
            return nodo;
        }
    }

    public void emOrdem(BufferedWriter writer) throws IOException {//escreve a raiz 
        emOrdem(raiz, writer);
    }

    private void emOrdem(Nodo nodo, BufferedWriter writer) throws IOException {//metodo usado para escrever em ordem alfabetica
        if (nodo != null) {
            emOrdem(nodo.esquerdo, writer); 
            writer.write(nodo.elemento + " ");
            nodo.linhas.escrever(writer);
            emOrdem(nodo.direito, writer);
        }
    }

    public boolean busca(String elemento) {//metodo de busca
        return buscaNodo(raiz, elemento) != null;
    }
}