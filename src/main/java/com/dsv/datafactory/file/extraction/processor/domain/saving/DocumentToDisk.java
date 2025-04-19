package com.dsv.datafactory.file.extraction.processor.domain.saving;

import com.dsv.datafactory.file.extraction.processor.util.ConfigurationLoader;
import com.dsv.datafactory.model.Document;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;


import java.io.*;
import java.nio.charset.StandardCharsets;


public class DocumentToDisk {
//    private String pathImages;    // remove this
    // wrong order
    // should be static final or better
    // private static final

    // introduce const
    // private static final String DEFAULT_PATH_DOCUMENT = "/files/hocr/";
    final static Logger logger = Logger.getLogger(DocumentToDisk.class.getSimpleName());    // const should be name LOGGER
    //  final static Logger LOGGER = Logger.getLogger(DocumentToDisk.class);
    public void execute(String key, Document res,String shipmentId) throws IOException  // missing space between command and String
    {   // opening curly bracket should be on the same line
        // extract const
        String pathDocument =  ConfigurationLoader.getOrDefault("PATH_DOCUMENT", "/files/hocr/")+shipmentId+"/";
        checkIfExists(pathDocument);
        saveDocumentAsJson(key, res, pathDocument);
    }

    private void saveDocumentAsJson(String key, Document file,String pathToDocument) throws IOException
    {
            writeToJson(key,file,pathToDocument);   // redundant tabula
    }

    private void checkIfExists(String pathShipmentIdFolder){
        File file = new File(pathShipmentIdFolder);
        if (!file.exists()) {
            file.mkdir();
        }
        // use mkdirs() instead of mkdir() to support nested folders.
        // log if creation has failed
        //if (!file.exists() && !file.mkdirs()) {
        //    LOGGER.warn("Could not create directory: " + file.getAbsolutePath());
        //}

    }
    private void writeToJson(String key, Document doc,String pathDocument) throws IOException { // missing space
        String documentExt = "json";    // extarct this to const
        String fullPath = pathDocument +key + "."+ documentExt; // missing space
        doc.setPathToDocumentFile(fullPath);
        logger.info("fullpath is: " + fullPath);

        File file = new File(fullPath);
        // resource leak
        OutputStreamWriter writer =
                new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
        // use try with resources
        // try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
        //    ObjectMapper mapper = new ObjectMapper();
        //    String jsonString = mapper.writeValueAsString(doc);
        //    writer.write(jsonString);
        //}
        // extract ObjectMapper to configuration class as @Bean and inject it here
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(doc);
        writer.write(jsonString);
        writer.close();
    }

    // in configuration class
/*    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }*/

}