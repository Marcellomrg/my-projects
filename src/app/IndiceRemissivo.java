package app;
import entidades.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IndiceRemissivo {
    public static void main(String[] args) {
        ArvoreBinariaBusca abb = new ArvoreBinariaBusca();
        TabelaHash hashTable = new TabelaHash(101);

        //le as palavras-chave do arquivo
        try (BufferedReader reader = new BufferedReader(new FileReader("palavras_chave.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {//enquanto  a variavel linha receber os caracteres dentro do arquivo txt e nao for nulo
                String[] palavras = linha.split(", "); // separa as palavras por vírgulas e espaços
                for (String palavra : palavras) {//a variavel palavra vai receber cada palavra armazenada no array palavras
                    abb.insere(palavra); // Insere a palavra na ABB
                    hashTable.insere(palavra); // Insere a palavra na tabela hash
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        // le o arquivo de texto e insere as palavras com suas linhas
        try (BufferedReader reader = new BufferedReader(new FileReader("texto.txt"))) {
            String linha;
            int numeroLinha = 1;
            while ((linha = reader.readLine()) != null) {
            	String[] palavras = linha.split("[^\\w-]+"); // separa as palavras por caracteres não alfanuméricos
                for (String palavra : palavras) {
                    if (hashTable.contem(palavra)) {//verifica com o metodo contem do hash se a palavra armazenda na variavel palavra eh igual a armazenda na tabela hash
                        abb.adicionarLinha(palavra, numeroLinha); // Se for Adiciona a linha na ABB
                        hashTable.adicionarLinha(palavra, numeroLinha); // se for Adiciona a linha na tabela hash
                    }
                }
                numeroLinha++;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Escreve a saída no arquivo "saida.txt"
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("saida_.txt"))) {
            abb.emOrdem(writer); // Imprime a ABB em ordem no arquivo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
