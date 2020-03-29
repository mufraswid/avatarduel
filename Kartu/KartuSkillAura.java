/*
    Kelas KartuSkillAura: kelas yang memodelkan konsep kartu skill tipe Aura, diturunkan dari kelas KartuSkill
*/

class KartuSkillAura extends KartuSkill {
    // Atribut: untuk menambahkan atk dan def dari kartu target. Dapat bernilai 0 dan negatif
    private int incAtk;
    private int incDef;

    // Konstruktor
    public KartuSkillAura(String nama, String desc, String elemen, int power, KartuKarakter target, int incAtk, int incDef){
        super.setAttr(nama, desc, elemen, power);
        super.setTarget(target);
        this.incAtk = incAtk;
        this.incDef = incDef;
    }

    // override applyEffect() dari KartuSkill: menambah atk dan defense target;
    public void applyEffect(){
        // implementasi
    }
}