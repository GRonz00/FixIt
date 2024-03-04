package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Input {
    public static int prendi_int(int inizio, int fine) {
        int choice = 0;


        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.print("Selezionare scelta: ");
            try {
                choice = input.nextInt();
            } catch (InputMismatchException ignored) {}
            if (choice >= inizio && choice <= fine) {
                break;
            }
            System.out.println("Invalid option");
        }

        return choice;
    }

    public static int checkIfInt() {
        int num = -1;
        while (true) {
            Scanner input = new Scanner(System.in);
            try {
                num = input.nextInt();
            } catch (InputMismatchException ignored) {}
            if (num >= 0) break;
            System.out.print("Bisogna inserire un intero\n");
        }
        return num;
    }



    public static float checkIfFloat() {
        float num = -1;
        while (true) {
            Scanner input = new Scanner(System.in);
            try {
                num = input.nextFloat();
            } catch (InputMismatchException ignored) {}
            if (num >= 0) break;
            System.out.print("Bisogna inserire un numero positivo\n");
        }
        return num;
    }

    public static String checkString(){
        String a;
        while (true)
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            a = reader.readLine();
            if(a.matches(("^[a-zA-Z0-9 ]*$"))){break;}
            System.out.print("Bisogna inserire solo caratteri alfanumerici\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return a;
    }



    public static List<String> listaStringhe(String parola){
        List<String> a = new ArrayList<>();
        int c = 1;
        while (c==1){
            a.add(Input.checkString());
            System.out.print("1)Aggiungere "+parola+" 2)Finito\n");
            c = utils.Input.prendi_int(1,2);}
        return a;
    }

    public static String checkTesto() {
        String a;
        while (true)
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                a = reader.readLine();
                if(a.matches(("^[a-zA-Z0-9 ,.]*$"))){break;}
                System.out.print("Non si possono inserire caratteri speciali\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return a;
    }

    public static String checkIfTelefono() {
        String a;
        while (true)
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                a = reader.readLine();
                if(a.matches(("^[0-9]*$"))){break;}
                System.out.print("Bisogna inserire un numero di telefono\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return a;
    }
}
