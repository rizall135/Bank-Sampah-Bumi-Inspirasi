
package Bank.Sampah.Bumi.Inspirasi.Model.Repo;

import Bank.Sampah.Bumi.Inspirasi.Model.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    @Query("SELECT u FROM Employee u WHERE u.username =?1")
    public Employee getUsername(String username);

    @Query("SELECT u FROM Employee u WHERE u.role.nama_role='HRD' AND u.username=?1")
    public Employee getHrd(String username);

    @Query("SELECT u FROM Employee u WHERE u.deleted =true")
    List<Employee> employeeAktif();

    @Query("SELECT count(u.employe_id) FROM Employee u WHERE u.deleted=true")
    public Integer getTotalEmployee();

    @Query("SELECT count(u.employe_id) FROM Employee u WHERE u.deleted=true AND u.role.nama_role='HRD'")
    public Integer getTotalHrd();

    @Query("SELECT count(u.employe_id) FROM Employee u WHERE u.deleted=true AND u.role.nama_role='KARYAWAN'")
    public Integer getTotalKaryawan();

    @Query("SELECT u FROM Employee u WHERE u.deleted = true AND u.role.nama_role='KARYAWAN' AND NOT u.employe_id =?1")
    List<Employee> getPengganti(Integer employe_id);

    @Query("SELECT u.email FROM Employee u WHERE u.deleted=true AND u.role.nama_role='HRD'")
    List<String> getHrd();
}