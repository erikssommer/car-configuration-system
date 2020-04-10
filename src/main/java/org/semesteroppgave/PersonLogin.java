package org.semesteroppgave;

import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

public class PersonLogin {

    public static boolean verifyLogin(String username, String password, String usernameAndPasswordfile){

        var filepath = Paths.get("src/main/java/org/semesteroppgave/loginFiles", usernameAndPasswordfile);

        try{
            Scanner s = new Scanner(new File(String.valueOf(filepath)));

            s.useDelimiter("[,\n]");
            while (s.hasNext()){
                String checkUsername = s.next();
                String checkPassword = s.next();


                if(checkUsername.trim().equals(username.trim()) && checkPassword.trim().equals(password.trim())){
                    System.out.println("\nSuccessful match with username "+username.trim()+" in file "+usernameAndPasswordfile);
                    return true;
                }
            }
        }catch (Exception e){
            System.out.print("No match with username "+username.trim()+" in file "+usernameAndPasswordfile);
            System.out.print("Error :"+e.getMessage());
        }
        return false;
    }

    public static boolean checkIfExisting(String username, String usernameAndPasswordfile){
        boolean existing = false;

        var filepath = Paths.get("src/main/java/org/semesteroppgave/loginFiles", usernameAndPasswordfile);


        try{
            Scanner s = new Scanner(new File(String.valueOf(filepath)));

            s.useDelimiter("[,\n]");
            while (s.hasNext() && !existing){
                String checkUsername = s.next();
                String checkPassword = s.next();


                if(checkUsername.trim().equals(username.trim())){
                    existing = true;
                    System.out.println("\nUsername "+username.trim()+" already exists in file "+usernameAndPasswordfile);
                }
            }
        }catch (Exception e){
            System.out.print("No match with username "+username.trim()+" in file "+usernameAndPasswordfile);
            System.out.print("Error :"+e.getMessage());
        }
        return existing;
    }
}
