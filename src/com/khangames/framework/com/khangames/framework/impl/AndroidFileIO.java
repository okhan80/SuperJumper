package com.khangames.framework.com.khangames.framework.impl;

import android.content.res.AssetManager;
import android.os.Environment;
import com.khangames.framework.FileIO;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 1/13/12
 * Time: 2:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class AndroidFileIO implements FileIO {
    AssetManager assets;
    String externalStoragePath;

    public AndroidFileIO(AssetManager assets) {
        this.assets = assets;
        this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    @Override
    public InputStream readAsset(String fileName) throws IOException {
        return assets.open(fileName);
    }

    @Override
    public InputStream readFile(String fileName) throws IOException {
        return new FileInputStream(externalStoragePath + fileName);
    }

    @Override
    public OutputStream writeFile(String fileName) throws IOException {
        return new FileOutputStream(externalStoragePath + fileName);
    }
}
