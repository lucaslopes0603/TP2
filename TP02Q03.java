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
public class TP02Q03 {

    public static boolean pesqSeq(List<String> vet, String x){
        boolean resp = false;
        int n = vet.size();
  
        for(int i = 0; i < n; i++){
           if(vet.get(i).equals(x)){
              resp = true;
              i = n;
           }
        }
        return resp;
    }


    public static void main(String[] args) {
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

        List<String> nomesJogadores = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String pesquisa = "";
        do {
            pesquisa = sc.nextLine();

            if (!pesquisa.equals("FIM")) {
                try {
                    int idzada = Integer.parseInt(pesquisa);

                    if (idzada >= 0 && idzada < list.size()) {
                        Jogador referencia = list.get(idzada);
                        nomesJogadores.add(referencia.getNome());
                    } else {
                        System.out.println("Índice fora do limite da lista.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Digite um número válido ou 'FIM' para sair.");
                }
            }

        } while (!pesquisa.equals("FIM"));

        long startTime = System.currentTimeMillis();
        int comparacoes = 0;

        do {
            pesquisa = sc.nextLine();
            Boolean achou = false;
            achou = pesqSeq (nomesJogadores, pesquisa);
            if (achou == true){
                System.out.println("SIM");
            }else{
                System.out.println("NAO");
            }

            comparacoes++;

        } while (!(pesquisa.equals("FIM")));

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        sc.close();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("matricula_sequencial.txt", true));

            // Adicionar informações ao arquivo de log
            String matricula = "1398113"; // Substitua com sua matrícula real
            String tempoExecucao = String.valueOf(executionTime);
            String numeroComparacoes = String.valueOf(comparacoes);

            String logData = matricula + "\t" + tempoExecucao + "\t" + numeroComparacoes;

            writer.write(logData);
            writer.newLine(); // Adicionar uma nova linha para a próxima execução

            // Fechar o arquivo de log
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao acessar o arquivo de log: " + e.getMessage());
        }

    }
}

