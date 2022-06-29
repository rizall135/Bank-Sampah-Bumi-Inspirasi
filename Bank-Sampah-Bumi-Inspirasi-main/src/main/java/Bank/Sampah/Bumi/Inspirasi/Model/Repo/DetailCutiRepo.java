package Bank.Sampah.Bumi.Inspirasi.Model.Repo;

import Bank.Sampah.Bumi.Inspirasi.Model.Entity.DetailCuti;
import Bank.Sampah.Bumi.Inspirasi.Model.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailCutiRepo extends JpaRepository<DetailCuti, Integer> {
    @Query("SELECT u FROM DetailCuti u WHERE u.deleted=true AND u.pengajuanCuti.employee=?1")
    public List<DetailCuti> getDetailCuti(Employee employee);

    @Query("SELECT u FROM DetailCuti u WHERE u.deleted=true AND u.pengajuanCuti.pengajuan_id=?1")
    public DetailCuti getDetailId(Integer id);
}
