package Bank.Sampah.Bumi.Inspirasi.Model.Repo;

import Bank.Sampah.Bumi.Inspirasi.Model.Entity.PengajuanCuti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PengajuanCutiRepo extends JpaRepository<PengajuanCuti,Integer> {
    @Query("SELECT u FROM PengajuanCuti u WHERE u.pengajuan_id=?1")
    public PengajuanCuti getPengajuan(Integer id);

    @Query("SELECT count(u.pengajuan_id) FROM PengajuanCuti u WHERE u.statusCuti.cuti_id=2")
    public Integer getCutiMasuk();
}
