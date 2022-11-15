/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package takehome.card;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class controller {
    
    @RequestMapping("/viewCard")
    public String getData(@RequestParam("name") String nama,
                          @RequestParam("tanggal")@DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                          @RequestParam("gambar") MultipartFile gambar,
                          Model model) throws IOException{
        
        SimpleDateFormat tgl = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        String tanggal = tgl.format(date);
        
        String blob = Base64.encodeBase64String(gambar.getBytes());
        String foto = "data:image/jpeg;base64,".concat(blob);
        
        model.addAttribute("nama", nama);
        model.addAttribute("img", foto);
        model.addAttribute("tanggal", tanggal);
        
        return "view";
    }
    
}