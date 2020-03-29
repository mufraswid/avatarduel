/*
    Kelas Kartu Karakter: kelas yang memodelkan konsep kartu karakter, merupakan subclass dari kelas Kartu
*/

class KartuKarakter extends Kartu {
    // Atribut dari KartuKarakter: atk untuk attack dan def untuk defense dari kartu
    private int atk;
    private int def;

    // Konstruktor
    public KartuKarakter(String nama, String desc, String elemen, int power, int atk, int def){
        super.setAttr(nama, desc, elemen, power);
        this.atk = atk;
        this.def = def;
    }

    // Getter elemen
    public int getAtk(){
        return this.atk;
    }

    public int getDef(){
        return this.def;
    }
}
