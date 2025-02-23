package entidades;
import java.io.BufferedWriter;
import java.io.IOException;

class ListaEncadeada {
	
    private class Nodo {
        int linha;
        Nodo proximo;

        Nodo(int linha) {
            this.linha = linha;
            this.proximo = null;
        }
    }

    private Nodo primeiro;

    public ListaEncadeada() {
        this.primeiro = null;
    }

    public void adicionar(int linha) {
        Nodo novoNodo = new Nodo(linha);
        if (primeiro == null) {//se o primeiro eh nulo adiciona um novo nodo
            primeiro = novoNodo;
        } else {//caso se o primeiro nao eh nulo
            Nodo atual = primeiro;//aki uma variavel temporaria atual recebe oque tem no primeiro
            while (atual.proximo != null) {//enquanto o proximo do atual for diferente de nulo o atual recebe o proximo do atual
                atual = atual.proximo;//usado para pecorrer ate achar um nodo vazio
            }
            atual.proximo = novoNodo;//quando o atual.prox for nulo ele recebe o novo nodo
        }
    }

    public void escrever(BufferedWriter writer) throws IOException {//esse metodo escreve as linhas armazenadas na lista encadeada
        Nodo atual = primeiro;
        while (atual != null) {
            writer.write(atual.linha + " ");
            atual = atual.proximo;
        }
        writer.write("\n");
    }
}