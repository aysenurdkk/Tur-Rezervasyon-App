import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import com.toedter.calendar.*;
import com.toedter.calendar.IDateEvaluator;

public class TarihSecim {
    private JFrame frame;
    private TurBilgisi tur;
    private String kullaniciEmail;
    private JDateChooser dateChooser;
    private JLabel bitisTarihiLabel;

    public TarihSecim(TurBilgisi tur, String kullaniciEmail) {
        this.tur = tur;
        this.kullaniciEmail = kullaniciEmail;

        frame = new JFrame("Tarih Seçimi");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.getContentPane().setBackground(new Color(176, 196, 222));
        frame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Tarih Seçimi", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.BLACK);
        frame.add(title, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(176, 196, 222));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel turLabel = new JLabel("Tur: " + tur.getAd()); // turun adı alınır
        turLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        turLabel.setForeground(Color.BLACK);
        mainPanel.add(turLabel);
        
        // tarih seçici
        dateChooser = new JDateChooser();
        dateChooser.setFont(new Font("Segoe UI", Font.BOLD, 14));
        dateChooser.setBackground(new Color(176, 196, 222));
        dateChooser.setForeground(Color.BLACK);
        dateChooser.setDateFormatString("d MMMM yyyy");
        
        // tarih aralığı belirttim 
        Calendar minDate = Calendar.getInstance();
        minDate.set(2025, Calendar.MAY, 22);
        dateChooser.setMinSelectableDate(minDate.getTime());

        Calendar maxDate = Calendar.getInstance();
        maxDate.set(2026, Calendar.DECEMBER, 31);
        dateChooser.setMaxSelectableDate(maxDate.getTime());

        JCalendar jCalendar = dateChooser.getJCalendar(); // JCalendar alınır.
        jCalendar.getDayChooser().addDateEvaluator(new CustomDateEvaluator(tur)); // tarih geçerli mi kontrolü

        dateChooser.getDateEditor().addPropertyChangeListener("date", evt -> { // date = olay dineleyicisi
            Date selectedDate = dateChooser.getDate();
            if (selectedDate != null && isValidDate(selectedDate)) { // seçilen tarih geçerliyse
                SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
                String bitisTarihi = hesaplaBitisTarihi(sdf.format(selectedDate), tur.getSure());
                bitisTarihiLabel.setText("Bitiş Tarihi: " + bitisTarihi);
            } else {
                dateChooser.setDate(null);
                bitisTarihiLabel.setText("Bitiş Tarihi: Geçersiz tarih, lütfen uygun bir tarih seçin");
            }
        });

        mainPanel.add(dateChooser);

        bitisTarihiLabel = new JLabel("Bitiş Tarihi: Lütfen bir tarih seçin"); // tarih seçiminden önceki mesaj
        bitisTarihiLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bitisTarihiLabel.setForeground(Color.BLACK);
        mainPanel.add(bitisTarihiLabel);

        JButton devamButton = createStyledButton("Devam Et");
        mainPanel.add(devamButton);

        frame.add(mainPanel, BorderLayout.CENTER);

        devamButton.addActionListener(e -> {
            Date selectedDate = dateChooser.getDate();
            if (selectedDate == null || !isValidDate(selectedDate)) {
                JOptionPane.showMessageDialog(frame, "Lütfen geçerli bir tarih seçin!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
            frame.dispose();
            new KisiSayisiSecim(tur, sdf.format(selectedDate), kullaniciEmail);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private boolean isValidDate(Date date) {
        if (date == null) return false;

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        Calendar minDate = Calendar.getInstance();
        minDate.set(2025, Calendar.MAY, 22);

        Calendar maxDate = Calendar.getInstance();
        maxDate.set(2026, Calendar.DECEMBER, 31);

        int day = cal.get(Calendar.DAY_OF_MONTH);

        if (cal.before(minDate) || cal.after(maxDate)) return false;

        boolean gunubirlik = tur instanceof GunubirlikTur;

        return gunubirlik ? day % 2 == 0 : (day == 1 || day == 8 || day == 15 || day == 22 || day == 30);
    }

    private String hesaplaBitisTarihi(String baslangicTarihi, int sure) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
            Date baslangic = sdf.parse(baslangicTarihi);
            Calendar cal = Calendar.getInstance();
            cal.setTime(baslangic);
            cal.add(Calendar.DAY_OF_MONTH, sure - 1);
            return sdf.format(cal.getTime());
        } catch (Exception e) {
            return "Hesaplanamadı";
        }
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

    private class CustomDateEvaluator implements IDateEvaluator {
        private TurBilgisi tur;

        public CustomDateEvaluator(TurBilgisi tur) {
            this.tur = tur;
        }

        @Override
        public boolean isSpecial(Date date) {
            return false;
        }

        @Override
        public Color getSpecialForegroundColor() {
            return null;
        }

        @Override
        public Color getSpecialBackroundColor() {
            return null;
        }

        @Override
        public String getSpecialTooltip() {
            return null;
        }

        @Override
        public boolean isInvalid(Date date) { // tarihin geçersiz olup olmadığının kontrolü
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            Calendar min = Calendar.getInstance();
            min.set(2025, Calendar.MAY, 22);
            Calendar max = Calendar.getInstance();
            max.set(2026, Calendar.DECEMBER, 31);

            if (cal.before(min) || cal.after(max)) return true;

            int day = cal.get(Calendar.DAY_OF_MONTH);
            boolean gunubirlik = tur instanceof GunubirlikTur;

            return gunubirlik ? (day % 2 != 0) : !(day == 1 || day == 8 || day == 15 || day == 22 || day == 30);
        }

        @Override
        public Color getInvalidForegroundColor() { // Geçersiz tarihler gri yazı rengi  
            return Color.GRAY;
        }

        @Override
        public Color getInvalidBackroundColor() { // Geçersiz tarihler açık gri arka planla gösterilir;
            return new Color(240, 240, 240);
        }

        @Override
        public String getInvalidTooltip() { 
            return "Bu tarih seçilemez";
        }
    }
}