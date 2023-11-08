import java.io.*;
import java.util.*;
class Jogador {

  private int id;
  private String nome;
  private int altura;
  private int peso;
  private String universidade;
  private int anoNascimento;
  private String cidadeNascimento;
  private String estadoNascimento;

  public Jogador() {
    this.id = -1;
    this.nome = "";
    this.altura = -1;
    this.peso = -1;
    this.universidade = "";
    this.anoNascimento = -1;
    this.cidadeNascimento = "";
    this.estadoNascimento = "";
  }

  public Jogador(
    int id,
    String nome,
    int altura,
    int peso,
    String universidade,
    int anoNascimento,
    String cidadeNascimento,
    String estadoNascimento
  ) {
    this.id = id;
    this.nome = nome;
    this.altura = altura;
    this.peso = peso;
    this.universidade = universidade;
    this.anoNascimento = anoNascimento;
    this.cidadeNascimento = cidadeNascimento;
    this.estadoNascimento = estadoNascimento;
  }

  public int getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public int getAltura() {
    return altura;
  }

  public int getPeso() {
    return peso;
  }

  public String getUniversidade() {
    return universidade;
  }

  public int getAnoNascimento() {
    return anoNascimento;
  }

  public String getCidadeNascimento() {
    return cidadeNascimento;
  }

  public String getEstadoNascimento() {
    return estadoNascimento;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setId(String id) {
    int convertedId = id.equals("nao informado") ? -1 : Integer.parseInt(id);
    setId(convertedId);
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setAltura(int altura) {
    this.altura = altura;
  }

  public void setAltura(String altura) {
    int convertedAltura = altura.equals("nao informado") ? 0 : Integer.parseInt(altura);
    setAltura(convertedAltura);
  }

  public void setPeso(int peso) {
    this.peso = peso;
  }

  public void setPeso(String peso) {
    int convertedPeso = peso.equals("nao informado") ? 0 : Integer.parseInt(peso);
    setPeso(convertedPeso);
  }

  public void setUniversidade(String universidade) {
    this.universidade = universidade;
  }

  public void setAnoNascimento(int anoNascimento) {
    this.anoNascimento = anoNascimento;
  }

  public void setAnoNascimento(String anoNascimento) {
    int convertedAnoNascimento = anoNascimento.equals("nao informado") ? 0 : Integer.parseInt(anoNascimento);
    setAnoNascimento(convertedAnoNascimento);
  }

  public void setCidadeNascimento(String cidadeNascimento) {
    this.cidadeNascimento = cidadeNascimento;
  }

  public void setEstadoNascimento(String estadoNascimento) {
    this.estadoNascimento = estadoNascimento;
  }
 public Jogador clone() throws CloneNotSupportedException {
    Jogador clone = new Jogador();
    clone.nome = this.nome;
    clone.altura = this.altura;
    clone.peso = this.peso;
    clone.universidade = this.universidade;
    clone.anoNascimento =this.anoNascimento;
    clone.cidadeNascimento =this.cidadeNascimento;
    clone.estadoNascimento =this.estadoNascimento;
    return clone;}
    
    public void imprimir() {
        System.out.println("[" + id + " ## " + nome + " ## " + altura + " ## " + peso + " ## " + anoNascimento + " ## " + universidade + " ## " + cidadeNascimento + " ## " + estadoNascimento + "]");
    }

    
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
}
public class TP02Q013 {

    private static void writeMatricula(long totalTime, int comparisons) {
        String OUTPUT_FILE_NAME = "matrícula_mergesort.txt";
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

        Mergesort universidadeSort = new Mergesort(arr.size());
        for (int i = 0; i < arr.size(); i++) {
            universidadeSort.array[i] = arr.get(i);
        }

        long startTime = System.currentTimeMillis();
        Jogador[] jogadoresOrdenados = universidadeSort.sort();
        long endTime = System.currentTimeMillis();

        long executionTime = endTime - startTime;
        totalTime += executionTime;

        for (int i = 0; i < jogadoresOrdenados.length; i++) {
            Jogador jogadorOrdenado = jogadoresOrdenados[i];
            jogadorOrdenado.imprimir();
        }

        writeMatricula(totalTime, Mergesort.comparisons);

    }
}

class Mergesort {
    Jogador[] array;
    int n = 0;
    static int comparisons = 0;

    public Mergesort() {
        array = new Jogador[100];
        int n = array.length;
    }

    public Mergesort(int tamanho) {
        array = new Jogador[tamanho];
        n = array.length;
    }

    public Jogador[] sort() {
        mergesort(0, n - 1);
        return array;
    }

    private void mergesort(int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergesort(esq, meio);
            mergesort(meio + 1, dir);
            intercalar(esq, meio, dir);
        }
    }

    public void intercalar(int esq, int meio, int dir) {
        int n1, n2, i, j, k;

        n1 = meio - esq + 1;
        n2 = dir - meio;

        Jogador[] a1 = new Jogador[n1 + 1];
        Jogador[] a2 = new Jogador[n2 + 1];

        for (i = 0; i < n1; i++) {
            a1[i] = array[esq + i];
        }

        for (j = 0; j < n2; j++) {
            a2[j] = array[meio + j + 1];
        }

        a1[i] = a2[j] = new Jogador(); // Sentinela

        for (i = j = 0, k = esq; k <= dir; k++) {
            if (a1[i] != null && a2[j] != null) {
                comparisons++;
                if (a1[i].getUniversidade() != null && a2[j].getUniversidade() != null) {
                    int compUniversidade = a1[i].getUniversidade().compareTo(a2[j].getUniversidade());
                    if (compUniversidade < 0 || (compUniversidade == 0 && a1[i].getNome().compareTo(a2[j].getNome()) <= 0)) {
                        array[k] = a1[i++];
                    } else {
                        array[k] = a2[j++];
                    }
                } else if (a1[i].getUniversidade() != null) {
                    array[k] = a1[i++];
                } else {
                    array[k] = a2[j++];
                }
            } else if (a1[i] != null) {
                array[k] = a1[i++];
            } else {
                array[k] = a2[j++];
            }
        }
    }


}


