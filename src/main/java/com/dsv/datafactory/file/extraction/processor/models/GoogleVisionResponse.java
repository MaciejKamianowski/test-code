package com.dsv.datafactory.file.extraction.processor.models;

import java.util.ArrayList;

// // boiler plate code
//// use lombok annotations
public class GoogleVisionResponse {
    private ArrayList<EntityAnnotation> textAnnotations = new ArrayList<>();    //
    private TextAnnotation fullTextAnnotation;

    public void setFullTextAnnotation(TextAnnotation fullTextAnnotation) {
        this.fullTextAnnotation = fullTextAnnotation;
    }

    public void setTextAnnotations(ArrayList<EntityAnnotation> textAnnotations) {
        this.textAnnotations = textAnnotations;
    }

    public void addTextAnnotation(EntityAnnotation entity){
        this.textAnnotations.add(entity);
    }

    // Verbose list addition –
    // addMultipleTextAnnotations can use addAll instead of manually iterating.
    public void addMultipleTextAnnotations(ArrayList<EntityAnnotation> textAnnotations) {   // use List<EntityAnnotation> textAnnotations
        for (EntityAnnotation textAnnotation : textAnnotations) {
            this.textAnnotations.add(textAnnotation);
        }

    }

    public TextAnnotation getFullTextAnnotation() {
        return fullTextAnnotation;
    }

    // Mutable collections exposed directly
    // Returning the ArrayList directly exposes internal state,
    // which breaks encapsulation
    public ArrayList<EntityAnnotation> getTextAnnotations() {
        return textAnnotations;
    }


}

// better version
// @Data
//@NoArgsConstructor
//public class GoogleVisionResponse {
//
//    private List<EntityAnnotation> textAnnotations = new ArrayList<>();
//    private TextAnnotation fullTextAnnotation;
//
//    public void addTextAnnotation(EntityAnnotation entity) {
//        this.textAnnotations.add(entity);
//    }
//
//    public void addTextAnnotations(List<EntityAnnotation> annotations) {
//        this.textAnnotations.addAll(annotations);
//    }
//}