# appcrudmvcgc
Aplicação de Teste de CRUD de usuários e upload de imagens no Google Cloud. Esta versão precisa de um container Java para rodar. Com isso, configure o Tomcat para rodar a aplicação com um .war. 

Recomendações para configuração do Bucket e banco Mysql do Google Cloud. 

1. Criar um novo projeto para construir a aplicação usando o Google API Engine
Nome do projeto
my-app-google-api-engine

2. Setar o projeto via 
$ gcloud config set project my-spring-mvc

3. Garantir que os componentes api-java estejam instalados.
$ gcloud components install app-engine-java

4. Instalar o plugin do google cloud sdk no Eclipse.

5. Abrir o projeto java criado e converter para Api Engine Padrão. 

6. Criar a instancia do banco
a) Dados
my-app-sql
skyinfo
us-central1

b) Criar o banco da aplicação 
Entre na instância do banco e crie o banco da aplicação. 

7. Vá até a classe de configuração de acesso ao banco e atualize o IP da nova instância criada.

8. Vá até a lista de buckets do projeto e escolha um bucket já criado no projeto. 
obs: faça o ajuste para permitir escrita e leitura no bucket pela aplicação. 