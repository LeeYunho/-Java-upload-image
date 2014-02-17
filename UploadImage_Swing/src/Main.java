import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;


public class Main extends JFrame{
 
    JButton btn;
    String filePath;
     
    Main() {
        super("FileUpload"); 
         
        setSize(300,300);
        setLocation(500, 300);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
         
        btn = new JButton("FileUpload");
        btn.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                JFileChooser fileChooser = new JFileChooser();
                int ret = fileChooser.showOpenDialog(null);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    filePath = fileChooser.getSelectedFile().getPath();
                    File file = new File(filePath);
                     
                    try {
                        HttpClient client = new DefaultHttpClient();
                        String url = "http://www.***.com/***.php";
                        HttpPost post = new HttpPost(url);
                        FileBody image = new FileBody(file);
                        MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
                        reqEntity.addPart("uploadImage", image);
                        post.setEntity(reqEntity);
                        HttpResponse response = client.execute(post);
                        HttpEntity resEntity = response.getEntity();
                          
                        /*if (resEntity != null)
                        {
                            System.out.println(EntityUtils.toString(resEntity));
                        }*/
                    }
                    catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }
             
        });
         
        add(btn);
        setVisible(true);
    }
     
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Main();
    }
}
