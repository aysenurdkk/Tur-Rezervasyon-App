import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class KatilimciBilgileri {
    private JFrame frame;
    private TurBilgisi tur;
    private String secilenTarih;
    private int kisiSayisi;
    private String kullaniciEmail;
    private List<JTextField> adFields;
    private List<JTextField> soyadFields;
    private List<Choice> uyrukChoices;
    private List<JTextField> kimlikFields;
    private List<JTextField> dogumTarihiFields;
    private List<JTextField> telefonFields;

    public KatilimciBilgileri(TurBilgisi tur, String secilenTarih, int kisiSayisi, String kullaniciEmail) {
        this.tur = tur;
        this.secilenTarih = secilenTarih;
        this.kisiSayisi = kisiSayisi;
        this.kullaniciEmail = kullaniciEmail;
        frame = new JFrame("Katılımcı Bilgileri");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.getContentPane().setBackground(new Color(176, 196, 222));
        frame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Katılımcı Bilgileri", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(70, 130, 180));
        frame.add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(); // katılımcı giriş formları için
        formPanel.setBackground(new Color(176, 196, 222));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        adFields = new ArrayList<>(); // arraylistleri form alanları için oluşturdum
        soyadFields = new ArrayList<>(); // Initialize soyadFields to prevent NullPointerException
        uyrukChoices = new ArrayList<>();
        kimlikFields = new ArrayList<>();
        dogumTarihiFields = new ArrayList<>();
        telefonFields = new ArrayList<>();

        for (int i = 0; i < kisiSayisi; i++) {
            JPanel katilimciPanel = new JPanel(); // her katılımcı için panel döngüsü
            katilimciPanel.setBackground(new Color(176, 196, 222));
            katilimciPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180)),
                "Katılımcı " + (i + 1),
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.PLAIN, 14),
                new Color(70, 130, 180)
            ));
            katilimciPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel adLabel = new JLabel("Ad:");
            adLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            adLabel.setForeground(Color.BLACK);
            JTextField adField = new JTextField(15);
            adField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            JLabel soyadLabel = new JLabel("Soyad:");
            soyadLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            soyadLabel.setForeground(Color.BLACK);
            JTextField soyadField = new JTextField(15);
            soyadField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            JLabel uyrukLabel = new JLabel("Uyruk:");
            uyrukLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            uyrukLabel.setForeground(Color.BLACK);
            Choice uyrukChoice = new Choice();
            uyrukChoice.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            uyrukChoice.add("Türk");
            uyrukChoice.add("Yabancı");

            JLabel kimlikLabel = new JLabel("Kimlik No:");
            kimlikLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            kimlikLabel.setForeground(Color.BLACK);
            JTextField kimlikField = new JTextField(15);
            kimlikField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            JLabel dogumTarihiLabel = new JLabel("Doğum Tarihi:");
            dogumTarihiLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            dogumTarihiLabel.setForeground(Color.BLACK);
            JTextField dogumTarihiField = new JTextField(15);
            dogumTarihiField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            dogumTarihiField.setToolTipText("Gün.Ay.Yıl");

            JLabel telefonLabel = new JLabel("Telefon:");
            telefonLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            telefonLabel.setForeground(Color.BLACK);
            JTextField telefonField = new JTextField(15);
            telefonField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            // panel ızgara ayarları
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0.2;
            katilimciPanel.add(adLabel, gbc);
            gbc.gridx = 1;
            gbc.weightx = 0.8;
            katilimciPanel.add(adField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.weightx = 0.2;
            katilimciPanel.add(soyadLabel, gbc);
            gbc.gridx = 1;
            gbc.weightx = 0.8;
            katilimciPanel.add(soyadField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.weightx = 0.2;
            katilimciPanel.add(uyrukLabel, gbc);
            gbc.gridx = 1;
            gbc.weightx = 0.8;
            katilimciPanel.add(uyrukChoice, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.weightx = 0.2;
            katilimciPanel.add(kimlikLabel, gbc);
            gbc.gridx = 1;
            gbc.weightx = 0.8;
            katilimciPanel.add(kimlikField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.weightx = 0.2;
            katilimciPanel.add(dogumTarihiLabel, gbc);
            gbc.gridx = 1;
            gbc.weightx = 0.8;
            katilimciPanel.add(dogumTarihiField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.weightx = 0.2;
            katilimciPanel.add(telefonLabel, gbc);
            gbc.gridx = 1;
            gbc.weightx = 0.8;
            katilimciPanel.add(telefonField, gbc);

            formPanel.add(katilimciPanel);
            formPanel.add(Box.createVerticalStrut(10)); //katılımcı panelleri arası 10 piksel dikey boşluk

            adFields.add(adField);
            soyadFields.add(soyadField);
            uyrukChoices.add(uyrukChoice);
            kimlikFields.add(kimlikField);
            dogumTarihiFields.add(dogumTarihiField);
            telefonFields.add(telefonField);
        }    // giriş alanları, listelerine eklenir

        JButton devamButton = createStyledButton("Devam Et");
        frame.add(new JScrollPane(formPanel), BorderLayout.CENTER);
        frame.add(devamButton, BorderLayout.SOUTH);

        devamButton.addActionListener(e -> {
            for (int i = 0; i < kisiSayisi; i++) {
                String ad = adFields.get(i).getText().trim();
                String soyad = soyadFields.get(i).getText().trim();
                String uyruk = uyrukChoices.get(i).getSelectedItem();
                String kimlik = kimlikFields.get(i).getText().trim();
                String dogumTarihi = dogumTarihiFields.get(i).getText().trim();
                String telefon = telefonFields.get(i).getText().trim();

                if (ad.isEmpty() || soyad.isEmpty() || kimlik.isEmpty() || dogumTarihi.isEmpty() || telefon.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Tüm alanları doldurun!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (uyruk.equals("Türk") && !kimlik.matches("\\d{11}")) {
                    JOptionPane.showMessageDialog(frame, "TC Kimlik No 11 haneli olmalı!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!dogumTarihi.matches("\\d{1,2}\\.\\d{1,2}\\.\\d{4}")) {
                    JOptionPane.showMessageDialog(frame, "Doğum tarihi Gün.Ay.Yıl formatında olmalı!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!telefon.matches("\\d{11}")) {
                    JOptionPane.showMessageDialog(frame, "Telefon numarası 11 haneli olmalı!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            String[] katilimciBilgileri = new String[kisiSayisi];
            for (int i = 0; i < kisiSayisi; i++) {
                katilimciBilgileri[i] = adFields.get(i).getText().trim() + ";" +
                                        soyadFields.get(i).getText().trim() + ";" +
                                        uyrukChoices.get(i).getSelectedItem() + ";" +
                                        kimlikFields.get(i).getText().trim() + ";" +
                                        dogumTarihiFields.get(i).getText().trim() + ";" +
                                        telefonFields.get(i).getText().trim();
            }  // katılımcı bilgilerini dosyada ; ile ayırdım

            if (KatilimciKayit.katilimciKaydet(kullaniciEmail, tur.getAd(), secilenTarih, kisiSayisi, katilimciBilgileri)) {
                frame.dispose();
                new Odeme(tur, secilenTarih, kisiSayisi, kullaniciEmail);
            } else {
                JOptionPane.showMessageDialog(frame, "Katılımcı kaydı başarısız!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBackground(new Color(135, 206, 235));
        button.setForeground(Color.WHITE);
        button.setBorder(new RoundedBorder(10));
        button.setFocusPainted(false);
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