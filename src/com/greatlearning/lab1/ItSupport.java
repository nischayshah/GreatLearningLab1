package com.greatlearning.lab1;

import java.security.SecureRandom;
import java.util.Scanner;

public class ItSupport  {

    static String firstName;
    static String lastName;
    static String departmentName = null;
    static String company=null;


    ItSupport(String firstName, String lastName, String departmentName, String company){
        this.firstName=firstName;
        this.lastName=lastName;
        this.departmentName=departmentName;
        this.company=company;
    }


    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        System.out.println("Please enter the Company Name : ");
        company=sc.next();
        System.out.println("Please enter the department from the following");
        System.out.println("1. Technical \n 2. Admin \n 3. Human Resource \n 4. Legal");
        int dept=sc.nextInt();
        switch (dept){
            case 1 : departmentName="tech";
                break;
            case 2 : departmentName="admin";
                break;
            case 3 : departmentName="hr";
                break;
            case 4 : departmentName="legal";
                break;
            default: System.out.println(" Wrong input");
                break;
        }

        if(departmentName!=null){
            ItSupport it1=new ItSupport("harshit","chaudhary", departmentName, company);

            String email = CredentialService.generateEmailAddress(it1);
            String password = CredentialService.generatePassword(8); // Adjust the length as needed

            CredentialService.showCredentials(it1, email, password);
        }

    }
}

class CredentialService {
    static String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String SMALL_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    static String DIGITS = "0123456789";
    static String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/";

    public static String generateEmailAddress(ItSupport employee) {
        return employee.firstName.toLowerCase().replace(" ", "") + employee.lastName.toLowerCase().replace(" ", "") + "@" + employee.departmentName.toLowerCase().replace(" ", "") + "." + employee.company.toLowerCase().replace(" ", "")+".com";
    }

    public static String generatePassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        password.append(getRandomChar(DIGITS, random));
        password.append(getRandomChar(CAPITAL_LETTERS, random));
        password.append(getRandomChar(SMALL_LETTERS, random));
        password.append(getRandomChar(SPECIAL_CHARACTERS, random));

        for (int i = 4; i < length; i++) {
            String allCharacters = CAPITAL_LETTERS + SMALL_LETTERS + DIGITS + SPECIAL_CHARACTERS;
            password.append(getRandomChar(allCharacters, random));
        }

        return shuffleString(password.toString(), random);
    }

    public static void showCredentials(ItSupport employee, String email, String password) {
        System.out.println("Dear " + employee.firstName.toLowerCase().replace(" ", "")  + ", your generated credentials are as follows:");
        System.out.println("Email ---> " + email);
        System.out.println("Password ---> " + password);
    }

    private static char getRandomChar(String characters, SecureRandom random) {
        int index = random.nextInt(characters.length());
        return characters.charAt(index);
    }

    private static String shuffleString(String input, SecureRandom random) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = characters[index];
            characters[index] = characters[i];
            characters[i] = temp;
        }
        return new String(characters);
    }
}


