package Bank.Sampah.Bumi.Inspirasi.Services.Email;

import Bank.Sampah.Bumi.Inspirasi.Model.Entity.Nasabah;
import Bank.Sampah.Bumi.Inspirasi.Model.Repo.NasabahRepo;
import Bank.Sampah.Bumi.Inspirasi.Services.UserAktif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class SendEmailNasabah {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    NasabahRepo nasabahRepo;

    @Autowired
    UserAktif userAktif;

    public void EmailRecrutment(String email, String nama, String password, String namaRekening, Integer nomorRekening, String nib){
        Nasabah nasabah = nasabahRepo.getUsername(userAktif.getUser());
        System.out.println(nasabah.getEmail());
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try{
            helper.setSubject("PT Pasim Utama");
            helper.setFrom(new InternetAddress(nasabah.getEmail(), nasabah.getNama()));
            helper.setTo(email);
            String link = "http://localhost:8080/login";
            boolean html = true;
            helper.setText("<b>Yang Terhormat "+nama+"</b>," +
                            "<p>Terima kasih telah menjadi nasabah di <b>Bank Sampah Bumi Inspirasi</b></p>" +
                            "<p>Kamu terdaftar dengan nama Rekening : <b>"+namaRekening+"</b></p>"+
                            "<p>dan Nomor Rekening : <b>"+nomorRekening+"</b> <p>" +
                            "<p>Kamu bisa akses info seputar akun dan transaksi nasabah di link <b><a href="+link+">login</a></b> </p>." +
                            "<p>dengan username <b>"+nama+"</b> dan password <b>"+password+"</b></p>"+
                            "<p>Yuk kita kelola sampah dengan baik, sampah bukan mantan yang harus dibuang ke sembarang tempat :) </p>"+
                            "<p><b>Bank Sampah Bumi Inspirasi</b></p>" +
                            "<p><i>Jl. Cisitu Indah VI no. 188 RT 001 RW 004 Dago Coblong</i><p/>"
                    , html);

            emailSender.send(message);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

   /* public void SendCuti(nasabah nasabah, String[] to, PengajuanCuti cuti){
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try{
            helper.setSubject("Pengajuan cuti dari "+nasabah.getNama()+"");
            helper.setFrom(new InternetAddress(nasabah.getEmail(), nasabah.getEmail()));
            helper.setTo(to);
            boolean html = true;
            helper.setText("Assalamu'alaikum warohmatullahi wabarokatuh"+
                    "<p>Nama Saya: <b>"+nasabah.getNama()+"</b></p>" +
                            "<p>Saya izin cuti selama <b>"+cuti.getLama_cuti()+" hari </b>"+
                            "<p>Dikarenakan "+cuti.getKeterangan()+"<p>"
                    , html);

            emailSender.send(message);
        }catch(Exception e){
            e.printStackTrace();
        }

    }*/

   /* public void Sendcancel(Nasabah nasabah, String[] to){
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try{
            helper.setSubject("Cancel cuti dari "+nasabah.getEmail()+"");
            helper.setFrom(new InternetAddress(nasabah.getEmail(), nasabah.getEmail()));
            helper.setTo(to);
            boolean html = true;
            helper.setText("Assalamu'alaikum warohmatullahi wabarokatuh bapak/ibu "+
                            "<p>Nama Saya: <b>"+nasabah.getNama()+"</b></p>" +
                            "<p>Saya tidak jadi cuti hehehe</p>"
                    , html);

            emailSender.send(message);
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/


   /* public void SendAprove(nasabah nasabah, PengajuanCuti cuti){
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try{
            helper.setSubject("Pengajuan Cuti Di Approve");
            helper.setFrom(new InternetAddress(nasabah.getEmail(),nasabah.getEmail()));
            helper.setTo(cuti.getnasabah().getEmail());
            boolean html = true;
            helper.setText("<p>Pengajuan cuti anda diterima HRD</p>" +
                    "<p>Selamat Bercuti</p>",html);

            emailSender.send(message);
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/

   /* public void SendReject(Nasabah nasabah, PengajuanCuti cuti){
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try{
            helper.setSubject("Pengajuan Cuti Ditolak");
            helper.setFrom(new InternetAddress(nasabah.getEmail(),nasabah.getEmail()));
            helper.setTo(cuti.getnasabah().getEmail());
            boolean html = true;
            helper.setText("<p>Maff Pengajuan Cuti Anda Ditolak HRD</p>" +
                    "<p>Baru Kerja Sudah minta cuti</p>", html);

            emailSender.send(message);
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/

}
