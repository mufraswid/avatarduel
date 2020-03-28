/*
    Kelas Kartu: kelas yang memodelkan konsep kartu pada permainan
*/

class Kartu {
    // atribut
    private String nama;
    private String desc;
    private String elemen;
    private int power;

    // Konstruktor
    public Kartu(String nama, String desc, String elemen, int power){
        this.nama = nama;
        this.desc = desc;
        this.elemen = elemen;
        this.power = power;
    }

    // Getter elemen
    public String getNama(){
        return this.nama;
    }

    public String getDesc(){
        return this.desc;
    }

    public String getElemen(){
        return this.elemen;
    }

    public String getPower(){
        return this.power;
    }
}