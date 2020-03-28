/*
    Kelas KartuSkillDestroy: kelas yang memodelkan konsep kartu skill tipe destroy, diturunkan dari kelas KartuSkill
*/

class KartuSkillDestroy extends KartuSkill {
    // Konstruktor
    public KartuSkillDestroy(KartuKarakter target, int incAtk, int incDef){
        super.setTarget(target);
    }

    // override applyEffect() dari KartuSkill: menghancurkan target
    public applyEffect(){
        // implementasi
    }
}