import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TurDetay {
    private JFrame frame;
    private TurBilgisi tur;
    private String kullaniciEmail;
    private Image backgroundImage;

    public TurDetay(TurBilgisi tur, String kullaniciEmail) {
        this.tur = tur;
        this.kullaniciEmail = kullaniciEmail;

        backgroundImage = new ImageIcon(getClass().getResource("/resimler/background2.png")).getImage();

        frame = new JFrame("Tur Detayları");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel backgroundPanel = new JPanel() {
            @Override // override edilerek arka plan resmi çizilir
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // resim panelin boyutlarına göre ölçeklendirilyo
                }
            }
        };
        backgroundPanel.setLayout(new BorderLayout(10, 10));
        frame.setContentPane(backgroundPanel);

        JLabel title = new JLabel("Tur Detayları", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.BLACK);
        backgroundPanel.add(title, BorderLayout.NORTH);

        JTextArea detayArea = new JTextArea();
        detayArea.setText("Tur Adı: " + tur.getAd() + "\n" + 
                          "Fiyat: " + tur.getFiyat() + " " + (tur instanceof YurtIciTur ? "TL" : "Euro") + "\n" +
                          "Süre: " + tur.getSure() + " Gün\n" +
                          "İçerik:\n" + tur.getIcerik());
        detayArea.setEditable(false); // Kullanıcı metni düzenleyemez
        detayArea.setLineWrap(true); // metin satır sonlarında otomatik olarak kırılır 
        detayArea.setWrapStyleWord(true); // kelimeler bölünmez
        detayArea.setFont(new Font("Segoe UI", Font.BOLD, 14));
        detayArea.setForeground(Color.BLACK);
        detayArea.setOpaque(false); // arka plan şeffaf yapılır (background görülsün diye)

        JScrollPane scrollPane = new JScrollPane(detayArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);

        JButton devamButton = createStyledButton("Devam Et");
        backgroundPanel.add(devamButton, BorderLayout.SOUTH);

        devamButton.addActionListener(e -> {
            frame.dispose();
            new TarihSecim(tur, kullaniciEmail);
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
}