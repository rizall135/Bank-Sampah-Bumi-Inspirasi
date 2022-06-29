package Bank.Sampah.Bumi.Inspirasi.Model.Repo;

import Bank.Sampah.Bumi.Inspirasi.Model.Entity.JenisCuti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JenisCutiRepo extends JpaRepository<JenisCuti, Integer> {
    @Query("SELECT u FROM JenisCuti u WHERE u.jenis_cuti_id=?1")
    public JenisCuti getJenisCuti(Integer id);
}
