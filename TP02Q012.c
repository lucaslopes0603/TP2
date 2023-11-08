#include <stdio.h>
#include <string.h>
void trocarStrings(char *a, char *b) {
    char temp[50]; 
    strcpy(temp, a);
    strcpy(a, b);
    strcpy(b, temp);
}
int compareStrings(const char *str1, const char *str2) {
    while (*str1 && *str2) {
        if (*str1 != *str2) {
            return *str1 - *str2;
        }
        str1++;
        str2++;
    }
    return 0;
}

void sortp(char str[][50],int k,int z) {
  
    int i, j;
 
for(int i=k;z>i;i++){
   int menor=i;
for(int j=i+1;z>=j;j++){
if(strcmp(str[menor],str[j])>0){
menor=j;
}
}
char temp[50];
strcpy(temp,str[i]);
strcpy(str[i],str[menor]);
strcpy(str[menor],temp);

  
}

  
}




int main() {

    char caminhoDoArquivo[] = "/tmp/players.csv";
    int linhas = 3922;

struct Jogador {
    int idu;
    char nome[50];
    int altura;
    int peso;
    char universidade[50];
    int anoNascimento;
    char cidadeNascimento[50];
    char estadoNascimento[15];
};
struct Jogador jogadores[linhas];
    FILE *arquivo = fopen(caminhoDoArquivo, "r");
    if (arquivo == NULL) {
        perror("Erro ao abrir o arquivo");
        return 1;
    }

  
    int k = 0;


  
char linha1[400];
   fgets(linha1, 400, arquivo);

    for (int i = 0; i < linhas; i++) {

        char texto[400];
        fgets(texto, 400, arquivo);


        int j = 0;
        int contador = 0;

        while (contador < 8) {

          int a=0;
            char *palavra;

          palavra=(char*)malloc(100*sizeof(char));
            int tamanho = strlen(texto) - 2;

            if (contador == 7) {

                if (tamanho >= 0 && texto[tamanho] == ',') {

                   strcpy(palavra,"nao informado");

                } else {
                    while (texto[tamanho] != ',') {
                        palavra[a] = texto[j];

                        tamanho--;
                        j++;
                      a++;
                     palavra[a] = '\0';
                    }
                }
            } else {
                if (j < strlen(texto) && texto[j] == ',') {


                    strcpy(palavra,"nao informado");

                } else {
                    while (j < strlen(texto) && texto[j] != ',') {


                        palavra[a] = texto[j];


                        j++;
                      a++;

                    }
                    palavra[a] = '\0';
                }
            }


if (contador == 0) {
  jogadores[i].idu=atoi(palavra);

        }
        if (contador == 1) {
    strcpy(jogadores[i].nome,palavra);


        }
        if (contador == 2) {
            jogadores[i].altura=atoi(palavra);
        }
        if (contador == 3) {
            jogadores[i].peso=atoi(palavra);
        }
        if (contador == 4) {
            strcpy(jogadores[i].universidade, palavra);
        }
        if (contador == 5) {
            jogadores[i].anoNascimento=atoi(palavra);
        }
        if (contador == 6) {
            strcpy(jogadores[i].cidadeNascimento, palavra);
        }
        if (contador == 7) {
            strcpy(jogadores[i].estadoNascimento, palavra);
        }


          free(palavra);

            j++;
            k++;
            contador++;
        }
    }

    int h = 0;
    char id[6];
  int id1[500];


    id[0] = '\0';


    while (h > -1) {
     scanf("%s",&id);

       int num = atoi(id);


        if (strcmp(id, "FIM") == 0) {
            break;
        } else {

          id1[h]=atoi(id);

           h++;
        }

    }




    char nomesv[h][50];
int anonascimento[h];
    for(int i=0;h>i;i++){

            int temp=id1[i];

strcpy(nomesv[i],jogadores[temp].nome);
anonascimento[i]=jogadores[temp].anoNascimento;

    }



  for (int i = 0; i < h - 1; i++) {
    int temp1;
char tempc[50];
      for (int j = 0; j < h - i - 1; j++) {
        
          if (anonascimento[j] > anonascimento[j + 1]) {
             
              temp1 = anonascimento[j];
              anonascimento[j] = anonascimento[j + 1];
              anonascimento[j + 1] = temp1;
            strcpy(tempc, nomesv[j]);
            strcpy(nomesv[j], nomesv[j+1]);
            strcpy(nomesv[j+1], tempc);

            
            
          }
      }
  }
  for(int i=0;h>i;i++){
int z=i;
int k=i;
  while(anonascimento[i]==anonascimento[i+1]){

  i++;
   z=i;
  }
if(k!=z){
sortp(nomesv,k,z);
k=z;
}
    
  }

  
    

  



  
 

               
            
                
              
            
  


  for(int i=0;h>i;i++){





    for(int j=0;linhas>j;j++){



if(strcmp(nomesv[i],jogadores[j].nome)==0){
             printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
     jogadores[j].idu, jogadores[j].nome, jogadores[j].altura,
     jogadores[j].peso, jogadores[j].anoNascimento, jogadores[j].universidade,
     jogadores[j].cidadeNascimento, jogadores[j].estadoNascimento);

}

    }



  }






        fclose(arquivo);
    return 0;

    
            }