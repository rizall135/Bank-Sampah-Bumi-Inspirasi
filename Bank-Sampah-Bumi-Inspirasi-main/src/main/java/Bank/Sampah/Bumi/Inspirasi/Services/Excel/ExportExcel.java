package Bank.Sampah.Bumi.Inspirasi.Services.Excel;

import Bank.Sampah.Bumi.Inspirasi.Model.Entity.DetailCuti;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ExportExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<DetailCuti> detailCutis;

    public ExportExcel(List<DetailCuti> detailCutis) {
        this.detailCutis = detailCutis; //untuk menerima data
        workbook = new XSSFWorkbook(); // untuk membuat file baru
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Laporan");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);

        createCell(row, 0, "ID Pengajuan", style);
        createCell(row, 1, "Nama", style);
        createCell(row, 2, "Nip", style);
        createCell(row, 3, "Divisi", style);
        createCell(row, 4, "Telp", style);
        createCell(row, 5, "Alamat", style);
        createCell(row, 6, "Tanggal Cuti", style);
        createCell(row, 7, "Lama Cuti", style);
        createCell(row, 8, "Keterangan", style);
        createCell(row, 9, "Status", style);
        createCell(row, 10, "Deleted", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        }else if(value instanceof Date){
            cell.setCellValue((Date) value);
        }else if(value instanceof Boolean){
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }


    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(12);
        style.setFont(font);
        CreationHelper createHelper = workbook.getCreationHelper();

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d/m/yy h:mm"));

        for (DetailCuti detailCuti : detailCutis) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, detailCuti.getPengajuanCuti().getPengajuan_id(), style);
            createCell(row, columnCount++, detailCuti.getPengajuanCuti().getEmployee().getNama(), style);
            createCell(row, columnCount++, detailCuti.getPengajuanCuti().getEmployee().getNip(), style);
            createCell(row, columnCount++, detailCuti.getPengajuanCuti().getEmployee().getDivisi(), style);
            createCell(row, columnCount++, detailCuti.getPengajuanCuti().getNoTelp(), style);
            createCell(row, columnCount++, detailCuti.getPengajuanCuti().getAlamat(), style);
            createCell(row, columnCount++, detailCuti.getTanggal().toString(), cellStyle);
            createCell(row, columnCount++, detailCuti.getPengajuanCuti().getLama_cuti(), style);
            createCell(row, columnCount++, detailCuti.getPengajuanCuti().getKeterangan(), style);
            createCell(row, columnCount++, detailCuti.getPengajuanCuti().getStatusCuti().getStatus_cuti(), style);
            createCell(row, columnCount++, detailCuti.isDeleted(), style);


        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }

}

