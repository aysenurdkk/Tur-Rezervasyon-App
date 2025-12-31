import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TurDosyaOkuyucu {
    public static List<TurBilgisi> turlariOku(String dosyaAdi, boolean gunubirlik) {
        List<TurBilgisi> turlar = new ArrayList<>();
        File file = new File(dosyaAdi);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "Dosya bulunamadı: " + file.getAbsolutePath(), "Hata", JOptionPane.ERROR_MESSAGE);
            return turlar;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String satir;
            String ad = null;
            StringBuilder icerik = new StringBuilder();
            double fiyat = 0;
            int sure = 0;

            while ((satir = reader.readLine()) != null) {
                satir = satir.trim();
                if (satir.startsWith("Günübirlik") || satir.startsWith("Diğer") || satir.startsWith("Yurt Dışı")) {
                    if (ad != null) {
                        if (dosyaAdi.equals("yurtdisi.txt") && !gunubirlik) {
                            turlar.add(new YurtDisiTurImpl(ad, icerik.toString(), fiyat, sure));
                        } else if (gunubirlik && ad.startsWith("Günübirlik")) {
                            turlar.add(new GunubirlikTur(ad, icerik.toString(), fiyat));
                        } else if (!gunubirlik && ad.startsWith("Diğer")) {
                            turlar.add(new DigerTur(ad, icerik.toString(), fiyat, sure));
                        }
                        icerik = new StringBuilder();
                    }
                    ad = satir;
                    fiyat = 0;
                    sure = 0;
                } else if (satir.matches("\\d+\\s*(tl|euro)")) {
                    fiyat = Double.parseDouble(satir.replaceAll("[^0-9]", ""));
                    icerik.append(satir).append("\n");
                } else if (satir.matches("\\d+\\. Gün.*")) {
                    sure++;
                    icerik.append(satir).append("\n");
                } else if (!satir.isEmpty()) {
                    icerik.append(satir).append("\n");
                }
            }

            if (ad != null) {
                if (dosyaAdi.equals("yurtdisi.txt") && !gunubirlik) {
                    turlar.add(new YurtDisiTurImpl(ad, icerik.toString(), fiyat, sure));
                } else if (gunubirlik && ad.startsWith("Günübirlik")) {
                    turlar.add(new GunubirlikTur(ad, icerik.toString(), fiyat));
                } else if (!gunubirlik && ad.startsWith("Diğer")) {
                    turlar.add(new DigerTur(ad, icerik.toString(), fiyat, sure));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Dosya okuma hatası: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
        return turlar;
    }
}