# processoseletivo

Como configurar as imagens no docker para rodar o projeto local


* docker image pull mysql:8.0.39

* docker network create --driver bridge arquivos-network

* docker container run --name arquivos-mysql -d --rm -p 3306:3306 -e MYSQL_ROOT_PASSWORD=roOt@195251 --network arquivos-network mysql:8.0.39

* mvn clean package

* docker image build -t aplicacao-spring .

* docker container run --name arquivos-api -d --rm -p 8080:8080 -e DB_HOST=arquivos-mysql --network arquivos-network aplicacao-spring

No diretorio test/resources/postman tem uma collection do postman.

Listar raiz:
-Lista todos os diretório, subdiretorios e seus arquivos, caso haja.
GET http://localhost:8080/api/diretorios/raiz

Adicionar diretorio:
-Para criar um diretorio deve ser passado o obj pai com id, ex:
POST http://localhost:8080/api/diretorios
Body:
{
    "nome": "Documentos",
    "diretorioPai": {
    "id": 1
    }
}

Alterar diretorio(no caso, nome):
-Passando o id do mesmo na uri
PUT http://localhost:8080/api/diretorios/1
Body:
{
    "nome": "Documentos alterado",
    "diretorioPai": {
    "id": 1
    }
}

Deletar diretorio:
-Passando o id do mesmo na uri
DELETE http://localhost:8080/api/diretorios/1

Criar arquivo:
-Para a criação de um arquivo em um determinado diretório, deve ser passar o obj do diretorio com id, ex:
POST http://localhost:8080/api/arquivos
Body:
{
    "nome": "lista de compras.txt",
    "diretorio": {
    "id": 1
    }
}

Alterar arquivo(no caso, nome):
-Passando o id do mesmo na uri
PUT http://localhost:8080/api/arquivos/1
Body:
{
    "nome": "lista de compras alterado.txt",
    "diretorio": {
    "id": 1
    }
}

Deletar arquivo:
-Passando o id do mesmo na uri
DELETE http://localhost:8080/api/arquivos/1