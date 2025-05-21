package service;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import pojos.Productos;
import service.InformeService;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Properties;

@Service
public class EmailService {

    private final InformeService informeService;

    // Configuración SMTP
    private static final String SMTP_USER = "danieledgar.caballerohu@elorrieta-errekamari.com";
    private static final String SMTP_PASS = "vxzq innv tlmx xood";
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final int SMTP_PORT = 587;

    public EmailService(InformeService informeService) {
        this.informeService = informeService;
    }

    /**
     * ENVÍO DE INFORME PDF DE ALBARANES (NO TOCADO)
     */
    public boolean enviarInformePorEmail(String email, List<Integer> ids) {
        byte[] pdfBytes = informeService.generarPDF(ids);
        if (pdfBytes == null || pdfBytes.length == 0) return false;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", String.valueOf(SMTP_PORT));

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USER, SMTP_PASS);
            }
        });

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(SMTP_USER));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            msg.setSubject("Informe de Albaranes");

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Adjunto encontrarás el Informe de Albaranes en PDF.", "utf-8");

            MimeBodyPart pdfPart = new MimeBodyPart();
            DataSource pdfSource = new ByteArrayDataSource(pdfBytes, "application/pdf");
            pdfPart.setDataHandler(new DataHandler(pdfSource));
            pdfPart.setFileName("informe_albaranes.pdf");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(pdfPart);
            msg.setContent(multipart);

            Transport.send(msg);
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * ENVÍO DE INVENTARIO EN EXCEL (ADAPTADO)
     */
    public boolean enviarInventarioConExcel(String email, List<Productos> productos) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Inventario");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Nombre");
            header.createCell(1).setCellValue("Descripción");
            header.createCell(2).setCellValue("Precio");
            header.createCell(3).setCellValue("Stock Actual");
            header.createCell(4).setCellValue("Stock Mínimo");
            header.createCell(5).setCellValue("Habilitado");

            int rowNum = 1;
            for (Productos p : productos) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(p.getNombre());
                row.createCell(1).setCellValue(p.getDescripcion() != null ? p.getDescripcion() : "");
                row.createCell(2).setCellValue(p.getPrecioUnitario().doubleValue());
                row.createCell(3).setCellValue(p.getStockActual());
                row.createCell(4).setCellValue(p.getStockMinimo());
                row.createCell(5).setCellValue(p.isHabilitado() ? "Sí" : "No");
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            byte[] excelData = out.toByteArray();

            return enviarCorreoConExcel(email, "Informe de Inventario", "Adjunto encontrarás el inventario en Excel.", excelData);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean enviarCorreoConExcel(String destinatario, String asunto, String cuerpo, byte[] archivoExcel) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", String.valueOf(SMTP_PORT));

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USER, SMTP_PASS);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SMTP_USER));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(cuerpo, "utf-8");

            MimeBodyPart excelPart = new MimeBodyPart();
            DataSource excelSource = new ByteArrayDataSource(archivoExcel, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            excelPart.setDataHandler(new DataHandler(excelSource));
            excelPart.setFileName("inventario.xlsx");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(excelPart);
            message.setContent(multipart);

            Transport.send(message);
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
