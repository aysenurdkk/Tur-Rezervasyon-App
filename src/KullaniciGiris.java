import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KullaniciGiris {
    private JFrame frame;

    public KullaniciGiris() {
        frame = new JFrame("Giriş Yap");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.getContentPane().setBackground(new Color(176, 196, 222));
        frame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Giriş Yap", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.BLACK);
        frame.add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(176, 196, 222));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setLayout(new GridLayout(2, 2, 10, 10));

        JLabel emailLabel = new JLabel("E-posta:");
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        emailLabel.setForeground(Color.BLACK);
        JTextField emailField = new JTextField();
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel sifreLabel = new JLabel("Şifre:");
        sifreLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sifreLabel.setForeground(Color.BLACK);
        JPasswordField sifreField = new JPasswordField();
        sifreField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(sifreLabel);
        formPanel.add(sifreField);

        JButton girisButton = createStyledButton("Giriş Yap");
        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(girisButton, BorderLayout.SOUTH);

        girisButton.addActionListener(e -> {
            String email = emailField.getText().trim(); // mail ve şifre alınırken trim() ile boşlukları temizler
            String sifre = new String(sifreField.getPassword()).trim();

            if (email.isEmpty() || sifre.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "E-posta ve şifre girin!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (KullaniciKayit.girisKontrol(email, sifre)) {
                JOptionPane.showMessageDialog(frame, "Giriş başarılı!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new TatilSecenekleri(email);
            } else {
                JOptionPane.showMessageDialog(frame, "E-posta veya şifre yanlış!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JButton createStyledButton(String text) { // metin içeren özelleştirilmiş buton
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