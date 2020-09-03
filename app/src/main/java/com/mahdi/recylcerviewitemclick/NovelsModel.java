package com.mahdi.recylcerviewitemclick;

import org.parceler.Parcel;

@Parcel
public class NovelsModel {

    private String novelNames,novelAuthors,novelIntros;
    private int novelImage;

    public NovelsModel() {
    }

    public NovelsModel(String novelNames, String novelAuthors, String novelIntros, int novelImage) {
        this.novelNames = novelNames;
        this.novelAuthors = novelAuthors;
        this.novelIntros = novelIntros;
        this.novelImage = novelImage;
    }

    public String getNovelNames() {
        return novelNames;
    }

    public void setNovelNames(String novelNames) {
        this.novelNames = novelNames;
    }

    public String getNovelAuthors() {
        return novelAuthors;
    }

    public void setNovelAuthors(String novelAuthors) {
        this.novelAuthors = novelAuthors;
    }

    public String getNovelIntros() {
        return novelIntros;
    }

    public void setNovelIntros(String novelIntros) {
        this.novelIntros = novelIntros;
    }

    public int getNovelImage() {
        return novelImage;
    }

    public void setNovelImage(int novelImage) {
        this.novelImage = novelImage;
    }
}
