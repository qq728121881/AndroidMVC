package com.example.mylibrary.util.file;

import java.io.File;

public final class LocalFileBookFilter extends LocalFileTypeFilter {

    @Override
    public boolean accept(File file, String s) {
        return !isHide(s) && (isEpub(s) || isFb2(s) || isMobi(s) || isRtf(s) || new File(file, s).isDirectory());
    }
}
