import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Odeme {
    private JFrame frame;
    private TurBilgisi tur;
    private String secilenTarih;
    private int kisiSayisi;
    private String kullaniciEmail;

    public Odeme(TurBilgisi tur, String secilenTarih, int kisiSayisi, String kullaniciEmail) {
        this.tur = tur;
        this.secilenTarih = secilenTarih;
        this.kisiSayisi = kisiSayisi;
        this.kullaniciEmail = kullaniciEmail;
        frame = new JFrame("Ödeme Bilgileri");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 250);
        frame.getContentPane().setBackground(new Color(176, 196, 222));
        frame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Ödeme Bilgileri", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.BLACK);
        frame.add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(176, 196, 222));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel kartNoLabel = new JLabel("Kart Numarası:");
        kartNoLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        kartNoLabel.setForeground(Color.BLACK);
        kartNoLabel.setToolTipText("16 haneli olmalı");
        JTextField kartNoField = new JTextField();
        kartNoField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel sktLabel = new JLabel("SKT (Ay/Yıl):");
        sktLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sktLabel.setForeground(Color.BLACK);
        sktLabel.setToolTipText("Ay/Yıl formatında (örn. 12/25)");
        JTextField sktField = new JTextField();
        sktField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel cvvLabel = new JLabel("CVV:");
        cvvLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cvvLabel.setForeground(Color.BLACK);
        cvvLabel.setToolTipText("3 haneli olmalı");
        JTextField cvvField = new JTextField();
        cvvField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        formPanel.add(kartNoLabel);
        formPanel.add(kartNoField);
        formPanel.add(sktLabel);
        formPanel.add(sktField);
        formPanel.add(cvvLabel);
        formPanel.add(cvvField);

        JButton odemeButton = createStyledButton("Ödeme Yap");
        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(odemeButton, BorderLayout.SOUTH);

        odemeButton.addActionListener(e -> {
            String kartNo = kartNoField.getText().trim(); // bilgiler boşlukları temizlenip alınır
            String skt = sktField.getText().trim(); // Fixed: Changed 'sktp' to 'skt'
            String cvv = cvvField.getText().trim();

            if (kartNo.isEmpty() || skt.isEmpty() || cvv.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Tüm alanları doldurun!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!kartNo.matches("\\d{16}")) {
                JOptionPane.showMessageDialog(frame, "Kart numarası 16 haneli olmalı!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!skt.matches("\\d{2}/\\d{2}")) {
                JOptionPane.showMessageDialog(frame, "SKT Ay/Yıl formatında olmalı (örn. 12/25)!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!cvv.matches("\\d{3}")) {
                JOptionPane.showMessageDialog(frame, "CVV 3 haneli olmalı!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            OdemeKayit.odemeKaydet(kullaniciEmail, tur.getAd(), kartNo, skt, cvv); // Ödeme başarılıysa bilgiler kaydediliyor
            frame.dispose();
            new TatilOnay(tur, secilenTarih, kisiSayisi, kullaniciEmail);
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
        button.setFocusPainted(false); // tıklandığında görünen odak çerçevesi devre dışı
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