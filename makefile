# 313327439 
# heilbri
compile: bin
	find src -name "*.java" > sources.txt
	javac -cp biuoop-1.4.jar:.src -d bin @sources.txt
run:
	java -cp biuoop-1.4.jar:bin:resources Ass7Game 
jar:
	jar cfm ass7game.jar manifest.mf -C bin . -C resources .
bin:
	mkdir bin

