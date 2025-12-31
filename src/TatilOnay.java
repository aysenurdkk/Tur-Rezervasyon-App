import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TatilOnay {
    private JFrame frame;
    private TurBilgisi tur;
    private String secilenTarih;
    private int kisiSayisi;
    private String kullaniciEmail;

    public TatilOnay(TurBilgisi tur, String secilenTarih, int kisiSayisi, String kullaniciEmail) {
        this.tur = tur;
        this.secilenTarih = secilenTarih;
        this.kisiSayisi = kisiSayisi;
        this.kullaniciEmail = kullaniciEmail;
        frame = new JFrame("Tatil Onayı");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);  
        frame.getContentPane().setBackground(new Color(176, 196, 222));
        frame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Tatil Onayı", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.BLACK);
        frame.add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(176, 196, 222));
        centerPanel.setLayout(new BorderLayout());
        
        // onay mesajı
        JLabel mesaj = new JLabel("<html>Tatiliniz onaylandı!<br>En kısa sürede sizinle iletişime geçeceğiz.</html>", SwingConstants.CENTER);
        mesaj.setFont(new Font("Segoe UI", Font.BOLD, 14));
        mesaj.setForeground(Color.BLACK);
        mesaj.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(mesaj, BorderLayout.NORTH);

        frame.add(centerPanel, BorderLayout.CENTER); // merkez panel

        JButton devamButton = createStyledButton("Devam Et");
        frame.add(devamButton, BorderLayout.SOUTH);

        devamButton.addActionListener(e -> {
            frame.dispose();
            new GorusOneri(tur, secilenTarih, kisiSayisi, kullaniciEmail);
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