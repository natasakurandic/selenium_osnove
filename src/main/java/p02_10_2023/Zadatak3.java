package p02_10_2023;
//Napisati program koji
//        Kreirati folder downloads folder u projektu
//        Ucitava stranu https://www.pexels.com/photo/a-woman-holding-a-laptop-in-the-living-room-6585859/
//        Cita href atribut sa glavne slike sa stranice
//        Koristi link iz href atributa za skidanje slike
//        Sliku sacuvajte u folderu downloads pod nazivom woman-holding-laptop.jpeg
//        Koristan link za skidanje fajlova u javi
//        Azurirajte gitignore da ignorise downloads folder

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.time.Duration;

public class Zadatak3 {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.pexels.com/photo/a-woman-holding-a-laptop-in-the-living-room-6585859/");

        String imageUrl = "https://cdn.britannica.com/29/150929-050-547070A1/lion-Kenya-Masai-Mara-National-Reserve.jpg";
        String imagePath = "downloads/lav.jpg";

        try {
            downloadUsingNIO(imageUrl, imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
    public static void downloadUsingStream(String imageUrl, String imagePath) throws IOException {
        URL url = new URL(imageUrl);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(imagePath);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }
    private static void downloadUsingNIO (String imageUrl, String imagePath) throws IOException {
        URL url = new URL(imageUrl);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(imagePath);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }

}
