package com.example.mylibrary.util.file;

import java.io.File;

public final class LocalFileEssayFilter extends LocalFileTypeFilter {

    @Override
    public boolean accept(File file, String s) {
        return !isHide(s) && (isDoc(s) || new File(file, s).isDirectory());
    }
}
