# Tur Rezervasyon Sistemi 🏖️
Bu uygulama, kullanıcıların tur rezervasyonları yapabilmesini sağlayan bir Java Swing masaüstü uygulamasıdır. "gezGO Tur" adlı bu sistem, hem yurt içi hem de yurt dışı tur seçeneklerini sunar.

## Özellikler

- Kullanıcı kaydı ve girişi
- Yönetici girişi
- Yurt içi ve yurt dışı tur seçenekleri
- Günübirlik turlar
- Diğer tur türleri
- Tarih seçimi (takvim arayüzü)
- Katılımcı bilgileri girişi
- Kişi sayısı seçimi
- Ödeme işlemleri
- Kullanıcı hesabı yönetimi
- Geri bildirim ve öneri gönderme

## Kurulum

1. Java JDK 8 veya üzeri yüklü olmalıdır
2. Gerekli kütüphaneler: jcalendar-1.4.jar, jgoodies-common-1.2.0.jar, jgoodies-looks-2.4.1.jar
3. Projeyi derlemek için: `javac -cp "src/lib/*:." -d bin src/*.java`
4. Uygulamayı çalıştırmak için: `java -cp "bin:src/lib/*" Giris`

## Kullanım

Uygulama açıldığında ana ekranda aşağıdaki seçenekler yer alır:

- **Üye Ol**: Yeni kullanıcı kaydı
- **Giriş Yap**: Mevcut kullanıcı ile giriş
- **Yönetici Girişi**: Yönetici paneline erişim

Kullanıcı girişi yaptıktan sonra yurt içi veya yurt dışı turlar arasından seçim yapabilir, tarih belirleyebilir, kişi sayısını seçebilir ve rezervasyon işlemini tamamlayabilir.

## Dosya Yapısı

- Ana sınıf: `Giris.java`
- Tur bilgileri: `TurBilgisi.java`
- Veri okuma: `TurDosyaOkuyucu.java`
- Kullanıcı işlemleri: `KullaniciGiris.java`, `KullaniciKayit.java`
- Tur seçimleri: `TatilSecenekleri.java`, `YurtIciSecenekleri.java`, `YurtDisiSecenekleri.java`
- Tarih seçimi: `TarihSecim.java`
- Katılımcı bilgileri: `KatilimciBilgileri.java`
- Ödeme işlemleri: `Odeme.java`, `OdemeKayit.java`
- Hesap yönetimi: `Hesabim.java`

## Veri Depolama

Uygulama şu anda aşağıdaki metin dosyalarında veri saklamaktadır:

- `kullanicilar.txt` - Kullanıcı bilgileri
- `katilimcilar.txt` - Katılımcı bilgileri
- `odemeler.txt` - Ödeme kayıtları
- `gorusler.txt` - Kullanıcı görüş ve önerileri
- `yurtici.txt` - Yurt içi tur bilgileri
- `yurtdisi.txt` - Yurt dışı tur bilgileri

## Teknolojiler 🚀
- Java Swing (GUI)
- JCalendar (tarih seçimi)
- JGoodies (GUI stilleri)
- Standart Java IO (dosya işlemleri)
