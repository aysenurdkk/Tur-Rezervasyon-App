public class GunubirlikTur extends YurtIciTur {
    public GunubirlikTur(String ad, String icerik, double fiyat) {
        super(ad, icerik, fiyat, 1);
    }

    @Override
    public String getTurTipi() {
        return "Günübirlik";
    } // YurtIciTur da tanımlı bir metodu geçersiz kıldık
}