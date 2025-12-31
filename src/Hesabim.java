import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hesabim {
    private JFrame frame;
    private String kullaniciEmail;

    public Hesabim(String kullaniciEmail) {
        this.kullaniciEmail = kullaniciEmail;
        frame = new JFrame("Hesabım");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.getContentPane().setBackground(new Color(176, 196, 222));
        frame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Hesabım", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.BLACK);
        frame.add(title, BorderLayout.NORTH);

        JTextArea detayArea = new JTextArea();
        detayArea.setEditable(false); // Kullanıcının tur kayıtlarını gösterir, düzenlenemez
        detayArea.setLineWrap(true); // Metin satır sonlarında kırılır.
        detayArea.setWrapStyleWord(true); // Kelimeler boşluklardan kırılır.
        detayArea.setFont(new Font("Segoe UI", Font.BOLD, 14));
        detayArea.setBackground(new Color(176, 196, 222));
        detayArea.setForeground(Color.BLACK);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("katilimcilar.txt"));
            String line;
            boolean hasTours = false;
            SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
            Date now = new Date();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4 || !parts[0].equals(kullaniciEmail)) {
                    continue;
                }

                hasTours = true;
                String turAdi = parts[1];
                String tarih = parts[2];
                int kisiSayisi;
                try {
                    kisiSayisi = Integer.parseInt(parts[3]);
                } catch (NumberFormatException e) {
                    detayArea.append("Hata: Geçersiz kişi sayısı formatı: " + parts[3] + "\n");
                    continue;
                }

                StringBuilder katilimcilar = new StringBuilder();
                int katilimciSayisi = 0;
                for (int i = 4; i < parts.length; i++) {
                    String[] katilimci = parts[i].split(";");
                    katilimciSayisi++;
                    String ad = katilimci.length > 0 ? katilimci[0] : "Bilinmiyor"; // indeksin değeri yoksa Bilinmiyor atanır.
                    String soyad = katilimci.length > 1 ? katilimci[1] : "Bilinmiyor";
                    String uyruk = katilimci.length > 2 ? katilimci[2] : "Bilinmiyor";
                    String kimlik = katilimci.length > 3 ? katilimci[3] : "Bilinmiyor";
                    String dogum = katilimci.length > 4 ? katilimci[4] : "Bilinmiyor";
                    String telefon = katilimci.length > 5 ? katilimci[5] : "Bilinmiyor";

                    katilimcilar.append("Katılımcı ").append(katilimciSayisi).append(":\n")
                                .append("  Ad: ").append(ad).append("\n")
                                .append("  Soyad: ").append(soyad).append("\n")
                                .append("  Uyruk: ").append(uyruk).append("\n")
                                .append("  Kimlik No: ").append(kimlik).append("\n")
                                .append("  Doğum Tarihi: ").append(dogum).append("\n")
                                .append("  Telefon: ").append(telefon).append("\n\n");
                    // StringBuilder a her katılımcının bilgilerini ekledim
                }

                if (katilimciSayisi != kisiSayisi) {
                    detayArea.append("Uyarı: Kayıtlı katılımcı sayısı (" + katilimciSayisi + ") kişi sayısıyla (" + kisiSayisi + ") uyuşmuyor!\n");
                }

                Date turTarihi;
                try {
                    turTarihi = sdf.parse(tarih);
                } catch (Exception e) {
                    detayArea.append("Hata: Geçersiz tarih formatı: " + tarih + "\n");
                    continue;
                }
                String turDurumu = turTarihi.before(now) ? "Geçmiş" : "Gelecek"; // hesabım sayfasındaki tur durumu için

                detayArea.append("Tur Adı: " + turAdi + "\n");
                detayArea.append("Tarih: " + tarih + "\n");
                detayArea.append("Durum: " + turDurumu + "\n");
                detayArea.append("Kişi Sayısı: " + kisiSayisi + "\n");
                detayArea.append("Katılımcılar:\n" + katilimcilar.toString());
                detayArea.append("------------------------\n\n");
            }
            reader.close();

            if (!hasTours) {
                detayArea.append("Henüz kayıtlı turunuz bulunmamaktadır.");
            }
        } catch (FileNotFoundException ex) {
            detayArea.append("Hata: katilimcilar.txt dosyası bulunamadı!");
        } catch (IOException ex) {
            detayArea.append("Hata: Dosya okuma hatası: " + ex.getMessage());
        } catch (Exception ex) {
            detayArea.append("Hata: Beklenmeyen hata: " + ex.getMessage());
        }

        frame.add(new JScrollPane(detayArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(176, 196, 222));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton geriButton = createStyledButton("Geri");
        JButton okButton = createStyledButton("OK");

        buttonPanel.add(geriButton);
        buttonPanel.add(okButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        geriButton.addActionListener(e -> {
            frame.dispose();
            new TatilSecenekleri(kullaniciEmail);
        });

        okButton.addActionListener(e -> {
            System.exit(0);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Kalın yazı
        button.setBackground(new Color(135, 206, 235));
        button.setForeground(Color.BLACK); // Siyah yazı
        button.setBorder(new RoundedBorder(10));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 40));
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(100, 149, 237));
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(135, 206, 235));
            }
        });
        return button;
    }

    static class RoundedBorder implements javax.swing.border.Border {
        private final int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 2, radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(new Color(70, 130, 180));
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}