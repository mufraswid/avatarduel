/*
    Kelas KartuSkillDestroy: kelas yang memodelkan konsep kartu skill tipe destroy, diturunkan dari kelas KartuSkill
*/

class KartuSkillDestroy extends KartuSkill {
    // Konstruktor
    public KartuSkillDestroy(String nama, String desc, String elemen, int power, KartuKarakter target){
        super.setAttr(nama, desc, elemen, power);
        super.setTarget(target);
    }

    // override applyEffect() dari KartuSkill: menghancurkan target
    public void applyEffect(){
        // implementasi
    }
}