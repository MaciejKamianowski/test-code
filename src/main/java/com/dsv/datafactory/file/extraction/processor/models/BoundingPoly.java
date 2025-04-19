package com.dsv.datafactory.file.extraction.processor.models;

import com.dsv.datafactory.model.Vertices;

import java.util.ArrayList;

public class BoundingPoly {

    // // use List instead of ArrayList for better abstraction and flexibility.
    // private List<Vertices> vertices;
    // private List<NormalizedVertices> normalizedVertices;
    private ArrayList<Vertices> vertices;
    private ArrayList<NormalizedVertices> normalizedVertices;

    // boiler plate code
    // use lombok annotation
    public void setVertices(ArrayList<Vertices> vertices) {
        this.vertices = vertices;
    }

    public void setNormalizedVertices(ArrayList<NormalizedVertices> normalizedVertices) {
        this.normalizedVertices = normalizedVertices;
    }

    public ArrayList<NormalizedVertices> getNormalizedVertices() {
        return normalizedVertices;
    }

    public ArrayList<Vertices> getVertices() {
        return vertices;
    }

    // BUG: this does not update the normalizedVertices field
    // fixed version
    // public void normalizeVertices(int width, int height){
    //    ArrayList<NormalizedVertices> normalized = new ArrayList<>();
    //    for (Vertices vertex : this.vertices){
    //        float x = (float) vertex.getX() / width;
    //        float y = (float) vertex.getY() / height;
    //        normalized.add(new NormalizedVertices(x, y));
    //    }
    //    this.normalizedVertices = normalized;
    //}
    public void normalizeVertices(int width, int height){
        ArrayList<Vertices> normalized = new ArrayList<>(); // use List instead of ArrayList for better abstraction and flexibility.
        //  List<Vertices> normalized = new ArrayList<>();
        for(Vertices vertex : this.vertices){
            int x = vertex.getX()/width;
            int y = vertex.getY()/height;
            normalized.add(new Vertices(x, y));
        }
    }
}
