import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class KullaniciKayit {
    public static boolean kullaniciKaydet(String ad, String soyad, String email, String sifre) {
        try (BufferedReader reader = new BufferedReader(new FileReader("kullanicilar.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[2].equals(email)) {
                    return false; // e-posta zaten varsa kayıt durdurulur 
                }
            }
        } catch (FileNotFoundException e) {
            // dosya yoksa oluşturulacak
        } catch (IOException e) { // dosya okuma hatası olursa false döncek
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("kullanicilar.txt", true))) { // // true ile yeni veriler dosyanın sonuna eklenir
            writer.write(ad + "," + soyad + "," + email + "," + sifre);
            writer.newLine();
            return true; // satır ekleme başarılıysa true döncek
           
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean girisKontrol(String email, String sifre) {
        try (BufferedReader reader = new BufferedReader(new FileReader("kullanicilar.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // virgülle bölünen satırlar parts dizisine ayrıldı
                if (parts[2].equals(email) && parts[3].equals(sifre)) {
                    return true; // başarılı giriş
                }
            }
        } catch (IOException e) { 
        	return false;
        }
        return false; // eşleşme olmazsa
    }
}