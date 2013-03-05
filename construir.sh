fontes=fontes
testes=testes
bibliotecas=bibliotecas
recursos=recursos
binarios=binarios
construcao=construcao

fontesJava=${fontes}/java
fontesJs=${fontes}/js
fontesHtml=${fontes}/html
fontesCss=${fontes}/css
testesJava=${testes}/java
bibliotecasJava=${bibliotecas}/jar
binariosJava=${binarios}/class

arquivosFontesJava=$(find ${fontesJava} -name *.java)
arquivosTestesJava=$(find ${testesJava} -name *.java)
classesTestesJava=$(echo ${arquivosTestesJava} | sed -e s:${testesJava}:: -e s:/:.:g -e s:[.]java$:: -e s:^[.]:: -e s:[a-Z]*[.]figuracao[.][a-Z]*::)

limpar() {
	echo ":limpar";
	rm -rf ${binarios};
	rm -rf ${construcao};
}

criarEstrutura() {
	echo ":criarEstrutura";
	mkdir -p ${fontesJava};
	mkdir -p ${testesJava};
	mkdir -p ${bibliotecasJava};
	mkdir -p ${binariosJava};
	mkdir -p ${construcao};
}

adicionarBibliotecas() {
	echo ":adicionarBibliotecas";
	ln -sf ~/projetos/estruturados -t ${bibliotecasJava}
}

construir() {
	limpar;
	criarEstrutura;
	adicionarBibliotecas;
	echo ":construir";
}

compilar() {
	construir;
	javac -classpath ${bibliotecasJava}:${binariosJava} -sourcepath ${fontesJava} -d ${binariosJava} -Werror -deprecation -verbose -g ${arquivosFontesJava}
 	javac -classpath ${bibliotecasJava}:${binariosJava} -sourcepath ${testesJava} -d ${binariosJava} -Werror -deprecation -verbose -g ${arquivosTestesJava}
	echo ":compilar";
}

testar() {
	compilar;
	java -classpath ${bibliotecasJava}:${binariosJava} org.junit.runner.JUnitCore ${classesTestesJava};
	echo ":testar";
}

depurar() {
	compilar;
	jdb -classpath ${bibliotecasJava}:${binariosJava} org.junit.runner.JUnitCore ${classesTestesJava};
	echo ":depurar";
}

if [ -n "$1" ]
then
	$1;
else
	construir;
fi
