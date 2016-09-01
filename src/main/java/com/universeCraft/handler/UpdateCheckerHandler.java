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
            in = new URL("https://raw.githubusercontent.com/darthbob555/UniverseCraft/master/CurrentVersion.txt").openStream();
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
        isLatestVersion = UniverseCraft.VERSION.equals(latestVersion);
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
