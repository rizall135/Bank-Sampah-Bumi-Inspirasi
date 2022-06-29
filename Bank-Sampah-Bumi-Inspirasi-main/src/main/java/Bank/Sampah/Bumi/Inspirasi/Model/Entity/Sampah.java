package Bank.Sampah.Bumi.Inspirasi.Model.Entity;

import Bank.Sampah.Bumi.Inspirasi.Model.Common.MyAudtableBase;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_sampah")
public class Sampah extends MyAudtableBase<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sampah_id")
    private Integer id;

    @Column(name = "code", length = 100)
    private String code;

    @Column(name = "nama_sampah", length = 100)
    private String nama;

    @Column(name = "harga", length = 200)
    private Integer harga;

    @Column(name = "total", length = 200)
    private Integer total;

    @Column(name = "foto", length = 100, unique = true)
    private String foto;

    @ManyToOne
    @JoinColumn(name = "fk_kategori_sampah")
    private KategoriSampah kategoriSampah;

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

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public KategoriSampah getKategoriSampah() {
        return kategoriSampah;
    }

    public void setKategoriSampah(KategoriSampah kategoriSampah) {
        this.kategoriSampah = kategoriSampah;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
