package Bank.Sampah.Bumi.Inspirasi.Model.Repo;

import Bank.Sampah.Bumi.Inspirasi.Model.Entity.HistoryPenarikan;
import Bank.Sampah.Bumi.Inspirasi.Model.Entity.Sampah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryPenarikanRepo extends JpaRepository<HistoryPenarikan, Integer> {


}
