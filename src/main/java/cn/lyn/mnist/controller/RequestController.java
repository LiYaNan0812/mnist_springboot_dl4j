package cn.lyn.mnist.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lyn.mnist.service.ImageService;
import sun.misc.BASE64Decoder;

@Controller
@RequestMapping("/")
public class RequestController {
    @Autowired
    private ImageService imageService;

    @RequestMapping("tranningOfModel")
    public String tranningOfModel(Model model) {
        try {

            String evalStats = imageService.tranningOfModel();
            model.addAttribute("evalStats", evalStats);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "index";
    }

    @RequestMapping("imageRecognition")
    @ResponseBody
    public String imageRecognition(@RequestParam String picture) throws IOException {

        //	System.out.println("//////////////////////"+picture);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(picture);
        //	System.out.println("+++++++++++++"+b);

        return imageService.imageRecognition(bytes);

    }

    @RequestMapping("index")
    public String index() {

        return "index";
    }

    @RequestMapping("show")
    public String show() {
        return "show";
    }
}
