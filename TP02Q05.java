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

    }}
public class TP02Q05 {

    private static void writeMatricula(long totalTime, int comparisons) {
        String OUTPUT_FILE_NAME = "matrícula_selecao.txt";
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
		
        //Monta a lista dos jogadores
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
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}


        //Entrada padrão
        ArrayList<String> arr = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        String pesquisa = "";
        do {
            pesquisa = sc.nextLine();

            if (!pesquisa.equals("FIM")) {
                try {
                    int idzada = Integer.parseInt(pesquisa);

                    if (idzada >= 0 && idzada < list.size()) {
                        Jogador referencia = list.get(idzada);
                        arr.add(referencia.getNome());
                    } else {
                        System.out.println("Índice fora do limite da lista.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Digite um número válido ou 'FIM' para sair.");
                }
            }

        } while (!pesquisa.equals("FIM"));

        sc.close();

        Selecao nome = new Selecao(arr.size());
        for (int i = 0; i < arr.size(); i++){
            nome.array[i] = arr.get(i);
        }

        long startTime = System.currentTimeMillis();
        String[] nomesOrdenados = nome.sort();
        long endTime = System.currentTimeMillis();

        long executionTime = endTime - startTime;
        totalTime += executionTime;

        for (int i = 0; i < nomesOrdenados.length; i++) {
            String nomeOrdenado = nomesOrdenados[i];
            for (int j = 0; j < list.size(); j++) {
                Jogador jogador = list.get(j);
                if (nomeOrdenado.equals(jogador.getNome())) {
                    jogador.imprimir();
                    break;
                }
            }
        }
        
        writeMatricula(totalTime, Selecao.comparisons);
        
    }




}

class Selecao{
    String[] array;
    int n = 0;
    static int comparisons = 0;

    public Selecao(){
        array = new String[100];
        int n = array.length;
    }

    public Selecao (int tamanho){
        array = new String[tamanho];
        n = array.length;
    }

   /**
    * Algoritmo de ordenacao por selecao.
    */

    public String[] sort() {
        int n = array.length;
        for (int i = 0; i < (n - 1); i++) {
            int menor = i;
            for (int j = (i + 1); j < n; j++) {
                comparisons++;
                if (array[j].compareTo(array[menor]) < 0) {
                    menor = j;
                }
            }
            swap(menor, i);
        }
        return array;
    }


      /**
    * Troca o conteudo de duas posicoes do array
    * @param i int primeira posicao
    * @param j int segunda posicao
    */
   public void swap(int i, int j) {
      String temp = array[i];
      array[i] = array[j];
      array[j] = temp;
   }
}



