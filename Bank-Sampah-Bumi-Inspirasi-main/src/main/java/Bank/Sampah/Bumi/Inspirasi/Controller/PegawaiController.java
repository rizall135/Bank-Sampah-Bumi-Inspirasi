package Bank.Sampah.Bumi.Inspirasi.Controller;

import Bank.Sampah.Bumi.Inspirasi.Model.Entity.Nasabah;
import Bank.Sampah.Bumi.Inspirasi.Model.Entity.Role;
import Bank.Sampah.Bumi.Inspirasi.Model.Repo.NasabahRepo;
import Bank.Sampah.Bumi.Inspirasi.Model.Repo.RoleRepo;
import Bank.Sampah.Bumi.Inspirasi.Services.Email.SendEmailNasabah;
import Bank.Sampah.Bumi.Inspirasi.Utils.RandomPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pegawai")
public class PegawaiController {

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    NasabahRepo nasabahRepo;

    @Autowired
    RandomPassword password;

    @Autowired
    SendEmailNasabah kirim;

    @PostMapping("/simpannasabah")
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

        }
        nasabah.setDeleted(true);
        nasabah.setRole(nasabah.getRole());
        nasabahRepo.save(nasabah);

      /*  if(cek==true){
            newNasabah.Hak(employee);
        }*/
        return "redirect:/pegawai/nasabahadd";
    }

    @GetMapping("/nasabahview")
    public String Employee(Model model){
        List<Nasabah> nasabah = nasabahRepo.nasabahAktif();
        model.addAttribute("nasabah",nasabah);
        return "nasabahList";
    }

    @GetMapping("/nasabahadd")
    public String Recruitmen(Model model){
        List<Role> roles = roleRepo.findAll();
        model.addAttribute("roles",roles);
        model.addAttribute("nasabah",new Nasabah());
        return "nasabahAdd";
    }

    @GetMapping("/nasabahdelete/{id}")
    public String Hapus(@PathVariable("id") Integer id){
        Nasabah nasabah = nasabahRepo.findById(id).get();
        nasabah.setCreatedBy(nasabah.getCreatedBy());
        nasabah.setCreatedDate(nasabah.getCreatedDate());
        nasabah.setDeleted(false);
        nasabahRepo.save(nasabah);
        return "redirect:/pegawai/nasabahview";
    }

    @GetMapping("/nasabahedit/{id}")
    public String Edit(@PathVariable("id") Integer id, Model model){
        Nasabah nasabah = nasabahRepo.findById(id).get();
        List<Role> roles = roleRepo.findAll();
        model.addAttribute("nasabah",nasabah);
        model.addAttribute("roles",roles);
        return "nasabahAdd";
    }
}
