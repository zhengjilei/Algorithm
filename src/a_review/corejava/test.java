package a_review.corejava;

import java.io.*;

/**
 * created by Ethan-Walker on 2019/2/21
 */
public class test {
    public static void main(String[] args) throws CloneNotSupportedException, UnsupportedEncodingException, FileNotFoundException {

        copy1("G:\\26_面试\\Interview-Notebook-master\\notes\\MySQL.md");
        copy2("G:\\03_Code\\Algorithm\\src\\b.txt");
    }

    public static void read(String dir) {
        File file = new File(dir);
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    if (f.isDirectory()) {
                        read(f.getPath());
                    } else {
                        System.out.println(f.getName());
                    }
                }
            } else {
                System.out.println(file.getName());
            }
        }
    }

    public static void copy1(String dir) throws FileNotFoundException {
        File file = new File(dir);
        if (!file.exists()) throw new FileNotFoundException();

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(new File("copy1.txt")));
        ) {

            String line = null;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copy2(String dir) throws FileNotFoundException {
        File file = new File(dir);
        if (!file.exists()) throw new FileNotFoundException();

        try (FileInputStream fis = new FileInputStream(file);
             FileOutputStream fos = new FileOutputStream(new File("copy2.txt"))) {
            byte[] buffer = new byte[1024 * 4];// 4KB
            int len = -1;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
