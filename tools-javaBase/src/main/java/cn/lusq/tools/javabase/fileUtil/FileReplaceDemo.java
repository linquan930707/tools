package cn.lusq.tools.javabase.fileUtil;

import java.io.File;

/**
 * 文件批量改名
 * lusq
 * 2021/1/26 15:24
 */
public class FileReplaceDemo {

    private static final String MODIFY_CHAR = "2323223123";



    public static void main(String[] args) {
        File files = new File("D:\\demo");
        modifyName(files);
    }



    public static void modifyName(File files){
        if(files.isDirectory()){
            File[] fileArray = files.listFiles();
            for (File file:fileArray){
                modifyName(file);
            }
        }else{
            if(files.getName().contains(MODIFY_CHAR)){
                String newName = files.getName().replace(MODIFY_CHAR,"");
                System.out.println("file new name :"+new File(files.getParentFile()+"/"+newName).getName());
                files.renameTo(new File(files.getParentFile()+"/"+newName));
            }
        }

    }
}
