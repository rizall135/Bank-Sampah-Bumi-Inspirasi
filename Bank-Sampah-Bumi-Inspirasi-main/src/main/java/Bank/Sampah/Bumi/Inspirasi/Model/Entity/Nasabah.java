package Bank.Sampah.Bumi.Inspirasi.Model.Entity;


import Bank.Sampah.Bumi.Inspirasi.Model.Common.MyAudtableBase;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_nasabah")
public class Nasabah extends MyAudtableBase<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nasabah_id")
    private Integer nasabahId;

    @Column(name = "nib", length = 18, unique = true)
    private String nib;

    @Column(name = "nama_lengkap", length = 100)
    private String nama;

    @Column(name = "alamat", length = 200)
    private String alamat;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "foto")
    private String foto;

    @Column(name = "pekerjaan")
    private String pekerjaan;

    @Column(name = "transportasi")
    private String transportasi;

    @Column(name = "nama_rekening")
    private String namaRekening;

    @Column(name = "telepon", length = 13)
    private String telepon;

    @Column(name = "ttl")
    private Date ttl;

    @Column(name = "nomor_rekening", unique = true)
    private Integer nomorRekening;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public Date getTtl() {
        return ttl;
    }

    public void setTtl(Date ttl) {
        this.ttl = ttl;
    }

    public Integer getNasabahId() {
        return nasabahId;
    }

    public void setNasabahId(Integer nasabahId) {
        this.nasabahId = nasabahId;
    }

    public String getNib() {
        return nib;
    }

    public void setNib(String nib) {
        this.nib = nib;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getTransportasi() {
        return transportasi;
    }

    public void setTransportasi(String transportasi) {
        this.transportasi = transportasi;
    }

    public String getNamaRekening() {
        return namaRekening;
    }

    public void setNamaRekening(String namaRekening) {
        this.namaRekening = namaRekening;
    }

    public Integer getNomorRekening() {
        return nomorRekening;
    }

    public void setNomorRekening(Integer nomorRekening) {
        this.nomorRekening = nomorRekening;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}