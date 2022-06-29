package Bank.Sampah.Bumi.Inspirasi.Services.Interface;

import Bank.Sampah.Bumi.Inspirasi.DTO.PengajuanDto;
import Bank.Sampah.Bumi.Inspirasi.Model.Entity.Employee;

import java.util.List;

public interface PengajuanCutiService {
    public void Simpan(PengajuanDto pengajuanDto, Employee employee);
    public PengajuanDto getPengajuanId(Integer id, Employee employee);
    public List<PengajuanDto> getAll();
}
