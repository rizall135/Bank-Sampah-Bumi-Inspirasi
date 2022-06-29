package Bank.Sampah.Bumi.Inspirasi.Model.Repo;

import Bank.Sampah.Bumi.Inspirasi.Model.Entity.Libur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public interface LiburRepo extends JpaRepository<Libur, Integer> {
  @Query("SELECT u FROM Libur u WHERE u.deleted =true")
    List<Libur> getLibur();

  @Query("SELECT u FROM Libur u WHERE u.deleted=true AND NOT u.tanggal=?1")
  public Libur cekLibur(Date tanggal);

}
