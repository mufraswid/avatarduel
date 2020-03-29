/*
    Kelas KartuLand: kelas yang memodelkan konsep kartu land, merupakan subclass dari kelas Kartu
*/

class KartuLand extends Kartu {
    // Konstruktor
    public KartuLand(String nama, String desc, String elemen){
        super.setKartuAttr(nama, desc, elemen);
    }

    // Method untuk menambah power here
}
