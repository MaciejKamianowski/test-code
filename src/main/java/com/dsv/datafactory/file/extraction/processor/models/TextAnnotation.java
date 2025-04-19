package com.dsv.datafactory.file.extraction.processor.models;

import java.util.ArrayList;

// boiler plate code
// use lombok annotation
public class TextAnnotation {
    private ArrayList<GooglePage> pages;    // use List<GooglePage> pages;
    private String text;

    public void setPages(ArrayList<GooglePage> pages) {
        this.pages = pages;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public ArrayList<GooglePage> getPages() {
        return pages;
    }
}
