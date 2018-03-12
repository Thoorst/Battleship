/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shipbattle;

import java.io.BufferedReader;
import java.io.File;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class User {
    String login;
    String password;
    
    public User(){
        
    }
    
    public User(String login, String password){
         this.login = login;
         this.password = password;
    }
    
    public void CreateUser() throws IOException{
        if(UserExists(login)){
            System.out.println("Uzytkownik " + login + " istnieje!");
        } else {
                try(FileWriter plik = new FileWriter(login)) {
                plik.write(password);
            }
        }
    }
    
    public void LoginUser() throws IOException{
        if(UserExists(login)){
            try(BufferedReader file = new BufferedReader(new FileReader(login))) {
                String check = file.readLine();
                if(this.password.equals(check)==true){
                    System.out.println("Udane logowanie.");
                } else {
                    System.out.println("Bledny haslo!");
                }
            }
        } else {
            System.out.println("Bledny login lub haslo!");
        }
    }
    
    public void ShowUser(){
        System.out.println(login);
        System.out.println(password);
    }
    
    private static boolean UserExists(String login){
        File f = new File(login);
        return f.exists() && f.isFile();
    }

}
