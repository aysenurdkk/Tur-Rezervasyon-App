import java.io.*;

public class KatilimciKayit {
    public static boolean katilimciKaydet(String kullaniciEmail, String turAdi, String secilenTarih, int kisiSayisi, String[] katilimciBilgileri) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("katilimcilar.txt", true))) { // true ile yeni veriler dosyanın sonuna eklenir, mevcut veriler silinmez
            writer.write(kullaniciEmail + "," + turAdi + "," + secilenTarih + "," + kisiSayisi);
            for (String katilimci : katilimciBilgileri) {
                if (katilimci != null && katilimci.split(";").length >= 6) { // en az 6 alanı (ad, soyad, uyruk, kimlik, doğum tarihi, telefon) içerip içermediğini kontrol et
                    writer.write("," + katilimci);
                } else {
                    System.out.println("Hata: Geçersiz katılımcı verisi: " + katilimci);
                    return false;
                }
            }
            writer.newLine(); // yeni katılımcı için yeni satır
            return true;
        } catch (IOException e) {
            System.out.println("Hata: Dosya yazma hatası: " + e.getMessage());
            return false;
        }
    }
} // try-with-resources ile dosya işlemleri bitince writer nesnesini otomatik kapadım