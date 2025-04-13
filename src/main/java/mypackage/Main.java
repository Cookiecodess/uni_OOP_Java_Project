/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package mypackage;

// NOTE:
// To compile and run, first type this in your terminal
// to change your directory to the project folder
// (replace "<path>" with your path to OOP_Java_Project):
//   cd "C:\<path>\OOP_Java_Project"

// Since our project uses a dependency called JLine (for detecting arrow key presses), 
// we must use Maven to compile, as it automatically handles dependencies.
// You'll need to install Maven on your computer, then add it to your PATH.
//
// Then, to compile, type this in your terminal (cannot use the javac command, must use mvn):
//   mvn compile
//
// To run Main.java, type:
//   mvn exec:java -D"exec.mainClass"="mypackage.Main" -D"exec.classpathScope"=runtime -D"exec.fork"=true
//     (Note: I don't completely understand how or why this command works yet)
//     (I also don't understand why this doesn't work:
//          java -cp target/classes mypackage.JLineMenu
//      it throws this error:
//          Error: Unable to initialize main class mypackage.JLineMenu
//          Caused by: java.lang.NoClassDefFoundError: org/jline/terminal/Terminal
//     )

import java.util.Scanner;


/**
 *
 * @author User
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        
    }
    
    
    
    
}
