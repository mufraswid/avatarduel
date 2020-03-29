/*
    Kelas abstrak Kartu Skill: kelas yang memodelkan konsep kartu skill, merupakan subclass dari kelas Kartu, menurunkan 
    jenis KartuSkillAura, KartuSkillDestroy, dan KartuSkillPowerUp
*/

abstract class KartuSkill extends Kartu {
    // Atribut dari KartuSkill
    private int power;
    private KartuKarakter target;

    // Fungsi untuk mengaplikasikan efek ke kartu applyEffect() diaplikasikan
    public abstract void applyEffect();

    // setter atribut kartu skill
    public void setSkillAttr(KartuKarakter target, int power){
        this.target = target;
        this.power = power;
    }
}
