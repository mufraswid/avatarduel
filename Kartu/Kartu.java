/*
    Kelas Abstrak Kartu: kelas yang memodelkan konsep kartu pada permainan, diturunkan menjadi berbagai jenis kartu: Karakter, Land, dan Skill
*/

abstract class Kartu {
    // atribut
    private String nama;
    private String desc;
    private String elemen;

    // Setter elemen
    public void setNama(String nama){
        this.nama = nama;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public void setElemen(String elemen){
        this.elemen = elemen;
    }

    // Setter semua atribut
    public void setKartuAttr(String nama, String desc, String elemen){
        setNama(nama);
        setDesc(desc);
        setElemen(elemen);
    }

    // Getter elemen
    public String getNama(){
        return this.nama;
    }

    public String getDesc(){
        return this.desc;
    }

    public String getElemen(){
        return this.elemen;
    }
}