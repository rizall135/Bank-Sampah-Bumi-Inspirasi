package Bank.Sampah.Bumi.Inspirasi.Model.Entity;

import Bank.Sampah.Bumi.Inspirasi.Model.Common.MyAudtableBase;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_history_pemasukan")
public class HistoryPemasukan extends MyAudtableBase<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pemasukan_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_tabungan")
    private Tabungan tabungan;

    @ManyToOne
    @JoinColumn(name = "fk_sampah")
    private Sampah sampah;

    @Column(name = "jumlah")
    private Integer jumlah;

    @Column(name = "tanggal_pemasukkan")
    private Date tanggalPemasukkan;

    @Column(name = "catatan")
    private String catatan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tabungan getTabungan() {
        return tabungan;
    }

    public void setTabungan(Tabungan tabungan) {
        this.tabungan = tabungan;
    }

    public Sampah getSampah() {
        return sampah;
    }

    public void setSampah(Sampah sampah) {
        this.sampah = sampah;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Date getTanggalPemasukkan() {
        return tanggalPemasukkan;
    }

    public void setTanggalPemasukkan(Date tanggalPemasukkan) {
        this.tanggalPemasukkan = tanggalPemasukkan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
