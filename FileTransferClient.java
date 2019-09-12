import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class FileTransferClient { 
    
    public static void main(String[] args) throws Exception {

        //Initialize Sockets
        Socket ssock = new Socket("localhost", 1234);
        
        //The InetAddress specification
        InetAddress IA = InetAddress.getByName("localhost"); 
        
        //Specify the file
        File file = new File("/media/axcel/kuliah/Documents/c#/multithread/final-project-of-computer-network-practicum/filegede.iso");
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis); 
          
        //Get socket's output stream
        OutputStream os = ssock.getOutputStream();
                
        //Read File Contents into contents array 
        byte[] contents;
        long fileLength = file.length(); 
        long current = 0;
         
        long start = System.nanoTime();
        while(current!=fileLength){ 
            int size = 1024* 500000;
            if(fileLength - current >= size)
                current += size;    
            else{ 
                size = (int)(fileLength - current); 
                current = fileLength;
            } 
            contents = new byte[size]; 
            bis.read(contents, 0, size); 
            os.write(contents);
            System.out.print("Sending file ... "+(current*100)/fileLength+"% complete!");
        }   
        
        os.flush(); 
        //File transfer done. Close the socket connection!
        ssock.close();
        System.out.println("File sent succesfully!");
    }
}