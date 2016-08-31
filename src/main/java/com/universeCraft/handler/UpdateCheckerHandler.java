package com.universeCraft.handler;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import com.universeCraft.main.UniverseCraft;

public class UpdateCheckerHandler implements Runnable{
	
    private static boolean isLatestVersion = false;
    private static String latestVersion = "";

    @Override
    public void run(){
        InputStream in = null;
        try {
            in = new URL("https://www.dropbox.com/s/774elaxyi82qnwy/UniverseCraftModVersions.txt?dl=0").openStream();
        } 
        catch 
        (MalformedURLException e) {
            e.printStackTrace();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        try 
        {
            latestVersion = IOUtils.readLines(in).get(0);
        } 
        catch (IOException e) {
            e.printStackTrace();
        } 
        finally {
            IOUtils.closeQuietly(in);
        }
        System.out.println("Latest mod version = "+latestVersion);
        isLatestVersion = UniverseCraft.VERSION.equals(latestVersion);
        System.out.println("Are you running latest version = "+isLatestVersion);
    }
    
    public boolean isLatestVersion()
    {
     return isLatestVersion;
    }
    
    public String getLatestVersion()
    {
     return latestVersion;
    }
}
