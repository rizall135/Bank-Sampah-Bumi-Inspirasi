package Bank.Sampah.Bumi.Inspirasi.Model.Entity;

import Bank.Sampah.Bumi.Inspirasi.Model.Common.MyAudtableBase;

import javax.persistence.*;

@Entity
@Table(name = "tbl_kategori_sampah")
public class KategoriSampah extends MyAudtableBase<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kategori_sampah_id")
    private Integer id;

    @Column(name = "code", length = 100)
    private String code;

    @Column(name = "nama_kategori_sampah", length = 100)
    private String nama;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
