package Bank.Sampah.Bumi.Inspirasi.Controller;


import Bank.Sampah.Bumi.Inspirasi.Model.Entity.KategoriSampah;
import Bank.Sampah.Bumi.Inspirasi.Model.Entity.Role;
import Bank.Sampah.Bumi.Inspirasi.Model.Entity.Sampah;
import Bank.Sampah.Bumi.Inspirasi.Model.Repo.KategoriSampahRepo;
import Bank.Sampah.Bumi.Inspirasi.Model.Repo.RoleRepo;
import Bank.Sampah.Bumi.Inspirasi.Model.Repo.SampahRepo;
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
public class SampahController {
    @Autowired
    RoleRepo roleRepo;

    @Autowired
    SampahRepo sampahRepo;

    @Autowired
    KategoriSampahRepo kategoriSampahRepo;

    @PostMapping("/simpansampah")
    public String add(Sampah sampah){
        boolean cek=false;

        if(sampah.getId()!=null) {
            Sampah sampah1 = sampahRepo.findById(sampah.getId()).get();
            sampah.setCreatedDate(sampah1.getCreatedDate());
            sampah.setCreatedBy(sampah1.getCreatedBy());
        }

        sampah.setDeleted(true);
        sampahRepo.save(sampah);


        return "redirect:/pegawai/sampahadd";
    }

    @GetMapping("/sampahview")
    public String view(Model model){
        List<Sampah> sampah = sampahRepo.sampahAktif();
        model.addAttribute("sampah",sampah);
        return "sampahList";
    }

    @GetMapping("/sampahadd")
    public String preadd(Model model){
        List<KategoriSampah> kategoriSampahs = kategoriSampahRepo.findAll();
        model.addAttribute("kategoriSampahs",kategoriSampahs);
        model.addAttribute("sampah",new Sampah());
        return "sampahAdd";
    }

    @GetMapping("/sampahdelete/{id}")
    public String delete(@PathVariable("id") Integer id){
        Sampah sampah = sampahRepo.findById(id).get();
        sampah.setCreatedBy(sampah.getCreatedBy());
        sampah.setCreatedDate(sampah.getCreatedDate());
        sampah.setDeleted(false);
        sampahRepo.save(sampah);
        return "redirect:/pegawai/sampahview";
    }

    @GetMapping("/sampahedit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Sampah sampah = sampahRepo.findById(id).get();
        List<KategoriSampah> kategoriSampahs = kategoriSampahRepo.findAll();
        model.addAttribute("sampah",sampah);
        model.addAttribute("kategoriSampahs",kategoriSampahs);
        return "sampahAdd";
    }

}