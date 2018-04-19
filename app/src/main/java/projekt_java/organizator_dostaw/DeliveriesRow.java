package projekt_java.organizator_dostaw;


public class DeliveriesRow {

    private String skad;
    private String nazwa;
    private String ilosc;
    private String kiedy_mozna;
    private String status;

    public String getSkad() {
        return skad;
    }

    public void setSkad(String skad) {
        this.skad = skad;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getIlosc() {
        return ilosc;
    }

    public void setIlosc(String ilosc) {
        this.ilosc = ilosc;
    }

    public String getKiedy_mozna() {
        return kiedy_mozna;
    }

    public void setKiedy_mozna(String kiedy_mozna) {
        this.kiedy_mozna = kiedy_mozna;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}