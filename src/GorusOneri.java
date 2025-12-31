import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GorusOneri {
    private JFrame frame;
    private TurBilgisi tur;
    private String secilenTarih;
    private int kisiSayisi;
    private String kullaniciEmail;

    public GorusOneri(TurBilgisi tur, String secilenTarih, int kisiSayisi, String kullaniciEmail) {
        this.tur = tur;
        this.secilenTarih = secilenTarih;
        this.kisiSayisi = kisiSayisi;
        this.kullaniciEmail = kullaniciEmail;
        frame = new JFrame("Görüş ve Öneri");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.getContentPane().setBackground(new Color(176, 196, 222));
        frame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Görüş ve Öneri", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.BLACK); 
        frame.add(title, BorderLayout.NORTH);

        JTextArea gorusArea = new JTextArea();
        gorusArea.setLineWrap(true); // Metin otomatik olarak satır sonlarında kırılır
        gorusArea.setWrapStyleWord(true); // Kelimeler boşluklardan bölünür
        gorusArea.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        gorusArea.setBackground(new Color(176, 196, 222));
        gorusArea.setForeground(Color.BLACK); 
        frame.add(new JScrollPane(gorusArea), BorderLayout.CENTER);

        JButton gonderButton = createStyledButton("Gönder");
        frame.add(gonderButton, BorderLayout.SOUTH);

        gonderButton.addActionListener(e -> {
            String gorus = gorusArea.getText().trim(); // görüş alınır ve boşluklar temizlenir
            if (!gorus.isEmpty()) { 
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("gorusler.txt", true))) {
                    writer.write(kullaniciEmail + ": " + gorus);
                    writer.newLine();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Görüş kaydedilemedi!", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
            frame.dispose();
            new Tesekkur(tur, secilenTarih, kisiSayisi, kullaniciEmail);
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