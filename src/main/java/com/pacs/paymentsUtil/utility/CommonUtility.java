package com.pacs.paymentsUtil.utility;

import java.io.*;

public class CommonUtility {

    public static String getFileFromLocation(String path){
        StringBuilder contentBuilder = new StringBuilder();
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

}

