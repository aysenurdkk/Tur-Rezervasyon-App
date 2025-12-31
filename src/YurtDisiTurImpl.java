public class YurtDisiTurImpl extends YurtDisiTur {
    public YurtDisiTurImpl(String ad, String icerik, double fiyat, int sure) {
        super(ad, icerik, fiyat, sure);
    }

    @Override
    public String getTurTipi() {
        return "Yurt Dışı";
    }
}