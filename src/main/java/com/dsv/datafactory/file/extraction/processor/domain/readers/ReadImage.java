package com.dsv.datafactory.file.extraction.processor.domain.readers;


import com.dsv.datafactory.file.extraction.processor.domain.ExtractLines;
import com.dsv.datafactory.file.extraction.processor.domain.ocr.GoogleOcr;
import com.dsv.datafactory.file.extraction.processor.domain.ocr.GoogleOcrP;
import com.dsv.datafactory.model.Document;


import javax.inject.Inject;

import java.util.ArrayList;


public class ReadImage {

    // redundant new line
    private GoogleOcrP googleOcrP;
    private ExtractLines lineExtractor;

    @Inject
    public ReadImage(GoogleOcrP ocr, ExtractLines extractLines)
    {   // formatting, opening curly bracket on the same line
        this.googleOcrP = ocr; this.lineExtractor= extractLines;    // formatting:
        // for better readability
        // this.googleOcrP = ocr;
        //    this.lineExtractor = extractLines;
    }
    // replace ArrayList with List
    // it makes more flexibility, meets PTI principle (program to interface)
    public Document extract(ArrayList<String> listOfPathImgs, String key ) throws Exception {
       // clarify or remove this comment
        // made a new results each time to avoid accumulation
        // better version
        // Generate a fresh document for each request
        Document document = googleOcrP.generateDocument(listOfPathImgs,key);
        lineExtractor.generateInputFromDocument(document);
        return document;

    }

    // redundant new line
}
