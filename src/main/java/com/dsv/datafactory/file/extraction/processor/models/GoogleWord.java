package com.dsv.datafactory.file.extraction.processor.models;

import java.util.ArrayList;

// boiler plate code
// use lombok annotation
public class GoogleWord {
    private TextProperty property;
    private BoundingPoly boundingBox;
    private ArrayList<GoogleSymbol> symbols;    // use List<GoogleSymbol> symbols;
    private double confidence;

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public void setBoundingBox(BoundingPoly boundingBox) {
        this.boundingBox = boundingBox;
    }

    public void setProperty(TextProperty property) {
        this.property = property;
    }

    public void setSymbols(ArrayList<GoogleSymbol> symbols) {
        this.symbols = symbols;
    }

    public double getConfidence() {
        return confidence;
    }

    public BoundingPoly getBoundingBox() {
        return boundingBox;
    }

    public TextProperty getProperty() {
        return property;
    }

    public ArrayList<GoogleSymbol> getSymbols() {
        return symbols;
    }
}
