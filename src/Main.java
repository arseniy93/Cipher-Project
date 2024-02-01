import com.javarush.cryptoanalyzer.analyzer.CaesarCipher;
import com.javarush.cryptoanalyzer.files.WorkWithFile;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        CaesarCipher caesarCipher=new CaesarCipher();
        String message="Мама, лебле тебя!";
        System.out.println("message = " + message);
        String messageCiphet= String.valueOf(caesarCipher.toCipher(message,35));
        System.out.println("messageCiphet = " + messageCiphet);
        int getKey=caesarCipher.getKeyFromCipherText(message,messageCiphet);
        System.out.println("caesarCipher.toUnCipher(messageCiphet,35) = " + caesarCipher.toUnCipher(messageCiphet, 35));
        System.out.println("getKey = " + getKey);
       WorkWithFile workWithFile=new WorkWithFile("D:\\","D:\\");
//        System.out.println(workWithFile.loadTextOfFile("java.txt"));
       CaesarCipher caesarCipher1=new CaesarCipher();
//       System.out.println("caesarCipher.toCipher(workWithFile.loadTextOfFile(\"java.txt\"),12) = " + caesarCipher.toCipher(workWithFile.loadTextOfFile("java.txt"), 12));
//        workWithFile.createFileWriteToText(caesarCipher.toCipher(workWithFile.loadTextOfFile("java.txt"), 12),"LOLO.txt");
       System.out.println(caesarCipher1.getKeyFromCipherText(workWithFile.loadTextOfFile("LOLO.txt"), workWithFile.loadTextOfFile("java.txt")));


    }
}