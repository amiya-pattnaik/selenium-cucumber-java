package info.seleniumcucumber.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.Properties;

public class SauceLabsFileManager {

    static void uploadAppToSauceStorage(String appName, String appPath, Properties prop) {
        System.out.println("Uploading App " + appName + " to sauce storage");
        InputStream input;
        String username = prop.getProperty("username");
        String access_key = prop.getProperty("access_key");

        String uploadURL = "https://saucelabs.com/rest/v1/storage/" + username + "/" + appName + "?overwrite=true";
        String encoding = Base64.getEncoder().encodeToString((username + ":" + access_key).getBytes());

        URLConnection urlconnection = null;
        try {
            File file = new File(appPath);
            URL url = new URL(uploadURL);
            urlconnection = url.openConnection();
            urlconnection.setDoOutput(true);
            urlconnection.setDoInput(true);

            if (urlconnection instanceof HttpURLConnection) {
                ((HttpURLConnection) urlconnection).setRequestMethod("POST");
                urlconnection.setRequestProperty("Content-type", "text/plain");
                urlconnection.setRequestProperty("Authorization", "Basic " + encoding);
                urlconnection.connect();
            }

            BufferedOutputStream bos = new BufferedOutputStream(urlconnection.getOutputStream());
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            int i;
            // read byte by byte until end of stream
            while ((i = bis.read()) > 0) {
                bos.write(i);
            }
            bis.close();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            assert urlconnection != null;
            int responseCode = ((HttpURLConnection) urlconnection).getResponseCode();
            if ((responseCode >= 200) && (responseCode <= 202)) {
                System.out.println("App uploaded successfully");
            } else {
                System.out.println("App upload failed");
            }
            System.out.println("responseCode : " + responseCode);

            ((HttpURLConnection) urlconnection).disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
