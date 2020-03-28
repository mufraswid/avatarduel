/*
    Kelas KartuSkillPowerUp: kelas yang memodelkan konsep kartu skill tipe power up, diturunkan dari kelas KartuSkill
*/

class KartuSkillPowerUp extends KartuSkill {

    // Konstruktor
    public KartuSkillPowerUp(KartuKarakter target, int incAtk, int incDef){
        super.setTarget(target);
    }

    // override applyEffect() dari KartuSkill: mengurangi HP lawan jika target menyerang kartu lawan seakan kartu lawan sedang menyerang
    public applyEffect(){
        // implementasi
    }
}