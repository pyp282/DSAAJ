import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pei on 2017/3/12.
 */
public class BuildFolder {

    public static void replaceContent() throws Exception {
        String folderPath = "D:\\IdeaProjects\\DSAAJ2-Answers\\src.main.java";
        File files = new File(folderPath);
        String[] fileList = files.list();

        String target = "Solution";
        BufferedReader reader;

        for (String filename : fileList) {
            String oldCode = "";
            String simpleClassName = getSimpleClassName(filename);

            reader = new BufferedReader(new FileReader(folderPath + "/" + filename));

            String lineCode;

            while ((lineCode = reader.readLine()) != null) {
                oldCode += lineCode;
            }

            reader.close();
            String replacedCode = oldCode.replaceAll(target, simpleClassName);

            FileWriter writer = new FileWriter(folderPath + "/" + filename);
            writer.write(replacedCode);
            writer.close();
        }
    }

    public static String getSimpleClassName(String filename) {
        String regex = "\\.";

//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(filename);

//        return matcher.group(0);

        return filename.split(regex)[3];
    }

    public static void findDir() {
        String baseFolder = "D:\\IdeaProjects\\DSAAJ2-Answers\\src.main.java";
        File files = new File(baseFolder);
        String[] fileList = files.list();

        String newFolder = "D:\\IdeaProjects\\DSAAJ2-Answers\\src.main.resource";

        for (String filename : fileList) {
            String parentDir = getParentDir(filename);

            File file = new File(newFolder + "/" + parentDir);
            if (!file.isDirectory()) {
                file.mkdirs();
            }

        }
    }

    public static String getParentDir(String filename) {
        String regex = "(\\w+\\.){2}\\w+";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(filename);

        if (matcher.find()) {
            String parentDir = matcher.group(0).replaceAll("\\.", "/");
            return parentDir;
        }

        return null;
    }

    public static void copyFile(String fromFolder, String toFolder)throws Exception{
        File files = new File(fromFolder);
        String[] fileList = files.list();


        BufferedReader reader;

        for (String filename : fileList) {
            String oldCode = "";
            reader = new BufferedReader(new FileReader(fromFolder + "/" + filename));

            String lineCode;
            while ((lineCode = reader.readLine()) != null) {
                oldCode += lineCode;
            }
            reader.close();

            String folder = findFolder(filename, toFolder);
            FileWriter writer = new FileWriter(folder + "/" + getSimpleClassName(filename) + ".java");
                writer.write(oldCode);
                writer.close();
        }
    }

    public static String findFolder(String filename, String toFloder){
        String parentDir = getParentDir(filename);

        File file = new File(toFloder + "/" + parentDir);
        if (!file.isDirectory()) {
            try {
                file.mkdirs();
                return toFloder + "/" + parentDir;
            } catch (Exception e) {
                return null;
            }
        }

        return toFloder + "/" + parentDir;
    }


    public static void main(String[] args) throws Exception {
        String fromFolder = "D:\\IdeaProjects\\DSAAJ2-Answers\\src.main.java";
        String toFolder = "D:\\IdeaProjects\\DSAAJ2-Answers\\src.main.resource";

        copyFile(fromFolder, toFolder);
//        replaceContent();
    }
}
