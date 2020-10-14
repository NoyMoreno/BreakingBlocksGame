# 315429969
# shrikin4

compile: bin
	javac -cp biuoop-1.4.jar:src -d bin src/*/*.java

run:
	java -cp biuoop-1.4.jar:bin Ass6Game

check:
	java -jar checkstyle-5.7-all.jar -c biuoop.xml src/*.java

jar:
	jar cfm Ass6Game.jar Manifest.mf -C bin/ .
bin:
	mkdir bin