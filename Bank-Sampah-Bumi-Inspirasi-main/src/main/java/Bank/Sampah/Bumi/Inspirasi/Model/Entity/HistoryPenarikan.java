package Bank.Sampah.Bumi.Inspirasi.Model.Entity;

import Bank.Sampah.Bumi.Inspirasi.Model.Common.MyAudtableBase;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_history_penarikan")
public class HistoryPenarikan extends MyAudtableBase<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "penarikan_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_tabungan")
    private Tabungan tabungan;

    @ManyToOne
    @JoinColumn(name = "fk_sampah")
    private Sampah sampah;

    @Column(name = "jumlah")
    private Integer jumlah;

    @Column(name = "jumlahHarga")
    private Integer jumlahHarga;

    @Column(name = "tanggal_penarikan")
    private Date tanggalPenarikan;

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

    public Integer getJumlahHarga() {
        return jumlahHarga;
    }

    public void setJumlahHarga(Integer jumlahHarga) {
        this.jumlahHarga = jumlahHarga;
    }

    public Date getTanggalPenarikan() {
        return tanggalPenarikan;
    }

    public void setTanggalPenarikan(Date tanggalPenarikan) {
        this.tanggalPenarikan = tanggalPenarikan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
