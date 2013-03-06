#!/bin/bash

projeto=SisPro
pacoteDoProjeto=sisPro

bibliotecas=bibliotecas
binarios=binarios
construcao=construcao
documentacao=documentacao
fontes=fontes
recursos=recursos
testes=testes

binariosCss=${binarios}/css
binariosHtml=${binarios}/html
binariosJava=${binarios}/class
binariosJs=${binarios}/js
bibliotecasCss=${bibliotecas}/css
bibliotecasJava=${bibliotecas}/jar
bibliotecasJs=${bibliotecas}/js
fontesCss=${fontes}/css
fontesHtml=${fontes}/html
fontesJava=${fontes}/java
fontesJs=${fontes}/js
testesJava=${testes}/java
testesJs=${testes}/js

arquivosFontesJava=$(find ${fontesJava} -name *.java)
arquivosTestesJava=$(find ${testesJava} -name *.java)
classesTestesJava=$(echo ${arquivosTestesJava} | sed -e s:${testesJava}::g -e s:^/::g -e "s:\s/: :g" -e s:/:.:g -e s:[.]java::g -e s:[a-Z.]*figuracao[a-Z.]*::g)

limpar() {
	echo ":limpar";
	rm -rf ${binarios};
	rm -rf ${construcao};
}

criarEstrutura() {
	echo ":criarEstrutura";
	mkdir -p ${binariosCss};
	mkdir -p ${binariosHtml};
	mkdir -p ${binariosJava};
	mkdir -p ${binariosJs};
	mkdir -p ${bibliotecasCss};
	mkdir -p ${bibliotecasJava};
	mkdir -p ${bibliotecasJs};
	mkdir -p ${construcao};
	mkdir -p ${fontesCss};
	mkdir -p ${fontesHtml};
	mkdir -p ${fontesJava};
	mkdir -p ${fontesJs};
	mkdir -p ${testesJava};
	mkdir -p ${testesJs};
}

adicionarBibliotecas() {
	echo ":adicionarBibliotecas";
	ln -sf ~/projetos/estilos/construcao/limpo.css -t ${bibliotecasCss};
	ln -sf ~/projetos/estruturados/construcao/estruturados.jar -t ${bibliotecasJava};
	ln -sf ~/projetos/conexaoH/construcao/conexaoH.jar -t ${bibliotecasJava};
	ln -sf ~/projetos/lindaJs/construcao/linda.js -t ${bibliotecasJs};
}

compilar() {
	limpar;
	criarEstrutura;
	adicionarBibliotecas;
	echo ":compilar";
	cp -rf ${bibliotecasJs}/* ${fontesJs}/* ${binariosJs};
	cp -rf ${fontesHtml}/* ${binariosHtml};
	cp -rf ${bibliotecasCss}/* ${fontesCss}/* ${binariosCss};
	javac -classpath ${bibliotecasJava}/*:${binariosJava} -sourcepath ${fontesJava} -d ${binariosJava} -Werror -deprecation -g ${arquivosFontesJava};
	javac -classpath ${bibliotecasJava}/*:${binariosJava} -sourcepath ${testesJava} -d ${binariosJava} -Werror -deprecation -g ${arquivosTestesJava};
}

construir() {
	compilar;
	echo ":construir";
}

testar() {
	construir;
	echo ":testar";
	java -classpath ${bibliotecasJava}/*:${binariosJava} org.junit.runner.JUnitCore ${classesTestesJava};
}

depurar() {
	construir;
	echo ":depurar";
	jdb -classpath ${bibliotecasJava}:${binariosJava} org.junit.runner.JUnitCore ${classesTestesJava};
}

executar() {
	construir;
	echo ":executar";
	java -classpath ${bibliotecasJava}/*:${binariosJava} br.dominioL.${pacoteDoProjeto}.${projeto};
}

echo :${pacoteDoProjeto}
if [ -n "$1" ]
then
	$1;
else
	construir;
fi
