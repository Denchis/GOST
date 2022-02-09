import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input message: ");
        String input = scanner.nextLine();
        final byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        byte[] hashbytes = GOST.hash(bytes);
        String result = Utils.convert(hashbytes);
        System.out.println("HASH: " + result);
        System.out.println("SAMARA");
    }
}
