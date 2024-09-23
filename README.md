# processoseletivo

Como configurar as imagens no docker para rodar o projeto local


* docker image pull mysql:8.0.39

* docker network create --driver bridge arquivos-network

* docker container run --name arquivos-mysql -d --rm -p 3306:3306 -e MYSQL_ROOT_PASSWORD=roOt@195251 --network arquivos-network mysql:8.0.39

* mvn clean package

* docker image build -t aplicacao-spring .

* docker container run --name arquivos-api -d --rm -p 8080:8080 -e DB_HOST=arquivos-mysql --network arquivos-network aplicacao-spring