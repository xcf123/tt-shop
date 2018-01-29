/*import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpClientTest {
    @Test
    public void ftpClient() throws IOException {
        //创建客户端
        FTPClient ftpClient=new FTPClient();
        //连接客户端
        ftpClient.connect("106.14.165.2",21);
        //登录
        ftpClient.login("ftpuser","xcf123");
        //读取文件
        FileInputStream fs=new FileInputStream(new File("C:\\Documents\\Desktop\\girl.jpg"));
        //配置上传参数
        ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //上传文件
        ftpClient.storeFile("g.jpg",fs);

        fs.close();
        ftpClient.logout();


    }
}*/
