package Bank.Sampah.Bumi.Inspirasi.Controller;

import Bank.Sampah.Bumi.Inspirasi.Model.Entity.Nasabah;
import Bank.Sampah.Bumi.Inspirasi.Model.Repo.NasabahRepo;
import Bank.Sampah.Bumi.Inspirasi.Services.Email.SendEmailNasabah;
import Bank.Sampah.Bumi.Inspirasi.Utils.RandomPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nasabah")
public class NasabahController {
    @Autowired
    NasabahRepo nasabahRepo;

    @Autowired
    RandomPassword password;

    @Autowired
    SendEmailNasabah kirim;

    @PostMapping("/simpanNasabah")
    public String simpanNasabah(Nasabah nasabah){
        boolean cek=false;

        String email = nasabah.getEmail();
        String nama = nasabah.getNama();
        String nib = nasabah.getNib();
        String namaRekening = nasabah.getNamaRekening();
        Integer nomorRekening = Integer.valueOf(nasabah.getNomorRekening());

        if(nasabah.getNasabahId()!=null){
            Nasabah nasabah1 = nasabahRepo.findById(nasabah.getNasabahId()).get();
            nasabah.setCreatedDate(nasabah1.getCreatedDate());
            nasabah.setCreatedBy(nasabah1.getCreatedBy());
            nasabah.setUsername(nasabah1.getUsername());
            nasabah.setPassword(nasabah1.getPassword());
        }else{
            cek=true;
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String kode = password.randomPassword();
            String hasil =encoder.encode(kode);
            nasabah.setUsername(nama);
            nasabah.setPassword(hasil);
            kirim.EmailRecrutment(email,nama,kode,namaRekening,nomorRekening,nib);

        }
        nasabah.setDeleted(true);
        nasabah.setRole(nasabah.getRole());
        nasabahRepo.save(nasabah);

      /*  if(cek==true){
            newNasabah.Hak(employee);
        }*/
        return "redirect:/HRD/karyawan";
    }




}
