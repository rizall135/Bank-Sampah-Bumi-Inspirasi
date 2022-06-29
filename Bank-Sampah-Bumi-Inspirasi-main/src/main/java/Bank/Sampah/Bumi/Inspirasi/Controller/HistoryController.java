package Bank.Sampah.Bumi.Inspirasi.Controller;


import Bank.Sampah.Bumi.Inspirasi.Model.Entity.HistoryPenarikan;
import Bank.Sampah.Bumi.Inspirasi.Model.Entity.KategoriSampah;
import Bank.Sampah.Bumi.Inspirasi.Model.Entity.Sampah;
import Bank.Sampah.Bumi.Inspirasi.Model.Repo.HistoryPenarikanRepo;
import Bank.Sampah.Bumi.Inspirasi.Model.Repo.KategoriSampahRepo;
import Bank.Sampah.Bumi.Inspirasi.Model.Repo.RoleRepo;
import jdk.internal.org.jline.reader.History;
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
public class HistoryController {

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    HistoryPenarikanRepo historyPenarikanRepo;

    @Autowired
    KategoriSampahRepo kategoriSampahRepo;

    @PostMapping("/historypenarikansave")
    public String save(HistoryPenarikan historyPenarikan){
        boolean cek=false;

        if(historyPenarikan.getId()!=null) {
            HistoryPenarikan historyPenarikan1 = historyPenarikanRepo.findById(historyPenarikan.getId()).get();
            historyPenarikan.setCreatedDate(historyPenarikan1.getCreatedDate());
            historyPenarikan.setCreatedBy(historyPenarikan1.getCreatedBy());
        }

        historyPenarikan.setDeleted(true);
        historyPenarikanRepo.save(historyPenarikan);


        return "redirect:/pegawai/historypenarikanadd";
    }

    @GetMapping("/historypenarikanview")
    public String view(Model model){
        List<HistoryPenarikan> historyPenarikan = historyPenarikanRepo.findAll();
        model.addAttribute("historyPenarikan",historyPenarikan);
        return "historyPenarikanList";
    }

    @GetMapping("/historypenarikanadd")
    public String add(Model model){
        List<HistoryPenarikan> historyPenarikan = historyPenarikanRepo.findAll();
        model.addAttribute("kategoriSampahs",historyPenarikan);
        model.addAttribute("sampah",new HistoryPenarikan());
        return "historyPenarikanAdd";
    }

    @GetMapping("/sampahedit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        HistoryPenarikan historyPenarikan = historyPenarikanRepo.findById(id).get();
        List<HistoryPenarikan> historyPenarikans = historyPenarikanRepo.findAll();
        model.addAttribute("historyPenarikan",historyPenarikan);
        model.addAttribute("historyPenarikans",historyPenarikans);
        return "historyPenarikanAdd";
    }

}
