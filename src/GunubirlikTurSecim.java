import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GunubirlikTurSecim {
    private JFrame frame;
    private String kullaniciEmail; // Kullanıcının e-postası tur seçimiyle ilgili işlemler için kullanılır

    public GunubirlikTurSecim(String kullaniciEmail) {
        this.kullaniciEmail = kullaniciEmail;
        frame = new JFrame("Günübirlik Turlar");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.getContentPane().setBackground(new Color(176, 196, 222));
        frame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Günübirlik Turlar", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.BLACK); 
        frame.add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(176, 196, 222));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setLayout(new GridLayout(0, 1, 10, 10));

        List<TurBilgisi> turlar = TurDosyaOkuyucu.turlariOku("yurtici.txt", true); // true parametresi günübirlik turları filtreler
        if (turlar.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Günübirlik turlar yüklenemedi!", "Hata", JOptionPane.ERROR_MESSAGE);
        } else {
            for (TurBilgisi tur : turlar) { // her tur için işlem döngüsü
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