import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class YoneticiGiris {
    private JFrame frame;

    public YoneticiGiris() {
        frame = new JFrame("Yönetici Girişi");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450, 200);
        frame.getContentPane().setBackground(new Color(176, 196, 222));
        frame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Yönetici Girişi", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.BLACK);
        frame.add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(176, 196, 222));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setLayout(new GridLayout(1, 2, 10, 10));

        JLabel sifreLabel = new JLabel("Şifre:");
        sifreLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sifreLabel.setForeground(Color.BLACK);
        JPasswordField sifreField = new JPasswordField();
        sifreField.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formPanel.add(sifreLabel);
        formPanel.add(sifreField);

        JButton girisButton = createStyledButton("Giriş Yap");
        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(girisButton, BorderLayout.SOUTH);

        girisButton.addActionListener(e -> {
            String sifre = new String(sifreField.getPassword()).trim();
            if (sifre.equals("Tur1234*")) {
                JTextArea dosyaIcerik = new JTextArea(); // düzenlenebilir TextArea oluşturuldu
                dosyaIcerik.setEditable(true);
                dosyaIcerik.setFont(new Font("Segoe UI", Font.BOLD, 14));
                dosyaIcerik.setBackground(new Color(245, 245, 245));
                dosyaIcerik.setForeground(Color.BLACK);

                try {
                    StringBuilder content = new StringBuilder();
                    content.append("=== yurtici.txt ===\n");
                    BufferedReader reader = new BufferedReader(new FileReader("yurtici.txt"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    reader.close();

                    content.append("\n=== yurtdisi.txt ===\n"); // dosya adları arasına ayırıcı ekledim
                    reader = new BufferedReader(new FileReader("yurtdisi.txt"));
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    reader.close();

                    dosyaIcerik.setText(content.toString());
                } catch (IOException ex) {
                    dosyaIcerik.setText("Dosya okuma hatası: " + ex.getMessage());
                }

                JFrame dosyaFrame = new JFrame("Tur Dosyaları (Düzenlenebilir)");
                dosyaFrame.setSize(600, 500);
                dosyaFrame.getContentPane().setBackground(new Color(176, 196, 222));
                dosyaFrame.setLayout(new BorderLayout());

                JScrollPane scrollPane = new JScrollPane(dosyaIcerik);
                scrollPane.getViewport().setBackground(new Color(176, 196, 222));
                dosyaFrame.add(scrollPane, BorderLayout.CENTER);

                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                buttonPanel.setBackground(new Color(176, 196, 222));

                JButton kaydetButton = new JButton("Kaydet");
                kaydetButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
                kaydetButton.setBackground(new Color(60, 179, 113));
                kaydetButton.setForeground(Color.BLACK);
                kaydetButton.setBorder(new RoundedBorder(10));

                kaydetButton.addActionListener(ae -> {
                    try {
                        String text = dosyaIcerik.getText(); // tüm metni string aldım
                        String[] parts = text.split("=== yurtdisi.txt ==="); // ayırıcıya göre bölüp saklama , parts[0],[1]

                        if (parts.length == 2) { // 2 part varsa  dosya kaydetme işlemine devam 
                            String yurticiContent = parts[0].replace("=== yurtici.txt ===", "").trim(); // ayırıcıyı kaldırma
                            BufferedWriter writer = new BufferedWriter(new FileWriter("yurtici.txt")); //yazma modunda açtım dosyayı
                            writer.write(yurticiContent);//dosyaya yazdı
                            writer.close();

                            String yurtdisiContent = parts[1].trim();
                            writer = new BufferedWriter(new FileWriter("yurtdisi.txt"));
                            writer.write(yurtdisiContent);
                            writer.close();

                            JOptionPane.showMessageDialog(dosyaFrame, "Dosyalar başarıyla kaydedildi!");
                        } else {
                            JOptionPane.showMessageDialog(dosyaFrame, "Format hatası! Lütfen dosya ayrımlarını koruyun.", "Hata", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(dosyaFrame, "Kaydetme hatası: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                });

                JButton geriButton = new JButton("Geri Dön");
                geriButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
                geriButton.setBackground(new Color(135, 206, 235));
                geriButton.setForeground(Color.BLACK);
                geriButton.setBorder(new RoundedBorder(10));
                geriButton.setFocusPainted(false);
                geriButton.addActionListener(evt -> {
                    dosyaFrame.dispose();
                    new Giris();
                });

                buttonPanel.add(kaydetButton);
                buttonPanel.add(geriButton);
                dosyaFrame.add(buttonPanel, BorderLayout.SOUTH);

                dosyaFrame.setLocationRelativeTo(null);
                dosyaFrame.setVisible(true);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Yanlış şifre!", "Hata", JOptionPane.ERROR_MESSAGE);
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

    static class RoundedBorder implements javax.swing.border.Border {
        private int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 2, radius);
        }

        public boolean isBorderOpaque() {
            return false;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.BLACK);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}