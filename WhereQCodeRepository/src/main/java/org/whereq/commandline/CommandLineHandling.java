package org.whereq.commandline;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class CommandLineHandling {

	// The installation path of APReSaver command
	private String apReSaveCmd;

	// The path for compare report generation
	private String shareFolder;

	private String fileName = null;
	private String reSavedFileName = null;

	private File pdfFile = null;
	private String contentType = null;
	private int contentLength = 0;

	public static void main(String[] argv) {
		CommandLineHandling ch = new CommandLineHandling();
		try {
			ch.reSavePDF("");
			// ch.test();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void test() {
		try {
			Runtime rt = Runtime.getRuntime();
			// Process pr = rt.exec("cmd /c dir");
			Process pr = rt.exec("d:\\temp\\test.bat");

			BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

			String line = null;

			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}

			int exitVal = pr.waitFor();
			System.out.println("Exited with error code " + exitVal);

		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * Invoke the command line of org.whereq.http.upload.server re-saver to
	 * re-save PDF files
	 * 
	 * @param pdfFileName
	 *            The file name of the PDF file needed to be re-saved.
	 * 
	 */
	public void reSavePDF(String pdfFileName) throws Exception {
		Runtime runtime = Runtime.getRuntime();
		Process proc = null;

		StringBuffer cmdSbf = new StringBuffer(64);
		cmdSbf.append("cmd.exe /c ");
		cmdSbf.append(apReSaveCmd);
		cmdSbf.append(" ");
		cmdSbf.append(this.shareFolder);
		cmdSbf.append(File.separatorChar);
		cmdSbf.append(pdfFileName);
		cmdSbf.append(" /X");

		// cmdSbf.append(apReSaveCmd);
		// cmdSbf.append(" ");
		// cmdSbf.append(this.shareFolder);
		// cmdSbf.append(File.separatorChar);
		// cmdSbf.append(pdfFileName);

		System.out.println("cmd=" + cmdSbf.toString());

		try {
			proc = runtime.exec(cmdSbf.toString());
			if (proc.waitFor() != 0) {
				throw new Exception();
			}

		} catch (Exception e) {
			throw new Exception();
		}

		init(pdfFileName);
	}

	private void init(String pdfFileName) {
		StringBuffer sbf = new StringBuffer(64);
		sbf.append(this.shareFolder);
		sbf.append(File.separatorChar);
		sbf.append(pdfFileName);
		this.fileName = sbf.toString();

		this.reSavedFileName = sbf.append("_ap").toString();

		System.out.println("this.fileName=" + this.fileName);
		System.out.println("this.reSavedFileName=" + this.reSavedFileName);
		this.pdfFile = new File(this.reSavedFileName);
		this.contentType = "application/pdf";
		this.contentLength = (int) this.pdfFile.length();
	}

	public void cleanTemporaryFiles() {
		File tempFile = new File(this.fileName);
		tempFile.delete();

		File savedFile = new File(this.reSavedFileName);
		savedFile.delete();
	}

	public boolean deleteFile(String filePath) {
		File f = new File(filePath);
		return f.delete();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getContentLength() {
		return contentLength;
	}

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	public File getPdfFile() {
		return pdfFile;
	}

	public void setPdfFile(File pdfFile) {
		this.pdfFile = pdfFile;
	}
}