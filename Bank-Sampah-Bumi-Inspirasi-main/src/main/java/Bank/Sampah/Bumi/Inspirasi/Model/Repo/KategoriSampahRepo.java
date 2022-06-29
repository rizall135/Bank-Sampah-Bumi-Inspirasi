package Bank.Sampah.Bumi.Inspirasi.Model.Repo;

import Bank.Sampah.Bumi.Inspirasi.Model.Entity.KategoriSampah;
import Bank.Sampah.Bumi.Inspirasi.Model.Entity.Sampah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KategoriSampahRepo extends JpaRepository<KategoriSampah, Integer> {

    @Query("SELECT n FROM KategoriSampah n WHERE n.nama =?1")
    public KategoriSampah getUsername(String nama);

    @Query("SELECT u FROM KategoriSampah u WHERE u.deleted =true")
    List<KategoriSampah> kategriSampahAktif();


   /* @Query("SELECT count(u.employe_id) FROM Employee u WHERE u.deleted=true")
    public Integer getTotalEmployee();

    @Query("SELECT count(u.employe_id) FROM Employee u WHERE u.deleted=true AND u.role.nama_role='HRD'")
    public Integer getTotalHrd();

    @Query("SELECT count(u.employe_id) FROM Employee u WHERE u.deleted=true AND u.role.nama_role='KARYAWAN'")
    public Integer getTotalKaryawan();

    @Query("SELECT u FROM Employee u WHERE u.deleted = true AND u.role.nama_role='KARYAWAN' AND NOT u.employe_id =?1")
    List<Employee> getPengganti(Integer employe_id);

    @Query("SELECT u.email FROM Employee u WHERE u.deleted=true AND u.role.nama_role='HRD'")
    List<String> getHrd();*/
}
