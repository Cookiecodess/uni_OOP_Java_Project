/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;

/**
 *
 * @author songl
 */
public interface  Payable { //to provide unified operating specifications, so that they can have the operation themselves
            //and interface dont have include the specific operation
               //interface dont have specific operation, is let the class to defined it
    boolean process();
   // String getMethod(); // return "Card" or "TNG" or "OnlineBanking"
}
