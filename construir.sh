#!/bin/bash

projeto=SisPro
pacoteDoProjeto=sisPro
pacoteGeral=br.dominioL

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
binariosJson=${binarios}/json

bibliotecasCss=${bibliotecas}/css
bibliotecasJava=${bibliotecas}/jar
bibliotecasJs=${bibliotecas}/js

fontesCss=${fontes}/css
fontesHtml=${fontes}/html
fontesJava=${fontes}/java
fontesJs=${fontes}/js
fontesJson=${fontes}/json

testesHtml=${testes}/html
testesJava=${testes}/java
testesJs=${testes}/js

contrucaoCompilacao=${construcao}/compilacao.txt

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
	mkdir -p ${binariosJson};
	mkdir -p ${bibliotecasCss};
	mkdir -p ${bibliotecasJava};
	mkdir -p ${bibliotecasJs};
	mkdir -p ${construcao};
	mkdir -p ${fontesCss};
	mkdir -p ${fontesHtml};
	mkdir -p ${fontesJava};
	mkdir -p ${fontesJs};
	mkdir -p ${fontesJson};
	mkdir -p ${testesHtml};
	mkdir -p ${testesJava};
	mkdir -p ${testesJs};
	mkdir -p ${recursos};
}

adicionarBibliotecas() {
	echo ":adicionarBibliotecas";
	cp -f ~/projetos/estilos/construcao/limpo.css ${bibliotecasCss};
	cp -f ~/projetos/verificaJs/construcao/verifica.css ${bibliotecasCss};
	cp -f ~/projetos/estruturados/construcao/estruturados.jar ${bibliotecasJava};
	cp -f ~/projetos/conexaoH/construcao/conexaoH.jar ${bibliotecasJava};
	cp -f ~/projetos/lindaJs/construcao/linda.js ${bibliotecasJs};
	cp -f ~/projetos/verificaJs/construcao/verifica.js ${bibliotecasJs};
	cp -f ~/projetos/verificaJs/construcao/jsHint.js ${bibliotecasJs};
}

compilar() {
	limpar;
	criarEstrutura;
	adicionarBibliotecas;
	echo ":compilar";
	cp -rf ${bibliotecasJs}/* ${fontesJs}/* ${binariosJs};
	cp -rf ${fontesHtml}/* ${testesHtml}/* ${binariosHtml};
	cp -rf ${bibliotecasCss}/* ${fontesCss}/* ${binariosCss};
	cp -rf ${fontesJson}/* ${binariosJson};
	cp -rf ${recursos}/* ${binarios};
	touch ${contrucaoCompilacao}
	javac -classpath ${bibliotecasJava}/*:${binariosJava} -sourcepath ${fontesJava} -d ${binariosJava} -Werror -deprecation -g ${arquivosFontesJava} -Xlint -Xmaxerrs 10 -Xmaxwarns 10 &> ${contrucaoCompilacao};
	javac -classpath ${bibliotecasJava}/*:${binariosJava} -sourcepath ${testesJava} -d ${binariosJava} -Werror -deprecation -g ${arquivosTestesJava} -Xlint -Xmaxerrs 10 -Xmaxwarns 10 &>> ${contrucaoCompilacao};
	less ${contrucaoCompilacao}
}

construir() {
	compilar;
	echo ":construir";
}

testar() {
	construir;
	echo ":testar";
	# java -classpath ${bibliotecasJava}/*:${binariosJava} org.junit.runner.JUnitCore ${classesTestesJava};
	chromium-browser ${binariosHtml}/testeDeCodigo.html --allow-file-access-from-files;
}

depurar() {
	construir;
	echo ":depurar";
	jdb -classpath ${bibliotecasJava}:${binariosJava} org.junit.runner.JUnitCore ${classesTestesJava};
}

executar() {
	construir;
	echo ":executar";
	# touch ${construcao}/execucao.txt;
	# java -classpath ${bibliotecasJava}/*:${binariosJava} ${pacoteGeral}.${pacoteDoProjeto}.${projeto} 2> ${construcao}/execucao.txt;
	java -classpath ${bibliotecasJava}/*:${binariosJava} ${pacoteGeral}.${pacoteDoProjeto}.${projeto};
}

echo :${pacoteDoProjeto}
if [ -n "$1" ]
then
	$1;
else
	construir;
fi
