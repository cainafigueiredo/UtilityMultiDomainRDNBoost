package edu.wisc.cs.will.Utils;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class check_disc {
	public boolean disc_flag=false;
	public boolean checkflagvalues(String DirectoryPath) throws IOException {
		String [] trial=null;
    	trial=DirectoryPath.split("/");
    	System.out.println(trial[trial.length-1]);
    	String[] temp=trial[trial.length-1].split("\\\\");
    	String trial1=trial[trial.length-1].replace("\\"+temp[temp.length-1],"\\");
    	String bkdp= DirectoryPath;
    	String factsdp=DirectoryPath;
    	trial[trial.length-1]=trial[trial.length-1].replace("/","");
    	String prefix = trial[trial.length-1];
    	String bkname=prefix+"/"+temp[temp.length-1]+"_bk.txt";
    	String alterbkpath=factsdp;
    	//FileInputStream fstreamtemp = new FileInputStream(DirectoryPath+bkname);
    	FileInputStream fstreamtemp = new FileInputStream(bkname);
        BufferedReader brtemp = new BufferedReader(new InputStreamReader(fstreamtemp));
        String strLinetemp;
        String[] bkline=null;
        String bkpath=null;
        boolean check=false;
        while ((strLinetemp = brtemp.readLine()) != null && check==false)  
        {
        	if((strLinetemp.contains("import:"))&&(!strLinetemp.contains("//")))
        	{	bkline=strLinetemp.split("/");
        		bkline[1]=bkline[1].replaceAll("\".", "");   
        		bkpath=bkdp.replace(trial[trial.length-1]+"/", trial1+bkline[1]);
        		check=true;
        	}
        	else if((!strLinetemp.contains("import:")))
        	{
        		bkpath=alterbkpath.replace("/","/"+prefix+"_bk.txt");
        		check=true;
        	}
        }
        brtemp.close();

        FileInputStream fstream = new FileInputStream(bkpath);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        while ((strLine = br.readLine()) != null)   {
        	if(strLine.contains("disc") && !strLine.contains("//"))
        		{
        		disc_flag=true;
        		}
        	
        }
        br.close();
        return disc_flag;
	}

}
