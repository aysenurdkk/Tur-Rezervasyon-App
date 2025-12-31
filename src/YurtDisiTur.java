public abstract class YurtDisiTur implements TurBilgisi {
    protected String ad;
    protected String icerik;
    protected double fiyat;
    protected int sure;

    public YurtDisiTur(String ad, String icerik, double fiyat, int sure) {
        this.ad = ad;
        this.icerik = icerik;
        this.fiyat = fiyat;
        this.sure = sure;
    }

    @Override
    public String getAd() {
        return ad;
    }

    @Override
    public String getIcerik() {
        return icerik;
    }

    @Override
    public double getFiyat() {
        return fiyat;
    }

    @Override
    public int getSure() {
        return sure;
    }

    public abstract String getTurTipi();
}