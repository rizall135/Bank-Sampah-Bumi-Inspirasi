package Bank.Sampah.Bumi.Inspirasi.Model.Entity;

import Bank.Sampah.Bumi.Inspirasi.Model.Common.MyAudtableBase;

import javax.persistence.*;


@Entity
@Table(name = "tbl_tabungan")
public class Tabungan extends MyAudtableBase<String> {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "tabungan_id")
        private Integer id;

        @Column(name = "saldo", unique = true)
        private Integer saldo;

        @ManyToOne
        @JoinColumn(name = "fk_nasabah")
        private Nasabah nasabah;

        @Column(name = "berat_total", unique = true)
        private Integer beratTotal;

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public Integer getSaldo() {
                return saldo;
        }

        public void setSaldo(Integer saldo) {
                this.saldo = saldo;
        }

        public Nasabah getNasabah() {
                return nasabah;
        }

        public void setNasabah(Nasabah nasabah) {
                this.nasabah = nasabah;
        }

        public Integer getBeratTotal() {
                return beratTotal;
        }

        public void setBeratTotal(Integer beratTotal) {
                this.beratTotal = beratTotal;
        }
}
