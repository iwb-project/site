package org.iwb.site.transport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * TODO: document me.
 *
 * @author mathieu.pousse@zenika.com
 */
@RestController
@RequestMapping(value = "/images/")
public class ImageResources {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageResources.class);


    @RequestMapping(value = "upload", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String prepare(
            @RequestParam("flowChunkNumber") int flowChunkNumber,
            @RequestParam("flowChunkSize") long flowChunkSize,
            @RequestParam("flowCurrentChunkSize") long flowCurrentChunkSize,
            @RequestParam("flowTotalSize") long flowTotalSize,
            @RequestParam("flowIdentifier") String flowIdentifier,
            @RequestParam("flowFilename") String flowFilename,
            @RequestParam("flowRelativePath") String flowRelativePath,
            @RequestParam("flowTotalChunks") long flowTotalChunks
    ) {
        LOGGER.info("preparing");
        return "not-available";
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadChunk(
            @RequestParam(value = "flowChunkNumber", required = false) Integer flowChunkNumber,
            @RequestParam(value = "flowChunkSize", required = false) Long flowChunkSize,
            @RequestParam(value = "flowCurrentChunkSize", required = false) Long flowCurrentChunkSize,
            @RequestParam(value = "flowTotalSize", required = false) Long flowTotalSize,
            @RequestParam(value = "flowIdentifier", required = false) String flowIdentifier,
            @RequestParam(value = "flowFilename", required = false) String flowFilename,
            @RequestParam(value = "flowRelativePath", required = false) String flowRelativePath,
            @RequestParam(value = "flowTotalChunks", required = false) Long flowTotalChunks,
            @RequestParam(value = "file") MultipartFile content
    ) {
        LOGGER.info("uploading");
        return "Uploaded";
    }

}
