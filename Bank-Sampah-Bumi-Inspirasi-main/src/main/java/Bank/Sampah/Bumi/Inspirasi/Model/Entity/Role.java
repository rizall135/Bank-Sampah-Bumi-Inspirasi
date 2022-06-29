package Bank.Sampah.Bumi.Inspirasi.Model.Entity;

import Bank.Sampah.Bumi.Inspirasi.Model.Common.MyAudtableBase;

import javax.persistence.*;

@Entity
@Table(name = "tbl_role")
public class Role extends MyAudtableBase<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer role_id;

    @Column(name = "nama")
    private String nama_role;

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getNama_role() {
        return nama_role;
    }

    public void setNama_role(String nama_role) {
        this.nama_role = nama_role;
    }
}
