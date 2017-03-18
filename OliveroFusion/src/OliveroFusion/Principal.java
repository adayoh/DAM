/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OliveroFusion;

import java.io.BufferedInputStream;
import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException; 
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Scanner;




/**
 *
 * @author aday
 */
public class Principal {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
   
  //Declaramos variables.
  Scanner teclado = new Scanner(System.in);
  String nombreFichero1, nombreFichero2, nombreFichero3;
  String ruta = "/Users/aday/Dropbox/PROG/NetBeansProjects/OliveroFusion/";
  File fichero1, fichero2, fichero3;
  FileReader fr1 = null;
  FileReader fr2 = null;
  FileWriter fw = null;
  
  //Creamos un bucle do-while que nos va a pedir el nombre del primer fichero y va a comprobar si existe.
  //Si no existe lo volverá a pedir.
  do{
    System.out.println("Introduzca el nombre del primer fichero");
    nombreFichero1 = ruta + teclado.nextLine();
    fichero1 = new File(nombreFichero1);
      if(fichero1.exists()){
        System.out.println("El fichero existe");
      }else{
        System.out.println("El fichero no existe. Vuelva a probar.");}
  }while(!fichero1.exists());
   
  //Hacemos lo mismo para el segundo fichero.
  
  do{
    System.out.println("Introduzca el nombre del segundo fichero");
    nombreFichero2 = ruta + teclado.nextLine();
    fichero2 = new File(nombreFichero2);
      if(fichero2.exists()){
        System.out.println("El fichero existe");
      }else{ 
        System.out.println("El fichero no existe. Vuelva a probar.");}
  }while(!fichero2.exists());
   
  //Pedimos el nombre del fichero de salida, que no debe existir. Ponemos un bucle y si existe, volver a probar.
  
  do{
    System.out.println("Introduzca el nombre del fichero de salida");
    nombreFichero3 = ruta + teclado.nextLine();
    fichero3 = new File(nombreFichero3);
      if(!fichero3.exists()){
        System.out.println("El fichero no existe. Todo bien.");
      }else{ 
        System.out.println("El fichero existe. Vuelva a probar.");}
  }while(fichero3.exists());
   
  /*
  1.-Copiar el contenido del primer fichero de entrada en el fichero de salida utilizando la clase FileReader
  y FileWriter, realizando la copia carácter a carácter
  */
  
  
  try{  
    InputStream archEntrada = new FileInputStream(fichero1);
    OutputStream archSalida = new FileOutputStream(fichero3);
    byte[] buffer = new byte[356];
    while (true) {
      int n = archEntrada.read(buffer);
      if (n < 0) //Esto me lo resolvió un compañero en el foro...
        break;
      archSalida.write(buffer, 0, n);
    }
    archEntrada.close();
    archSalida.close();
    
  }catch (FileNotFoundException e1) {
    System.out.println("El fichero pasado no existe "+e1.getMessage());
    System.exit(-1);
  }catch (IOException e2){
    System.out.println("Error durante la lectura "+e2.getMessage());
  }
       
  /*
  2.-Añadir el contenido del segundo fichero utilizando para su lectura de la clase BufferedReader, 
  se realizará línea a línea.
  */

  try{
    
  BufferedReader bf1 = new BufferedReader(new FileReader(fichero2));
  BufferedWriter bw = new BufferedWriter(new FileWriter(fichero3, true));
    
  String linea1 = "";
    
    while ((linea1 != null)){
    linea1 = bf1.readLine();
    if (linea1 != null) bw.write(linea1 + "\n");
    }
    
    bf1.close();
    bw.close();
  }catch(FileNotFoundException e6){
    System.out.println("El fichero pasado no existe "+e6.getMessage());
    System.exit(-1);
  }catch (IOException e7) {
    System.out.println("Error durante la lectura "+e7.getMessage());
  }
  
  //Ahora pedimos al usuario una frase para añadir al fichero
  
  Scanner teclado1 = new Scanner(System.in);
  System.out.println("Escribe una frase para añadir al fichero");
  String frase = teclado1.nextLine();
  try{
    FileWriter nuevafrase = new FileWriter(fichero3, true);
    BufferedWriter out = new BufferedWriter(nuevafrase);
    out.write(frase);
    out.close();
  }catch(FileNotFoundException e8){
    System.out.println("El fichero no existe "+e8.getMessage());
  }catch(IOException e9){
    System.out.println("Error durante el proceso E/S");
  }
  
  //Una vez escrito el fichero lo leeremos y mostraremos por pantalla utilizando la clase BufferedInputStream.
  
  try{
  byte[] buffer1 = new byte[1024];
  BufferedInputStream bufferedInput = new BufferedInputStream(new FileInputStream(fichero3));

    int leidos= 0;
    while ((leidos= bufferedInput.read(buffer1)) != -1) {
      String parte= new String(buffer1, 0, leidos);
      System.out.print(parte);
    }
    bufferedInput.close();
    System.out.println("El texto del fichero de salida es: ");
    System.out.println(buffer1);
  }catch(FileNotFoundException e9){
    System.out.println("El fichero no existe "+e9.getMessage());
  }catch(IOException e10){
    System.out.println("Error durante el proceso E/S");
  }
   
   
}

  
}
  

