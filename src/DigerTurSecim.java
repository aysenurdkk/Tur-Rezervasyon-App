import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class DigerTurSecim {
    private JFrame frame;
    private String kullaniciEmail;

    public DigerTurSecim(String kullaniciEmail) {
        this.kullaniciEmail = kullaniciEmail; // tur seçimiyle ilgili işlemler için e posta tutuluyor
        frame = new JFrame("Diğer Turlar");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.getContentPane().setBackground(new Color(176, 196, 222));
        frame.setLayout(new BorderLayout(10, 10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(176, 196, 222));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setLayout(new GridLayout(0, 1, 10, 10));

        List<TurBilgisi> turlar = TurDosyaOkuyucu.turlariOku("yurtici.txt", false); // dosyadan tur bilgilerini okur ve TurBilgisi nesnelerinden oluşan bir liste döndürür
        if (turlar.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Diğer turlar yüklenemedi!", "Hata", JOptionPane.ERROR_MESSAGE);
        } else {
            for (TurBilgisi tur : turlar) {
                JButton turButton = createStyledButton(tur.getAd());
                buttonPanel.add(turButton);
                turButton.addActionListener(e -> {
                    frame.dispose();
                    new TurDetay(tur, kullaniciEmail); // Seçilen tur ve kullanıcı e-postası ile TurDetay sınıfından yeni pencere açar
                });
            }
        }

        frame.add(new JScrollPane(buttonPanel), BorderLayout.CENTER);
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
                button.setBackground(new Color(100, 149, 237)); // arka plan rengi daha koyu
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(135, 206, 235));
            }
        });
        return button;
    }
}
