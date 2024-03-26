package com.yoru.model.Entity;


public class Libro extends Prodotto {

    public static final String COLUMNLABEL7 = "ISBN";
    public static final String COLUMNLABEL8 = "pagine";
    public static final String COLUMNLABEL9 = "lingua";

    private int pagine;
    private String ISBN;
    private String lingua;


    public int getNumeroPagine() {
        return pagine;
    }

    public void setNumeroPagine(int pagine) {
        this.pagine = pagine;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
