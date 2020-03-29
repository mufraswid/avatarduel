/*
    Kelas KartuSkillDestroy: kelas yang memodelkan konsep kartu skill tipe destroy, diturunkan dari kelas KartuSkill
*/

class KartuSkillDestroy extends KartuSkill {
    // Konstruktor
    public KartuSkillDestroy(String nama, String desc, String elemen, KartuKarakter target, int power){
        super.setKartuAttr(nama, desc, elemen);
        super.setSkillAttr(target, power);
    }

    // override applyEffect() dari KartuSkill: menghancurkan target
    public void applyEffect(){
        // implementasi
    }
}