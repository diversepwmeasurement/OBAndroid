package com.omni.wallet.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.omni.wallet.listItems.BackupFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FilesUtils {
    private String path;
    
    public static List<BackupFile> getDirectoryAndFile (String path, Context context){
        List <BackupFile> fileInfoList = new ArrayList<>();
        File file = new File(path);
        File[] tempList = file.listFiles();
            for(int i = 0;i<tempList.length;i++){
                String filename = tempList[i].getName();
                long lastEdit = tempList[i].lastModified();
                String lastEditTime = TimeFormatUtil.formatTimeAndDateLong(lastEdit/1000,context);
                BackupFile backupFile = null;
                if(!filename.equals("Android")){
                    if(tempList[i].isDirectory()){
                        backupFile = new BackupFile(false,"directory",filename,lastEditTime,file);
                    }else{
                        if(filename.endsWith(".db")){
                            backupFile = new BackupFile(false,"db",filename,lastEditTime,file);
                        }else if(filename.endsWith(".channelBack")){
                            backupFile = new BackupFile(false,"channelBack",filename,lastEditTime,file);
                        }else {
                            backupFile = new BackupFile(false,"else",filename,lastEditTime,file);
                        }
                    }
                    fileInfoList.add(backupFile);
                }
            }
        
        return fileInfoList;
    }

    public static List<BackupFile> getDirectory (String path, Context context){
        List <BackupFile> fileInfoList = new ArrayList<>();
        File file = new File(path);
        File[] tempList = file.listFiles();
        for(int i = 0;i<tempList.length;i++){
            String filename = tempList[i].getName();
            long lastEdit = tempList[i].lastModified();
            String lastEditTime = TimeFormatUtil.formatTimeAndDateLong(lastEdit/1000,context);
            BackupFile backupFile = null;
            int childCount = tempList[i].listFiles().length;
            if(!filename.equals("Android")){
                if(tempList[i].isDirectory()){
                    backupFile = new BackupFile(false,"directory",filename,lastEditTime,file,childCount);
                }
                fileInfoList.add(backupFile);
            }
        }
        return fileInfoList;
    }
}