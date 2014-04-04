#!/bin/bash

projeto=SisPro
pacoteDoProjeto=sisPro
pacoteGeral=br.dominioL

class=class
css=css
html=html
jar=jar
java=java
js=js
json=json
sh=sh
txt=txt
construcao=construcao

contrucaoCompilacao=${construcao}/compilacao.txt

limpar() {
	echo ":limpar"
	rm -rf ${construcao}
}

criarEstrutura() {
	echo ":criarEstrutura"
	mkdir -p ${class}
	mkdir -p ${css}
	mkdir -p ${html}
	mkdir -p ${jar}
	mkdir -p ${java}
	mkdir -p ${js}
	mkdir -p ${json}
	mkdir -p ${sh}
	mkdir -p ${txt}
	mkdir -p ${construcao}
}

atualizarBibliotecas() {
	limpar
	criarEstrutura
	echo ":atualizarBibliotecas"
	cp -f ~/projetos/estilos/construcao/limpo.css ${css}
	cp -f ~/projetos/verificaJs/construcao/verifica.css ${css}
	cp -f ~/projetos/estruturados/construcao/estruturados.jar ${jar}
	cp -f ~/projetos/conexaoH/construcao/conexaoH.jar ${jar}
	cp -f ~/projetos/lindaJs/construcao/linda.js ${js}
	cp -f ~/projetos/verificaJs/construcao/verifica.js ${js}
	cp -f ~/projetos/verificaJs/construcao/jsHint.js ${js}
}

jarjar() {
	limpar
	criarEstrutura
	echo ":jarjar"
	# java -jar ${jar}/jarjar.jar find class ${jar}/groovy.jar ${jar}/asm4.jar
	# java -jar ${jar}/jarjar.jar find jar ${jar}/groovy.jar ${jar}/asm4.jar
	java -jar ${jar}/jarjar.jar process ${txt}/jarjar.txt ${jar}/dependencias/asm4.jar ${jar}/asm4.jar
	java -jar ${jar}/jarjar.jar process ${txt}/jarjar.txt ${jar}/dependencias/asm4Analysis.jar ${jar}/asm4Analysis.jar
	java -jar ${jar}/jarjar.jar process ${txt}/jarjar.txt ${jar}/dependencias/asm4Commons.jar ${jar}/asm4Commons.jar
	java -jar ${jar}/jarjar.jar process ${txt}/jarjar.txt ${jar}/dependencias/asm4Tree.jar ${jar}/asm4Tree.jar
	java -jar ${jar}/jarjar.jar process ${txt}/jarjar.txt ${jar}/dependencias/asm4Util.jar ${jar}/asm4Util.jar
	java -jar ${jar}/jarjar.jar process ${txt}/jarjar.txt ${jar}/dependencias/groovy.jar ${jar}/groovy.jar
	java -jar ${jar}/jarjar.jar process ${txt}/jarjar.txt ${jar}/dependencias/groovyJson.jar ${jar}/groovyJson.jar
	java -jar ${jar}/jarjar.jar process ${txt}/jarjar.txt ${jar}/dependencias/groovyXml.jar ${jar}/groovyXml.jar
}

compilar() {
	limpar
	criarEstrutura
	echo ":compilar"
	touch ${contrucaoCompilacao}
	arquivosJava=$(find ${java} -name *.java)
	javac -classpath ${jar}/*:${class} -sourcepath ${java} -d ${class} -Werror -deprecation -g ${arquivosJava} -Xlint -Xmaxerrs 10 -Xmaxwarns 10 &> ${contrucaoCompilacao}
	less ${contrucaoCompilacao}
}

construir() {
	compilar
	echo ":construir"
}

testar() {
	construir
	echo ":testar"
	arquivosTestesJava=$(find ${java} -name *Teste*.java)
	classesTestesJava=$(echo ${arquivosTestesJava} | sed -e s:${java}/::g -e s:^/::g -e "s:\s/: :g" -e s:/:.:g -e s:\.java::g -e s:[a-Z.]*figuracao[a-Z.]*::g)
	java -classpath ${jar}/*:${class} org.junit.runner.JUnitCore ${classesTestesJava}
	chromium-browser ${html}/testes/testeDeCodigo.html --allow-file-access-from-files
}

testarJava() {
	construir
	echo ":testarJava"
	arquivosTestesJava=$(find ${java} -name *Teste*.java)
	classesTestesJava=$(echo ${arquivosTestesJava} | sed -e s:${java}/::g -e s:^/::g -e "s:\s/: :g" -e s:/:.:g -e s:\.java::g -e s:[a-Z.]*figuracao[a-Z.]*::g)
	java -classpath ${class}:${jar}/* org.junit.runner.JUnitCore ${classesTestesJava}
}

testarWeb() {
	construir
	echo ":testarWeb"
	chromium-browser ${html}/testes/testeDeCodigo.html --allow-file-access-from-files
}

executar() {
	construir
	echo ":executar"
	java -classpath ${jar}/*:${class} ${pacoteGeral}.${pacoteDoProjeto}.${projeto}
}

echo :${pacoteDoProjeto}
if [ -n "$1" ]
then
	$1
else
	construir
fi
