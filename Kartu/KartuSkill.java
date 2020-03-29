/*
    Kelas abstrak Kartu Skill: kelas yang memodelkan konsep kartu skill, merupakan subclass dari kelas Kartu, menurunkan 
    jenis KartuSkillAura, KartuSkillDestroy, dan KartuSkillPowerUp
*/

abstract class KartuSkill extends Kartu {
    // Atribut dari KartuSkill
    KartuKarakter target;

    // Fungsi untuk mengaplikasikan efek ke kartu applyEffect() diaplikasikan
    public abstract void applyEffect();

    // setter target kartu
    public void setTarget(KartuKarakter target){
        this.target = target;
    }
}
