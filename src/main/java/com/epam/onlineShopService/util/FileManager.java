package com.epam.onlineShopService.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class FileManager {
    private static final int MAX_SIZE = 4096;
    private static final int FILE_OVER = -1;
    private static final int START_OFFSET = 0;
    private static final String EMPTY_STRING = "";

    public String takeFileFromDataBase(Blob blob) throws SQLException, IOException {

        String base64String;
        byte[] buffer;
        int bytesRead;

        if (blob == null) {
            return EMPTY_STRING;
        } else {
            buffer = new byte[MAX_SIZE];

            InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            while ((bytesRead = inputStream.read(buffer)) != FILE_OVER) {
                outputStream.write(buffer, START_OFFSET, bytesRead);
            }

            byte[] imageBytes = outputStream.toByteArray();
            base64String = Base64.getEncoder().encodeToString(imageBytes);
            inputStream.close();
            outputStream.close();
            return base64String;
        }
    }
}

