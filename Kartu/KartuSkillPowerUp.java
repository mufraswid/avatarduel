/*
    Kelas KartuSkillPowerUp: kelas yang memodelkan konsep kartu skill tipe power up, diturunkan dari kelas KartuSkill
*/

class KartuSkillPowerUp extends KartuSkill {

    // Konstruktor
    public KartuSkillPowerUp(String nama, String desc, String elemen, int power, KartuKarakter target){
        super.setAttr(nama, desc, elemen, power);
        super.setTarget(target);
    }

    // override applyEffect() dari KartuSkill: mengurangi HP lawan jika target menyerang kartu lawan seakan kartu lawan sedang menyerang
    public void applyEffect(){
        // implementasi
    }
}