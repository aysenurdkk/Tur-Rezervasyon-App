import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UyeOl {
    private JFrame frame;

    public UyeOl() {
        frame = new JFrame("Üye Ol");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);
        frame.getContentPane().setBackground(new Color(176, 196, 222));
        frame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Üye Ol", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.BLACK);
        frame.add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(176, 196, 222));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel adLabel = new JLabel("Ad:");
        adLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        adLabel.setForeground(Color.BLACK);
        JTextField adField = new JTextField();
        adField.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JLabel soyadLabel = new JLabel("Soyad:");
        soyadLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        soyadLabel.setForeground(Color.BLACK);
        JTextField soyadField = new JTextField();
        soyadField.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JLabel emailLabel = new JLabel("E-posta:");
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        emailLabel.setForeground(Color.BLACK);
        JTextField emailField = new JTextField();
        emailField.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JLabel sifreLabel = new JLabel("Şifre:");
        sifreLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sifreLabel.setForeground(Color.BLACK);
        sifreLabel.setToolTipText("Min 8 karakter, 1 rakam, 1 özel karakter (!@#$%^&*+=?-)");
        JPasswordField sifreField = new JPasswordField();
        sifreField.setFont(new Font("Segoe UI", Font.BOLD, 14));

        formPanel.add(adLabel);
        formPanel.add(adField);
        formPanel.add(soyadLabel);
        formPanel.add(soyadField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(sifreLabel);
        formPanel.add(sifreField);

        JButton uyeOlButton = createStyledButton("Üye Ol");
        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(uyeOlButton, BorderLayout.SOUTH);

        uyeOlButton.addActionListener(e -> {
            String ad = adField.getText().trim();
            String soyad = soyadField.getText().trim();
            String email = emailField.getText().trim();
            String sifre = new String(sifreField.getPassword()).trim();

            if (ad.isEmpty() || soyad.isEmpty() || email.isEmpty() || sifre.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Tüm alanları doldurun!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) { 
                JOptionPane.showMessageDialog(frame, "Geçerli bir e-posta girin!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!sifre.matches("^(?=.*\\d)(?=.*[!@#$%^&*+=?-]).{8,}$")) {
                StringBuilder hataMesaji = new StringBuilder("Şifre geçersiz! Şifre:\n");
                if (sifre.length() < 8) {
                    hataMesaji.append("- En az 8 karakter olmalı\n");
                }
                if (!sifre.matches(".*\\d.*")) {
                    hataMesaji.append("- En az 1 rakam içermeli\n");
                }
                if (!sifre.matches(".*[!@#$%^&*+=?-].*")) {
                    hataMesaji.append("- En az 1 özel karakter içermeli (!@#$%^&*+=?-)\n");
                }
                JOptionPane.showMessageDialog(frame, hataMesaji.toString(), "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (KullaniciKayit.kullaniciKaydet(ad, soyad, email, sifre)) {
                JOptionPane.showMessageDialog(frame, "Üyelik başarılı!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new TatilSecenekleri(email);
            } else {
                JOptionPane.showMessageDialog(frame, "Bu e-posta zaten kayıtlı!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(135, 206, 235));
        button.setForeground(Color.BLACK);
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
}