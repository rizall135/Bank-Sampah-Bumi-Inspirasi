package Bank.Sampah.Bumi.Inspirasi.Model.Repo;

import Bank.Sampah.Bumi.Inspirasi.Model.Entity.StatusCuti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusCutiRepo extends JpaRepository<StatusCuti, Integer> {

    @Query("SELECT u FROM StatusCuti u WHERE u.cuti_id=?1")
    public StatusCuti getStatus(Integer id);
}