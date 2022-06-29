package Bank.Sampah.Bumi.Inspirasi.Controller;

import Bank.Sampah.Bumi.Inspirasi.Model.Entity.*;
import Bank.Sampah.Bumi.Inspirasi.Model.Repo.*;
import Bank.Sampah.Bumi.Inspirasi.Services.Email.SendEmail;
import Bank.Sampah.Bumi.Inspirasi.Services.Excel.ExportExcel;
import Bank.Sampah.Bumi.Inspirasi.Services.HakCutiNewEmployee;
import Bank.Sampah.Bumi.Inspirasi.Services.UserAktif;
import Bank.Sampah.Bumi.Inspirasi.Utils.RandomPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/HRD")
public class HrdController {
    @Autowired
    RoleRepo roleRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    RandomPassword password;

    @Autowired
    SendEmail kirim;

    @Autowired
    HakCutiNewEmployee newEmployee;

    @Autowired
    PengajuanCutiRepo pengajuanCutiRepo;

    @Autowired
    StatusCutiRepo cutiRepo;

    @Autowired
    UserAktif userAktif;

    @Autowired
    DetailCutiRepo detailCutiRepo;

    @Autowired
    HakCutiRepo hakCutiRepo;

    @GetMapping("/karyawan")
    public String Employee(Model model){
        List<Employee> employees = employeeRepo.employeeAktif();
        model.addAttribute("employee",employees);
        return "TabelKaryawan";
    }

    @GetMapping("/recruitmen")
    public String Recruitmen(Model model){
        List<Role> roles = roleRepo.findAll();
        model.addAttribute("roles",roles);
        model.addAttribute("employee",new Employee());
        return "form_recruitmen";
    }

    @GetMapping("/karyawan/{id}")
    public String Edit(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeRepo.findById(id).get();
        List<Role> roles = roleRepo.findAll();
        model.addAttribute("employee",employee);
        model.addAttribute("roles",roles);
        return "form_recruitmen";
    }

    @GetMapping("/hapus_karyawan/{id}")
    public String Hapus(@PathVariable("id") Integer id){
        Employee employee = employeeRepo.findById(id).get();
        employee.setCreatedBy(employee.getCreatedBy());
        employee.setCreatedDate(employee.getCreatedDate());
        employee.setDeleted(false);
        employeeRepo.save(employee);
        return "redirect:/HRD/karyawan";
    }

    @PostMapping("/simpan")
    public String simpan(Employee employee){
        boolean cek=false;
        String nama = employee.getNama();
        if(employee.getEmploye_id()!=null){
            Employee employee1 = employeeRepo.findById(employee.getEmploye_id()).get();
            employee.setCreatedDate(employee1.getCreatedDate());
            employee.setCreatedBy(employee1.getCreatedBy());
            employee.setUsername(employee1.getUsername());
            employee.setPassword(employee1.getPassword());
        }else{
            cek=true;
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String kode = password.randomPassword();
            String hasil =encoder.encode(kode);
            employee.setUsername(nama);
            employee.setPassword(hasil);
        }
        employee.setDeleted(true);
        employee.setRole(employee.getRole());
        employeeRepo.save(employee);

        if(cek==true){
            newEmployee.Hak(employee);
        }
        return "redirect:/HRD/karyawan";
    }

    @GetMapping("/masuk")
    public String PengajuanMasuk(Model model){
        List<DetailCuti> detailCuti = detailCutiRepo.findAll();
        model.addAttribute("masuk",detailCuti);

        return "pengajuan_masuk";
    }

    @GetMapping("/aprove/{id}")
    public String Aprove(@PathVariable("id") Integer id){
        Employee HRD = employeeRepo.getUsername(userAktif.getUser());
        PengajuanCuti pengajuanCuti = pengajuanCutiRepo.findById(id).get();
        StatusCuti statusCuti = cutiRepo.getStatus(3);
        pengajuanCuti.setStatusCuti(statusCuti);
        pengajuanCuti.setHrd(HRD);
        pengajuanCuti.setCreatedBy(pengajuanCuti.getCreatedBy());
        pengajuanCuti.setCreatedDate(pengajuanCuti.getCreatedDate());
        pengajuanCutiRepo.save(pengajuanCuti);
        kirim.SendAprove(HRD,pengajuanCuti);
        DetailCuti detailCuti = detailCutiRepo.getDetailId(pengajuanCuti.getPengajuan_id());
        HakCuti hakCuti = hakCutiRepo.getHakCuti(pengajuanCuti.getEmployee().getEmploye_id(),detailCuti.getJenisCuti().getJenis_cuti_id());
        hakCuti.setCreatedDate(hakCuti.getCreatedDate());
        hakCuti.setCreatedBy(hakCuti.getCreatedBy());
        hakCuti.setSisa_cuti(hakCuti.getSisa_cuti()-pengajuanCuti.getLama_cuti());
        hakCutiRepo.save(hakCuti);
        return "redirect:/HRD/masuk";
    }

    @GetMapping("/reject/{id}")
    public String Reject(@PathVariable("id") Integer id){
        Employee HRD = employeeRepo.getUsername(userAktif.getUser());
        PengajuanCuti pengajuanCuti = pengajuanCutiRepo.findById(id).get();
        StatusCuti statusCuti = cutiRepo.getStatus(4);
        pengajuanCuti.setStatusCuti(statusCuti);
        pengajuanCuti.setHrd(HRD);
        pengajuanCuti.setCreatedBy(pengajuanCuti.getCreatedBy());
        pengajuanCuti.setCreatedDate(pengajuanCuti.getCreatedDate());
        pengajuanCutiRepo.save(pengajuanCuti);
        kirim.SendReject(HRD,pengajuanCuti);
        return "redirect:/HRD/masuk";
    }

    @GetMapping("/excel")
    public void Excel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=laporan_cuti_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<DetailCuti> detailCuti = detailCutiRepo.findAll();

        ExportExcel excelExpor = new ExportExcel(detailCuti);

        excelExpor.export(response);

    }


}
