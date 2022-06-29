package Bank.Sampah.Bumi.Inspirasi.Services.Empl;

import Bank.Sampah.Bumi.Inspirasi.Model.Entity.Employee;
import Bank.Sampah.Bumi.Inspirasi.Model.Repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepo.getUsername(username);
        if(employee == null){
            throw new UsernameNotFoundException("Pengguna Tidak Ditemukan!");
        }
        return new MyUserDetails(employee);
    }
}
