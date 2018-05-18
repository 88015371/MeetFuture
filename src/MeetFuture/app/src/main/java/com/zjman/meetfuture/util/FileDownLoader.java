package com.zjman.meetfuture.util;

import com.zchu.log.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;


public class FileDownLoader {

    private static volatile FileDownLoader fileLoader;
    private static String filePath, fileName;

    public static FileDownLoader getInstance(String filePath, String fileName) {
        fileLoader = new FileDownLoader(filePath, fileName);
        return fileLoader;

    }


    private OkHttpClient httpClient;

    private FileDownLoader(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
        httpClient = new OkHttpClient.Builder().build();
        File fileDir = new File(filePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
    }

    public Observable<File> load(final String url) {
        return Observable.create(new Observable.OnSubscribe<File>() {
            @Override
            public void call(Subscriber<? super File> subscriber) {
                subscriber.onStart();
                try {
                    subscriber.onNext(downLoadFile(url));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    Logger.e(e);
                    /*try {
                        downLoadFile(url);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        subscriber.onError(e);
                    }*/
                    subscriber.onError(e);
                }
            }
        });
    }

    public File downLoadFile(String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = httpClient
                .newCall(request)
                .execute();

        InputStream inputStream = response.body().byteStream();
        File file = new File(filePath, fileName);
        if (file.exists()) {
            file.delete();
        }
        OutputStream outputStream = new FileOutputStream(file);
        byte[] fileReader = new byte[1024];
        while (true) {
            int read = inputStream.read(fileReader);
            if (read == -1) {
                break;
            }
            outputStream.write(fileReader, 0, read);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        return file;
    }


}
