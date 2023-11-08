import java.io.*;
import java.util.*;

public class TP02Q07 {

    private static void writeMatricula(long totalTime, int comparisons) {
        String OUTPUT_FILE_NAME = "matrícula_insercao.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME))) {
            writer.write("Matrícula: 1398113\tNumero de comparacoes: " + comparisons
                    + "\tTempo de execucao: " + totalTime + "ms");
        } catch (Exception e) {
            System.out.println("Erro saida.txt " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        long totalTime = 0;
        String path = "/tmp/players.csv";

        // Monta a lista dos jogadores
        List<Jogador> list = new ArrayList<Jogador>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine();
            line = br.readLine();

            while (line != null) {

                Jogador jogador = new Jogador();
                jogador.ler(line);
                list.add(jogador);

                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Entrada padrão
        ArrayList<Jogador> arr = new ArrayList<Jogador>();
        Scanner sc = new Scanner(System.in);
        String pesquisa = "";
        do {
            pesquisa = sc.nextLine();

            if (!pesquisa.equals("FIM")) {
                try {
                    int idzada = Integer.parseInt(pesquisa);

                    if (idzada >= 0 && idzada < list.size()) {
                        Jogador referencia = list.get(idzada);
                        arr.add(referencia);
                    } else {
                        System.out.println("Índice fora do limite da lista.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Digite um número válido ou 'FIM' para sair.");
                }
            }

        } while (!pesquisa.equals("FIM"));

        sc.close();

        Insercao insercao = new Insercao(arr.size());
        for (int i = 0; i < arr.size(); i++) {
            insercao.array[i] = arr.get(i);
        }

        long startTime = System.currentTimeMillis();
        insercao.sort();
        long endTime = System.currentTimeMillis();

        long executionTime = endTime - startTime;
        totalTime += executionTime;

        for (int i = 0; i < insercao.array.length; i++) {
            insercao.array[i].imprimir();
        }

        writeMatricula(totalTime, Insercao.comparisons);
    }
}

class Insercao {
    Jogador[] array;
    int n = 0;
    static int comparisons = 0;

    public Insercao(int tamanho) {
        array = new Jogador[tamanho];
        n = array.length;
    }

    public void sort() {
        for (int i = 1; i < n; i++) {
            Jogador tmp = array[i];
            int j = i - 1;

            while ((j >= 0) && (array[j].compareTo(tmp) > 0)) {
                array[j + 1] = array[j];
                j--;
                comparisons++;
            }
            array[j + 1] = tmp;
        }
    }
}



class Jogador implements Comparable<Jogador> {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    // Construtores
    public Jogador() {
    }

    public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento, String estadoNascimento) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    //Setters
    public void setId (int id) {this.id = id;}
    public void setNome (String nome) {this.nome = nome;}
    public void setAltura (int altura) {this.altura = altura;}
    public void setPeso (int peso) {this.peso = peso;}
    public void setUniversidade (String universidade) {this.universidade = universidade;}
    public void setAnoNascimento (int anoNascimento) {this.anoNascimento = anoNascimento;}
    public void setCidadeNascimento (String cidadeNascimento) {this.cidadeNascimento = cidadeNascimento;}
    public void setEstadoNascimento (String estadoNascimento) {this.estadoNascimento = estadoNascimento;};

    //Getters
    public int getId() {return id;}
    public String getNome() {return nome;}
    public int getAltura() {return altura;}
    public int getPeso() {return peso;}
    public String getUniversidade() {return universidade;}
    public int getAnoNascimento() {return anoNascimento;}
    public String getCidadeNascimento() {return cidadeNascimento;}
    public String getEstadoNascimento() {return estadoNascimento;}

    public int compareTo(Jogador other) {
        if (this.anoNascimento != other.anoNascimento) {
            return this.anoNascimento - other.anoNascimento;
        } else {
            return this.nome.compareTo(other.nome);
        }
    }

    // Método para imprimir os atributos do jogador
    public void imprimir() {
        System.out.println("[" + id + " ## " + nome + " ## " + altura + " ## " + peso + " ## " + anoNascimento + " ## " + universidade + " ## " + cidadeNascimento + " ## " + estadoNascimento + "]");
    }

    // Método para ler os atributos do jogador
    public void ler(String line) {
        String[] vect = line.split(",", -1);

        for (int i = 0; i < vect.length; i++) {
            if (vect[i].isEmpty()) {
                vect[i] = "nao informado";
            }
        }

        setId (Integer.parseInt(vect [0]));
        setNome (vect[1]);
        setAltura (Integer.parseInt(vect [2]));
        setPeso (Integer.parseInt(vect [3]));
        setUniversidade (vect[4]);
        setAnoNascimento (Integer.parseInt(vect[5]));
        setCidadeNascimento (vect[6]);
        setEstadoNascimento (vect[7]);

    }
    
    // Método para clonar um jogador
    public Jogador clone() {
        return new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
    }
    
}
