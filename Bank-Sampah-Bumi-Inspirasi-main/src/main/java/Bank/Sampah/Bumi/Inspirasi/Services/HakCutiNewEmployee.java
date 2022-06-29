package Bank.Sampah.Bumi.Inspirasi.Services;

import Bank.Sampah.Bumi.Inspirasi.Model.Entity.Employee;
import Bank.Sampah.Bumi.Inspirasi.Model.Entity.HakCuti;
import Bank.Sampah.Bumi.Inspirasi.Model.Entity.JenisCuti;
import Bank.Sampah.Bumi.Inspirasi.Model.Repo.HakCutiRepo;
import Bank.Sampah.Bumi.Inspirasi.Model.Repo.JenisCutiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class HakCutiNewEmployee {
    @Autowired
    HakCutiRepo hakCutiRepo;

    @Autowired
    JenisCutiRepo jenisCutiRepo;

    public void Hak(Employee employee){
        // default hak cuti untuk karyawan baru
        for(int i=1;i<=2;i++){
            JenisCuti jenisTahunan = jenisCutiRepo.findById(i).get();
            HakCuti hakCuti = new HakCuti();
            hakCuti.setEmployee(employee);
            hakCuti.setJenisCuti(jenisTahunan);
            hakCuti.setDeleted(true);
            if(i==1){
                hakCuti.setSisa_cuti(12);
            }else {
                hakCuti.setSisa_cuti(0);
            }
            hakCutiRepo.save(hakCuti);
        }

    }

    @Scheduled(cron = "0 0 0 1 1 *")
    public void AwalTahun(){
        Integer sisa=0;
        System.out.println("PERUBAHAN CUTI AWAL TAHUN");
        for(int i=1; i<=hakCutiRepo.getTotalHak(); i++){
            HakCuti hakCuti =hakCutiRepo.findById(i).get();
            hakCuti.setCreatedBy(hakCuti.getCreatedBy());
            hakCuti.setCreatedDate(hakCuti.getCreatedDate());
            if(hakCuti.getJenisCuti().getJenis_cuti_id()==1){
                sisa=hakCuti.getSisa_cuti();
                hakCuti.setSisa_cuti(12);
            }else{
                hakCuti.setSisa_cuti(sisa);
            }
            hakCutiRepo.save(hakCuti);
        }
    }

    @Scheduled(cron = "0 0 0 1 6 *")
    public void TengahTahun(){
        for(int i=1; i<=hakCutiRepo.getTotalHak(); i++){
            HakCuti hakCuti =hakCutiRepo.findById(i).get();
            if(hakCuti.getJenisCuti().getJenis_cuti_id()==2){
                hakCuti.setCreatedBy(hakCuti.getCreatedBy());
                hakCuti.setCreatedDate(hakCuti.getCreatedDate());
                hakCuti.setSisa_cuti(0);
                hakCutiRepo.save(hakCuti);
            }

        }
    }

}
