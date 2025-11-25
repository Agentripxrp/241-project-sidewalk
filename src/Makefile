CLASSPATH = .
TESTCLASSPATH = $(CLASSPATH):Test/:libs/junit4.jar
JFLAGS = -g -cp $(TESTCLASSPATH)
.SUFFIXES: .java .class
.java.class:
	javac $(JFLAGS) $*.java

TESTSRC = Test/TestRunner.java Test/ShortestPathsTest.java

SRC = \
	BasicParser.java \
	DBParser.java \
	Node.java \
	Graph.java \
	GraphParser.java \
	ShortestPaths.java \

default: classes

classes: $(SRC:.java=.class)

test-classes: $(TESTSRC:.java=.class)

clean:
	rm -f *.class Test/*.class

run: classes
	java -cp $(CLASSPATH) ShortestPaths

test: classes test-classes
	java -cp $(TESTCLASSPATH) TestRunner ShortestPathsTest
