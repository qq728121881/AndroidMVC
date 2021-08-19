package com.example.mylibrary.util.file;

import java.io.File;
import java.io.FilenameFilter;

public class LocalFileTypeFilter implements FilenameFilter {

    protected boolean isEpub(String fname) {
        return fname.toLowerCase().endsWith(".epub");
    }

    protected boolean isFb2(String fname) {
        return fname.toLowerCase().endsWith(".fb2");
    }

    protected boolean isMobi(String fname) {
        return fname.toLowerCase().endsWith(".mobi");
    }

    protected boolean isTxt(String fname) {
        return fname.toLowerCase().endsWith(".txt");
    }

    protected boolean isDoc(String fname) {
        return fname.toLowerCase().endsWith(".doc");
    }

    protected boolean isRtf(String fname) {
        return fname.toLowerCase().endsWith(".rtf");
    }

    protected boolean isHide(String fname){
        return fname.startsWith(".");
    }

    @Override
    public boolean accept(File file, String s) {
        return !isHide(s) && (isEpub(s) || isFb2(s) || isMobi(s) || isDoc(s) || isTxt(s) || isRtf(s) || new File(file, s).isDirectory());
    }

    public boolean accept(String fname){
        return isEpub(fname)||isFb2(fname)||isMobi(fname)||isTxt(fname)||isDoc(fname)||isRtf(fname);
    }

}
