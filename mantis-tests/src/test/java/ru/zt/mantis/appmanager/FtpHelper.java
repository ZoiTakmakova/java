package ru.zt.mantis.appmanager;

public class FtpHelper {

  public final ApplicationManager app;
  private FTPClient ftp;

  public FtpHelper(ApplicationManager app){
    this.app = app;
    ftp = new FTPClient();
  }

  public void upload(File file,String tatget, String backup){
    ftp.connect(app.getProperty("ftp.host"));
    ftp.login(app.getProperty("ftp.login"),app)
  }
}
