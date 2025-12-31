import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class YurtDisiSecenekleri {
    private JFrame frame;
    private String kullaniciEmail;

    public YurtDisiSecenekleri(String kullaniciEmail) {
        this.kullaniciEmail = kullaniciEmail;
        frame = new JFrame("Yurt Dışı Turlar");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.getContentPane().setBackground(new Color(176, 196, 222));
        frame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Yurt Dışı Turlar", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.BLACK);
        frame.add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(176, 196, 222));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setLayout(new GridLayout(0, 1, 10, 10));

        List<TurBilgisi> turlar = TurDosyaOkuyucu.turlariOku("yurtdisi.txt", false); // //her tur için TurBilgisi nesnesi
        if (turlar.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Yurt dışı turlar yüklenemedi!", "Hata", JOptionPane.ERROR_MESSAGE);
        } else {
            for (TurBilgisi tur : turlar) {  
                JButton turButton = createStyledButton(tur.getAd());
                buttonPanel.add(turButton);
                turButton.addActionListener(e -> {
                    frame.dispose();
                    new TurDetay(tur, kullaniciEmail);
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
                button.setBackground(new Color(100, 149, 237));
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(135, 206, 235));
            }
        });
        return button;
    }
}