#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

struct Jogador {
    int id;
    char nome[100];
    int altura;
    int peso;
    char universidade[100];
    int anoNascimento;
    char cidadeNascimento[100];
    char estadoNascimento[100];
};

typedef struct Jogador Jogador;

// Função para comparar jogadores por nome (necessária para a pesquisa binária)
int ComparaJogadoresPorNome(const void *a, const void *b) {
    return strcmp(((Jogador *)a)->nome, ((Jogador *)b)->nome);
}

int main() {
    clock_t inicio = clock();  // Registra o tempo de início
    int comparacoes = 0;  // Contador de comparações

    FILE *file = fopen("/tmp/players.csv", "r");
    if (file == NULL) {
        fprintf(stderr, "Erro ao abrir o arquivo.\n");
        return 1;
    }

    Jogador *jogadores = NULL;
    int numJogadores = 0;
    int capacidadeJogadores = 10;  // Capacidade inicial (pode ser ajustada conforme necessário)

    jogadores = (Jogador *)malloc(capacidadeJogadores * sizeof(Jogador));
    if (jogadores == NULL) {
        fprintf(stderr, "Erro ao alocar memória.\n");
        return 1;
    }

    char linha[400];
    fgets(linha, sizeof(linha), file); // Ignorar a primeira linha de cabeçalho

    while (fgets(linha, sizeof(linha), file) != NULL) {
        int idArq;
        char nome[100];
        int altura;
        int peso;
        char universidade[100];
        int anoNascimento;
        char cidadeNascimento[100];
        char estadoNascimento[100];

        sscanf(linha, "%d,%99[^,],%d,%d,%99[^,],%d,%99[^,],%99[^\n]", &idArq, nome, &altura, &peso, universidade, &anoNascimento, cidadeNascimento, estadoNascimento);

        if (numJogadores == capacidadeJogadores) {
            // Aumenta a capacidade do vetor de jogadores, se necessário
            capacidadeJogadores *= 2;
            jogadores = (Jogador *)realloc(jogadores, capacidadeJogadores * sizeof(Jogador));
            if (jogadores == NULL) {
                fprintf(stderr, "Erro ao realocar memória.\n");
                return 1;
            }
        }

        // Preenche a estrutura Jogador com os dados lidos
        jogadores[numJogadores].id = idArq;
        strcpy(jogadores[numJogadores].nome, nome);
        jogadores[numJogadores].altura = altura;
        jogadores[numJogadores].peso = peso;
        strcpy(jogadores[numJogadores].universidade, universidade);
        jogadores[numJogadores].anoNascimento = anoNascimento;
        strcpy(jogadores[numJogadores].cidadeNascimento, cidadeNascimento);
        strcpy(jogadores[numJogadores].estadoNascimento, estadoNascimento);

        numJogadores++; // Incrementa o contador de jogadores
    }

    // Ordenar o vetor de jogadores pelo nome (necessário para pesquisa binária)
    qsort(jogadores, numJogadores, sizeof(Jogador), ComparaJogadoresPorNome);

    // Leitura e pesquisa dos elementos
    char elemento[100];

    while (1) {
        scanf("%s", elemento);
        if (strncmp(elemento, "FIM", 3) == 0) {
            break;
        }

        comparacoes++;  // Incrementa o contador de comparações
        // Realiza a pesquisa binária
        Jogador jogadorPesquisado;
        strcpy(jogadorPesquisado.nome, elemento);

        Jogador *resultado = (Jogador *)bsearch(&jogadorPesquisado, jogadores, numJogadores, sizeof(Jogador), ComparaJogadoresPorNome);

        if (resultado != NULL) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }

    // Registra o tempo de término
    clock_t fim = clock();

    // Calcula o tempo de execução em segundos
    double tempoExecucao = (double)(fim - inicio) / CLOCKS_PER_SEC;

    // Cria o arquivo de log (substitua 'SuaMatricula' pela matrícula do estudante)
    FILE *logFile = fopen("matricula_binaria.txt", "w");
    if (logFile != NULL) {
        fprintf(logFile, "SuaMatricula\t%.6lf\t%d\n", tempoExecucao, comparacoes);
        fclose(logFile);
    } else {
        fprintf(stderr, "Erro ao criar o arquivo de log.\n");
    }

    // Libera a memória alocada
    free(jogadores);

    return 0;
}
