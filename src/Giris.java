import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Giris {
    private static final Color BG_COLOR = new Color(176, 196, 222);
    private static final Color BUTTON_BG = new Color(135, 206, 235);
    private static final Color BUTTON_HOVER = new Color(100, 149, 237);
    private static final Color BORDER_COLOR = new Color(70, 130, 180);
    private final JFrame frame;

    public Giris() {
        // Pencere
        frame = new JFrame("gezGO Tur");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 450);
        frame.getContentPane().setBackground(BG_COLOR); // Pencerenin arka plan rengini sabit açık mavi olarak ayarladık
        frame.setLayout(new BorderLayout(10, 10));

        // Logo
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(BG_COLOR);
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/resimler/Adsız_tasarım__1_-removebg-preview.png"));
        JLabel logoLabel = new JLabel(new ImageIcon(logoIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
        logoPanel.add(logoLabel);
        frame.add(logoPanel, BorderLayout.NORTH);

        // Karşılama mesajı
        JLabel hosGeldiniz = new JLabel("gezGO Tur'a Hoş Geldiniz", SwingConstants.CENTER);
        hosGeldiniz.setFont(new Font("Segoe UI", Font.BOLD, 20));
        hosGeldiniz.setForeground(Color.BLACK);
        frame.add(hosGeldiniz, BorderLayout.CENTER);

        
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBackground(BG_COLOR);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton uyeOlButton = createStyledButton("Üye Ol");
        JButton girisYapButton = createStyledButton("Giriş Yap");
        JButton yoneticiButton = createStyledButton("Yönetici Girişi");

        buttonPanel.add(uyeOlButton);
        buttonPanel.add(girisYapButton);
        buttonPanel.add(yoneticiButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

       
        uyeOlButton.addActionListener(e -> { frame.dispose(); new UyeOl(); });
        girisYapButton.addActionListener(e -> { frame.dispose(); new KullaniciGiris(); });
        yoneticiButton.addActionListener(e -> { frame.dispose(); new YoneticiGiris(); });
        // ilgili sınıflardan yeni pencereler açar

        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(BUTTON_BG);
        button.setForeground(Color.BLACK);
        button.setBorder(new RoundedBorder(10));
        button.setFocusPainted(false);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(BUTTON_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(BUTTON_BG);
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
            g.setColor(BORDER_COLOR);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    public static void main(String[] args) {
        new Giris();
    }
}