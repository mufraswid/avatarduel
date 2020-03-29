/*
    Kelas KartuLand: kelas yang memodelkan konsep kartu land, merupakan subclass dari kelas Kartu
*/

class KartuLand extends Kartu {
    // Konstruktor
    public KartuLand(String nama, String desc, String elemen, int power){
        super.setAttr(nama, desc, elemen, power);
    }

    // Method untuk menambah power here
}
