import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class YurtIciSecenekleri {
    private JFrame frame;
    private String kullaniciEmail;

    public YurtIciSecenekleri(String kullaniciEmail) {
        this.kullaniciEmail = kullaniciEmail;
        frame = new JFrame("Yurt İçi Turlar");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.getContentPane().setBackground(new Color(176, 196, 222));
        frame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Yurt İçi Turlar", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.BLACK);
        frame.add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(176, 196, 222));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));

        JButton gunubirlikButton = createStyledButton("İstanbul Çıkışlı Günübirlik Turlar");
        JButton digerButton = createStyledButton("İstanbul Çıkışlı Diğer Turlar");

        buttonPanel.add(gunubirlikButton);
        buttonPanel.add(digerButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        gunubirlikButton.addActionListener(e -> {
            frame.dispose();
            new GunubirlikTurSecim(kullaniciEmail);
        });

        digerButton.addActionListener(e -> {
            frame.dispose();
            new DigerTurSecim(kullaniciEmail);
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