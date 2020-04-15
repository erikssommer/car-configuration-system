package org.semesteroppgave.signin;

import org.semesteroppgave.exceptions.*;

import java.util.ArrayList;

public class PersonValidator {

    public static String testValidName(String name){
        //Støtter navn som består av én til fire ord.
        //De to første navnene kan ha bindestrek mellom navnene. eks: Markus-Johannes Pedersen
        String[] regex = {
                "[A-ZÆØÅ][a-zæøå]+",
                "[A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+",
                "[A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+",
                "[A-ZÆØÅ][a-zæøå]+[-][A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+",
                "[A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+",
                "[A-ZÆØÅ][a-zæøå]+[-][A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+"
        };

        for(String str : regex){
            if(name.matches(str)){
                return name;
            }
        }
        throw new InvalidNameException("Navnet du skrev inn er ikke gyldig\n");
    }

    public static String testValidPhone(String phonenumber) throws InvalidPhonenumberException {
        String[] regex = {
                "[0-9]{8}",
                "[+][4][7][0-9]{8}",
                "[0-9]{3}-[0-9]{4}",
                "[+][4][7] [0-9]{8}",
                "[0][0-9]{4} [0-9]{6}",
                "[0]{2} [4][7] [0-9]{8}",
                "[(][+][4][7][)][0-9]{8}",
                "[(][+][4][7][)] [0-9]{8}",
                "[+][4]{2} [0-9]{4} [0-9]{6}",
                "[(]541[)] [0-9]{3}-[0-9]{4}",
                "[1]-[0-9]{3}-[0-9]{3}-[0-9]{4}",
                "[+][1]-[0-9]{3}-[0-9]{3}-[0-9]{4}",
                "[0]{2}[1]-[0-9]{3}-[0-9]{3}-[0-9]{4}",
                "[0]{2} [4][7] [0-9]{3} [0-9]{2} [0-9]{3}",
                "[(][+][4][7][)] [0-9]{2} [0-9]{2} [0-9]{2} [0-9]{2}",
                //Amerikanske nummer
                "^[\\\\(]{0,1}([0-9]){3}[\\\\)]{0,1}[ ]?([^0-1]){1}([0-9]){2}[ ]?[-]?[ ]?([0-9]){4}[ ]*((x){0,1}([0-9]){1,5}){0,1}$"
        };

        for(String str : regex){
            if(phonenumber.matches(str)){
                return phonenumber;
            }
        }
        throw new InvalidPhonenumberException("Skriv inn gyldig telefonnummer\n");
    }

    public static String testValidEmail(String ePost) throws InvalidEmailException {
        String[] regex = {
                "[a-zæøåA-ZÆØÅ0-9.]+[@][a-z]+[.][a-z]+",
                "[a-zæøåA-ZÆØÅ0-9.]+[@][a-z]+[.][a-z]+[.][a-z]+"
        };

        for(String str : regex){
            if(ePost.matches(str)){
                return ePost;
            }
        }
        throw new InvalidEmailException("Skriv inn gyldig epost\n");
    }

    public static String testValidUsername(String username) throws InvalidPhonenumberException {
        String regex ="(?!^[0-9]*$)(?!^[a-zæøåA-ZÆØÅ]*$)^([a-zæøåA-ZÆØÅ0-9]{6,})$";

            if(username.matches(regex)){
                return username;
            }
        throw new InvalidUsernameException("Skriv inn gyldig brukernavn.\nMå være minst 6 tegn og inneholde\nminst en bokstav og et tall");
    }

    public static String testValidPassword(String password) throws InvalidPhonenumberException {
        String regex ="^(?=.*[A-ZÆØÅa-zæøå])(?=.*\\d)[A-ZÆØÅa-zæøå\\d]{4,}$";

        if(password.matches(regex)){
            return password;
        }
        throw new InvalidUsernameException("Skriv inn gyldig passord.\nMå være minst 4 tegn og inneholde\nminst en bokstav og et tall");
    }

    // Oppretter liste med gyldige og ledige ansattnummer
    public static ArrayList<String> availableEmpNos = new ArrayList<>();

    public static void initializeEmpNos(){
        availableEmpNos.clear();
        availableEmpNos.add("A0123");
        availableEmpNos.add("A1234");
        availableEmpNos.add("A2345");
        availableEmpNos.add("A3456");
        availableEmpNos.add("A4567");
        availableEmpNos.add("A5678");
        availableEmpNos.add("A6789");
        availableEmpNos.add("A7890");
        availableEmpNos.add("A8901");
        availableEmpNos.add("A9012");
    }

    // Sjekker om ansattnummer er gyldig. Hvis gyldig slettes ansattnummeret fra lista,
    // slik at det ikke er mulig å lage flere brukere pr ansattnummer
    public static boolean testValidEmpNo(String empNo){
        for (String admin : availableEmpNos) {
            if (admin.matches(empNo)){
                return true;
            }
        }
        return false;
        /* try {
            Scanner empNos = new Scanner(new File(String.valueOf(availableEmpNoPath)));
            empNos.useDelimiter("[\n]");

            while (empNos.hasNext()) {
                String empNoFromList = empNos.next();

                // legger gyldige og ledige ansattnummer i lista
                availableEmpNos.add(empNoFromList);
            }
            throw new InvalidUserParseException("Ingen ledige ansattnummer");

        } catch (FileNotFoundException | InvalidUserParseException e) {
            e.getMessage();
        }

        */
    }
    /*
    public static void removeCurrentEmpNo(String empNo){
        for (String admin : availableEmpNos) {
            if (admin.matches(empNo)){
                availableEmpNos.remove(admin);
                break;
            }
        }
    }

     */
/*
    // Legger inn bruker-info inn i filen igjen
    public static String empNoTxtToFile() {
        StringBuilder ut = new StringBuilder();
        for (String empNo : availableEmpNos) {
            ut.append(empNo).append("\n");
        }
        return ut.toString();
    }

 */
}
