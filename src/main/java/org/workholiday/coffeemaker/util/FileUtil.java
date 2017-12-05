package org.workholiday.coffeemaker.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Function: 文件工具类
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     18:31 06/13/2017
 * Version:  1.0
 */
public class FileUtil {


    private FileUtil() {
        // to avoid creating instance
    }

    /**
     * 导出文件
     * @param targetFile
     * @param source
     */
    public static void write(String targetPath, String targetFile, String source) {

        FileWriter writer = null;

        try {

            File path = new File(targetPath);
            if (!path.exists()) {
                path.mkdirs();
            }

            writer = new FileWriter(targetPath + targetFile);
            writer.write(source);

        } catch (IOException e) {
            // ignored
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                }
            }
        }


    }

}
