package Bank.Sampah.Bumi.Inspirasi.Model.Repo;

import Bank.Sampah.Bumi.Inspirasi.Model.Entity.Employee;
import Bank.Sampah.Bumi.Inspirasi.Model.Entity.HakCuti;
import Bank.Sampah.Bumi.Inspirasi.Model.Entity.JenisCuti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HakCutiRepo extends JpaRepository<HakCuti,Integer> {
    @Query("SELECT u.sisa_cuti FROM HakCuti u WHERE u.employee =?1 AND u.jenisCuti =?2")
    public Integer sisaCuti(Employee employee, JenisCuti jenisCuti);

    @Query("SELECT u.jenisCuti FROM HakCuti u WHERE NOT u.sisa_cuti = 0 AND u.employee=?1")
    List<JenisCuti> getJenisCuti(Employee employee);

    @Query("SELECT u FROM HakCuti u WHERE u.deleted=true AND u.employee.employe_id=?1 AND u.jenisCuti.jenis_cuti_id=?2")
    public HakCuti getHak(Integer employee_id,Integer jenis_id);

    @Query("SELECT count(u.hak_cuti_id) FROM HakCuti u")
    public Integer getTotalHak();

    @Query("SELECT u FROM HakCuti u WHERE u.employee.employe_id=?1 AND u.jenisCuti.jenis_cuti_id=?2")
    public HakCuti getHakCuti(Integer id, Integer jenis_id);
}
