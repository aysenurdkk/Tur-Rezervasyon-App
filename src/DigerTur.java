public class DigerTur extends YurtIciTur {
    public DigerTur(String ad, String icerik, double fiyat, int sure) {
        super(ad, icerik, fiyat, sure);
        // YurtIciTurve sınıfındaki alanlar  bu parametrelerle başlatılır.
    }

    @Override
    public String getTurTipi() {
        return "Diğer";
    } // YurtIciTur da tanımlı bir metodu geçersiz kılar
}