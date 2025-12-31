import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KisiSayisiSecim {
    private JFrame frame;
    private TurBilgisi tur;
    private String secilenTarih;
    private String kullaniciEmail;

    public KisiSayisiSecim(TurBilgisi tur, String secilenTarih, String kullaniciEmail) {
        this.tur = tur;
        this.secilenTarih = secilenTarih;
        this.kullaniciEmail = kullaniciEmail;
        frame = new JFrame("Kişi Sayısı Seçimi");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.getContentPane().setBackground(new Color(176, 196, 222));
        frame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Kişi Sayısı Seçimi", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.BLACK);

        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(); // kişi sayısı seçim bileşenlerini tutan yeni panel
        panel.setBackground(new Color(176, 196, 222));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Kişi Sayısı (Max 8):");
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(Color.BLACK);

        Choice kisiSayisiChoice = new Choice();
        kisiSayisiChoice.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        for (int i = 1; i <= 8; i++) {
            kisiSayisiChoice.add(String.valueOf(i));
        }

        panel.add(label);
        panel.add(kisiSayisiChoice);

        JButton devamButton = createStyledButton("Devam Et");
        frame.add(panel, BorderLayout.CENTER);
        frame.add(devamButton, BorderLayout.SOUTH);

        devamButton.addActionListener(e -> {
            int kisiSayisi = Integer.parseInt(kisiSayisiChoice.getSelectedItem()); // kullanıcının seçtiği sayı alınıp tamsayıya çevrilir
            frame.dispose();
            new KatilimciBilgileri(tur, secilenTarih, kisiSayisi, kullaniciEmail);
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