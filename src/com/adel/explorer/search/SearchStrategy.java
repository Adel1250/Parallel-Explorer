package com.adel.explorer.search;

import java.io.File;

public interface SearchStrategy {
    void search(File folder, String searchWord);
}