package service;

import org.springframework.stereotype.Service;
import pojos.Albaranes;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Base64;
import java.math.BigDecimal;

import repository.AlbaranRepository;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;

@Service
public class InformeService {

    private final AlbaranRepository albaranRepository;

    public InformeService(AlbaranRepository albaranRepository) {
        this.albaranRepository = albaranRepository;
    }

    public Map<String, Object> generarResumen(List<Integer> ids) {
        List<Albaranes> albaranes = albaranRepository.findAllById(ids);

        long pagados = albaranes.stream()
            .filter(a -> a.getEstado() == Albaranes.EstadoAlbaran.Pagado)
            .count();
        long pendientes = albaranes.size() - pagados;

        BigDecimal totalImporte = albaranes.stream()
            .map(Albaranes::getTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<Integer> idesPagados = albaranes.stream()
            .filter(a -> a.getEstado() == Albaranes.EstadoAlbaran.Pagado)
            .map(Albaranes::getIdAlbaran)
            .distinct()
            .toList();

        List<Integer> idesPendientes = albaranes.stream()
            .filter(a -> a.getEstado() == Albaranes.EstadoAlbaran.Pendiente)
            .map(Albaranes::getIdAlbaran)
            .distinct()
            .toList();

        Map<String, Object> resumen = new HashMap<>();
        resumen.put("totalAlbaranes", albaranes.size());
        resumen.put("pagados", pagados);
        resumen.put("pendientes", pendientes);
        resumen.put("totalImporte", totalImporte);
        resumen.put("idesPagados", idesPagados);
        resumen.put("idesPendientes", idesPendientes);
        return resumen;
    }

public byte[] generarPDF(List<Integer> ids) {
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
         PdfWriter writer  = new PdfWriter(baos);
         PdfDocument pdf   = new PdfDocument(writer);
         Document document = new Document(pdf)) {

        // 1. Título del informe
        document.add(new Paragraph("INFORME DE ALBARANES")
                .setBold()
                .setFontSize(22)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20));

        // 2. Añadir cada imagen decodificada desde Base64
        List<Albaranes> lista = albaranRepository.findAllById(ids);
        for (Albaranes al : lista) {
            String raw = al.getFotoAlbaran();  // String con Base64
            if (raw != null && !raw.isBlank()) {
                // 2.1 Quitar prefijo data:…;base64, si existe
                String clean = raw.replaceFirst("^data:image/[^;]+;base64,", "")
                                  .replaceAll("\\s+", "");
                try {
                    // 2.2 Decodificar Base64 (MIME tolera saltos de línea)
                    byte[] imgBytes = java.util.Base64.getMimeDecoder().decode(clean);
                    ImageData idata = ImageDataFactory.create(imgBytes);
                    document.add(new Image(idata).setAutoScale(true));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    document.add(new Paragraph(
                      "Error al procesar imagen albarán #" + al.getIdAlbaran()
                    ));
                }
            } else {
                document.add(new Paragraph(
                  "Sin imagen para albarán #" + al.getIdAlbaran()
                ));
            }
            document.add(new AreaBreak());
        }

        // 3. Resumen final
        Map<String, Object> resumen = generarResumen(ids);

        document.add(new Paragraph("Resumen de Albaranes")
                .setBold()
                .setFontSize(18)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginTop(20));

        Table table = new Table(UnitValue.createPercentArray(new float[]{4,4}))
                .setWidth(UnitValue.createPercentValue(100))
                .setMarginTop(10);

        table.addCell(new Cell().add(new Paragraph("Total albaranes")).setBold());
        table.addCell(resumen.get("totalAlbaranes").toString());

        table.addCell(new Cell().add(new Paragraph("Pagados")).setBold());
        table.addCell(resumen.get("pagados").toString());

        table.addCell(new Cell().add(new Paragraph("Pendientes")).setBold());
        table.addCell(resumen.get("pendientes").toString());

        table.addCell(new Cell().add(new Paragraph("Importe total")).setBold());
        table.addCell(resumen.get("totalImporte").toString() + " €");

        table.addCell(new Cell().add(new Paragraph("IDs pagados")).setBold());
        table.addCell(resumen.get("idesPagados").toString());

        table.addCell(new Cell().add(new Paragraph("IDs pendientes")).setBold());
        table.addCell(resumen.get("idesPendientes").toString());

        document.add(table);
        document.close();

        return baos.toByteArray();

    } catch (Exception e) {
        e.printStackTrace();
        return new byte[0];
    }
}

    public boolean enviarInformePorEmail(String email, List<Integer> ids) {
        // Implementación de envío de correo si se desea
        return true;
    }
}
