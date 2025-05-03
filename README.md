# OOP: Computer Retail Management System

## Note

Our project uses Maven. Therefore, to compile the project, run this command in a terminal:

```ps
mvn compile
```

...and to run the program:

```ps
mvn exec:java -D"exec.mainClass"="mypackage.Main" -D"exec.classpathScope"=runtime -D"exec.fork"=true
```

Please note that the usual method to compile and run a Java program (`javac` and `java`) will ***not*** work, as `javac` does not include Maven dependencies (e.g. JLine) in the build process.