// kullanıcıların ödeme bilgilerini  odemeler.txte kaydetmek için kullandığım yardımcı class
import java.io.*;

public class OdemeKayit {
    public static void odemeKaydet(String email, String turAdi, String kartNo, String skt, String cvv) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("odemeler.txt", true))) {
            writer.write(email + "," + turAdi + "," + kartNo + "," + skt + "," + cvv);
            writer.newLine();
        } catch (IOException e) {
            // dosya yazmada hata alırsam hiçbir işlem yapmıyorum
        }
    }
}