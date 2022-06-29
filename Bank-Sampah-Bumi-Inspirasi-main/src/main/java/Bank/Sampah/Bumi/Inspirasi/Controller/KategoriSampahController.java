package Bank.Sampah.Bumi.Inspirasi.Controller;


import Bank.Sampah.Bumi.Inspirasi.Model.Entity.KategoriSampah;
import Bank.Sampah.Bumi.Inspirasi.Model.Entity.Role;
import Bank.Sampah.Bumi.Inspirasi.Model.Repo.KategoriSampahRepo;
import Bank.Sampah.Bumi.Inspirasi.Model.Repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pegawai")
public class KategoriSampahController {
    @Autowired
    RoleRepo roleRepo;

    @Autowired
    KategoriSampahRepo kategoriSampahRepo;

    @PostMapping("/kategorisampahsimpan")
    public String simpanSampah(KategoriSampah kategoriSampah){

        if(kategoriSampah.getId()!=null) {
            KategoriSampah kategoriSampah1 = kategoriSampahRepo.findById(kategoriSampah.getId()).get();
            kategoriSampah.setCreatedDate(kategoriSampah1.getCreatedDate());
            kategoriSampah.setCreatedBy(kategoriSampah1.getCreatedBy());
        }
        kategoriSampah.setDeleted(true);
        kategoriSampahRepo.save(kategoriSampah);


        return "redirect:/pegawai/kategorisampahadd";
    }

    @GetMapping("/kategorisampahview")
    public String Employee(Model model){
        List<KategoriSampah> kategoriSampah = kategoriSampahRepo.kategriSampahAktif();
        model.addAttribute("kategoriSampah",kategoriSampah);
        return "kategoriSampahList";
    }

    @GetMapping("/kategorisampahadd")
    public String Recruitmen(Model model){
        List<Role> roles = roleRepo.findAll();
        model.addAttribute("roles",roles);
        model.addAttribute("kategoriSampah",new KategoriSampah());
        return "kategoriSampahAdd";
    }

    @GetMapping("/kategorisampahdelete/{id}")
    public String Hapus(@PathVariable("id") Integer id){
        KategoriSampah kategoriSampah = kategoriSampahRepo.findById(id).get();
        kategoriSampah.setCreatedBy(kategoriSampah.getCreatedBy());
        kategoriSampah.setCreatedDate(kategoriSampah.getCreatedDate());
        kategoriSampah.setDeleted(false);
        kategoriSampahRepo.save(kategoriSampah);
        return "redirect:/pegawai/kategorisampahview";
    }

    @GetMapping("/kategorisampahedit/{id}")
    public String Edit(@PathVariable("id") Integer id, Model model){
        KategoriSampah kategoriSampah = kategoriSampahRepo.findById(id).get();
        List<Role> roles = roleRepo.findAll();
        model.addAttribute("kategoriSampah",kategoriSampah);
        model.addAttribute("roles",roles);
        return "kategoriSampahAdd";
    }

}