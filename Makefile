
FLAGS = -cp "$(PWD)"

.PHONY: all clean

all: Main

%.class: Server/%.java
	javac $(FLAGS) $?

Main: Server/Main.java
	javac $(FLAGS) $?

clean:
	rm ./Server/*.class