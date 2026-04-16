package org.irri.iric.portal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/*
Create Zip File From Multiple Files using ZipOutputStream Example
This Java example shows how create zip file containing multiple files
using Java ZipOutputStream class.
*/

import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class CreateZipMultipleFiles {

	private String zipFile; 
	private String[] sourceFiles;

	
	
	public CreateZipMultipleFiles(String zipFile, String[] sourceFiles) {
		super();
		this.zipFile = zipFile;
		this.sourceFiles = sourceFiles;
	}
	
	public CreateZipMultipleFiles(String zipFile, List listFiles) {
		super();
		sourceFiles = new String[listFiles.size()];
		this.zipFile = zipFile;
		
		Iterator<String> itFilename = listFiles.iterator();
		int icount=0;
		while(itFilename.hasNext()) {
			sourceFiles[icount] = itFilename.next();
			icount++;
		}
	}


	public void create() {
		create(false);
	}
	public void create(boolean includePath) {
		create(includePath,false);
	}
	public void create(boolean includePath, boolean includeParentName)
	{
		try
        {
                //String zipFile = "C:/FileIO/zipdemo.zip";
                //String[] sourceFiles = {"C:/sourcefile1.doc", "C:/sourcefile2.doc"};
               
                //create byte buffer
                byte[] buffer = new byte[1024];
               
                /*
                 * To create a zip file, use
                 *
                 * ZipOutputStream(OutputStream out)
                 * constructor of ZipOutputStream class.
                */
                 
                 //create object of FileOutputStream
                 FileOutputStream fout = new FileOutputStream(zipFile);
                 
                 //create object of ZipOutputStream from FileOutputStream
                 ZipOutputStream zout = new ZipOutputStream(fout);
                 
                 for(int i=0; i < sourceFiles.length; i++)
                 {
                       
                        AppContext.debug("Adding " + sourceFiles[i]);
                        try {
                        
                        //create object of FileInputStream for source file
                        FileInputStream fin = new FileInputStream(sourceFiles[i]);

                        /*
                         * To begin writing ZipEntry in the zip file, use
                         *
                         * void putNextEntry(ZipEntry entry)
                         * method of ZipOutputStream class.
                         *
                         * This method begins writing a new Zip entry to
                         * the zip file and positions the stream to the start
                         * of the entry data.
                         */

                        if(includePath) {
                        	zout.putNextEntry(new ZipEntry(sourceFiles[i]));
                        } else  if (includeParentName){
                        	File addfile=new File(sourceFiles[i]);
                        	zout.putNextEntry(new ZipEntry(addfile.getParentFile().getName() +"/"+addfile.getName()));
                        }
                        else { 
                        	File addfile=new File(sourceFiles[i]);
                        	zout.putNextEntry(new ZipEntry(addfile.getName()));
                        }
                        
                        
                        //zout.putNextEntry(new ZipEntry(sourceFiles[i]));

                        /*
                         * After creating entry in the zip file, actually
                         * write the file.
                         */
                        int length;

                        while((length = fin.read(buffer)) > 0)
                        {
                           zout.write(buffer, 0, length);
                        }

                        /*
                         * After writing the file to ZipOutputStream, use
                         *
                         * void closeEntry() method of ZipOutputStream class to
                         * close the current entry and position the stream to
                         * write the next entry.
                         */

                         zout.closeEntry();

                         //close the InputStream
                         fin.close();
                         
                        } catch(Exception ex) {
                        	ex.printStackTrace();
                        }
                       
                 }
               
                 
                  //close the ZipOutputStream
                  zout.close();
                 
                  AppContext.debug("Zip file has been created!");
       
        }
        catch(IOException ioe)
        {
        	AppContext.debug("IOException :" + ioe);
        }
       
}

	public static void addFilesToExistingZip(File zipFile,
	         File file) throws IOException {

		File files[]=new File[]{file};
		addFilesToExistingZip(zipFile, files);
	}
	
	public static void addFilesToExistingZip(File zipFile,
	         File[] files) throws IOException {
	        // get a temp file
	    File tempFile = File.createTempFile(zipFile.getName(), null);
	        // delete it, otherwise you cannot rename your existing zip to it.
	    tempFile.delete();

	    boolean renameOk=zipFile.renameTo(tempFile);
	    if (!renameOk)
	    {
	        throw new RuntimeException("could not rename the file "+zipFile.getAbsolutePath()+" to "+tempFile.getAbsolutePath());
	    }
	    byte[] buf = new byte[1024];

	    ZipInputStream zin = new ZipInputStream(new FileInputStream(tempFile));
	    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));

	    ZipEntry entry = zin.getNextEntry();
	    while (entry != null) {
	        String name = entry.getName();
	        boolean notInFiles = true;
	        for (File f : files) {
	            if (f.getName().equals(name)) {
	                notInFiles = false;
	                break;
	            }
	        }
	        
	        if (notInFiles) {
	            // Add ZIP entry to output stream.
	            out.putNextEntry(new ZipEntry("word/document.xml"));
	            // Transfer bytes from the ZIP file to the output file
	            int len;
	            while ((len = zin.read(buf)) > 0) {
	                out.write(buf, 0, len);
	            }
	        }
	        entry = zin.getNextEntry();
	    }
	    // Close the streams        
	    zin.close();
	    // Compress the files
	    for (int i = 0; i < files.length; i++) {
	        InputStream in = new FileInputStream(files[i]);
	        // Add ZIP entry to output stream.
	        out.putNextEntry(new ZipEntry(files[i].getName()));
	        // Transfer bytes from the file to the ZIP file
	        int len;
	        while ((len = in.read(buf)) > 0) {
	            out.write(buf, 0, len);
	        }
	        // Complete the entry
	        out.closeEntry();
	        in.close();
	    }
	    // Complete the ZIP file
	    out.close();
	    tempFile.delete();
	}
	
	
	 public static void zipDir(String zipFileName, String dir) throws Exception {
		    File dirObj = new File(dir);
		    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
		    AppContext.debug("Creating : " + zipFileName);
		    File filepref=new File(dir);
		    addDir(dirObj, out, filepref.getAbsolutePath());
		    out.close();
		  }

	 	
		  static void addDir(File dirObj, ZipOutputStream out , String prefix) throws IOException {
		    File[] files = dirObj.listFiles();
		    byte[] tmpBuf = new byte[1024];

		    for (int i = 0; i < files.length; i++) {
		      if (files[i].isDirectory()) {
		        addDir(files[i], out, prefix);
		        continue;
		      }
		      FileInputStream in = new FileInputStream(files[i].getAbsolutePath());
		      
		      String filename=files[i].getAbsolutePath().replace( prefix,"");
		      /*
		      if(!files[i].isDirectory()) {
		    	  if(filename.startsWith("\\")) filename=filename.substring(1);
		      }
		      */
		      if(filename.startsWith("\\")) filename=filename.substring(1);
		      
		      AppContext.debug(" Adding: " + files[i].getAbsolutePath() + " with name " + filename );

		      out.putNextEntry(new ZipEntry(filename));
		      
		    	  //out.putNextEntry(new ZipEntry(files[i].getAbsolutePath().replace( prefix,"") ));
		      int len;
		      while ((len = in.read(tmpBuf)) > 0) {
		        out.write(tmpBuf, 0, len);
		      }
		      out.closeEntry();
		      in.close();
		    }
		  }

		  
}