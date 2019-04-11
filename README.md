# appcrudmvcgc
Aplicação de Teste de CRUD de usuários e upload de imagens no Google Cloud. Esta versão precisa de um container Java para rodar. Com isso, configure o Tomcat para rodar a aplicação com um .war. 

Recomendações para configuração do Bucket e banco Mysql do Google Cloud. 

1. Criação do Bucket 
meu-bucket-files

2. Adicionar a dependência do cloud storage no nosso projeto. Para isso, vá até o pom.xml

<dependency>
    <groupId>com.google.cloud</groupId>
    <artifactId>google-cloud-storage</artifactId>
    <version>1.14.0</version>
</dependency>

3. Criar uma classe para salvar os arquivos no Bucket criado. 

4. Criar uma chave de acesso da aplicação para o projeto do Google Cloud. 

Devemos configurar a chave de acesso que será utilizada pela nossa aplicação para que possamos assim salvar as imagens nesse Bucket que criamos.

Obs: Nós precisamos de uma chave com as credenciais necessárias que será utilizada por nossa aplicação para enviar as imagens.

5. Acesso da API

Criar Google Service Key

permitir-acesso-servicos-google-cloud

Feito isso, devemos dar o nome para essa credencial que estamos configurando, podemos dar o nome que desejarmos, por exemplo permitir-acesso-servicos-google-cloud e na permissão Role escolha que tal conta terá a possibilidade de realizar edição no projeto, sendo assim possível tanto de realizar a leitura como a escrita de dados. Escolha a opção Editor para essa conta, para finalizar, faça o download da chave no formato JSON para que nossa aplicação que ainda está no ambiente local de desenvolvimento salve as imagens no Bucket.

A criação do objeto Storage na classe FileSaver vai procurar essas credencias de acesso presentes no arquivo JSON através da variável de ambiente GOOGLE_APPLICATION_CREDENTIALS, precisamos configurar na nossa máquina essa variável de ambiente:

Para configurar a variável de ambiente no MAC:  

touch ~/.bash_profile
open -a TextEdit.app ~/.bash_profile
export GOOGLE_APPLICATION_CREDENTIALS=[Local da chave]

Outra opção é passar o arquivo direto no código. Veja exemplo da appspring

6. Criar o banco de dados no Google Cloud
a) Criar o banco
b) Liberar o acesso de IP público. Para isso, clique em Show configuration options e na parte de redes autorizadas Authorize Networks coloque o endereço 0.0.0.0/0

7. Criar uma instancia de maquina virtual no Google Cloud

gcloud compute instances create my-server-app-spring-mvc --image-family=ubuntu-1604-lts --image-project=ubuntu-os-cloud --zone=us-east1-b

8. Gere um conjunto de arquivos de chave pública e privada para acessar o servidor. 

9. Coloque a chave pública no servidor criado

10. Acesse via ssh o servidor criado com sua chave privada

11. Instale o Tomcat

12. Libere as portas do Firewall

Redes -> VCP Networks

Gerar a chave privada via Openssl
openssl genrsa -des3 -out private.pem 2048
openssl rsa -in private-google-cloud.pem -outform PEM -pubout -out public-google-cloud.pem

Via comando do GCloud na sua máquina com o Google Cloud SDK: gcloud compute --project "my-spring-mvc" ssh --zone "southamerica-east1-a" "my-server-app-spring-mvc"

13. Acesse a instancia criada no passo 7 e clone o projeto
a) Altere no FileServer.java para apontar para o arquivo my-spring-mvc-3fc1f59f9751.json de acesso ao Bucket. Se não tiver esse arquivo vá até o bucket criado no passo 1 e salve esse arquivo em um local seguro da sua instancia criada no passo 7.

14. Compila o projeto e gera o .war
mvn compile war:war

15. Copia o .war para a pasta de webs do tomcat
sudo cp appcrudmvc.war /var/lib/tomcat8/webapps/

Acesse http://ip-da-instancia:8080/appcrudmvc