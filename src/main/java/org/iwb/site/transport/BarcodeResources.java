package org.iwb.site.transport;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.oned.UPCEANWriter;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.google.zxing.client.j2se.MatrixToImageWriter.toBufferedImage;

/**
 * TODO document me
 *
 * @author Mathieu POUSSE <mathieu.pousse@zenika.com>
 */
@RestController
@RequestMapping("/barcode")
public class BarcodeResources {

    @RequestMapping(value = "/{barcode}", method = RequestMethod.GET, produces = "image/png")
    public byte[] generate(final HttpServletResponse response,
                           @PathVariable("barcode") final String barcode,
                           @RequestParam(value = "w", required = false, defaultValue = "200") int width,
                           @RequestParam(value = "h", required = false, defaultValue = "80") int height)
            throws WriterException, IOException {

        response.setHeader("Cache-Control", "public, max-age=2592000");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(barcode, BarcodeFormat.EAN_13, width, height);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "png", buffer);
        buffer.flush();
        buffer.close();
        return buffer.toByteArray();
    }

}
