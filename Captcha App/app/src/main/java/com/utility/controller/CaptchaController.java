import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CaptchaController {

    @Autowired
    private DefaultKaptcha captchaProducer;

    @GetMapping("/api/captcha")
    public ResponseEntity<Map<String, String>> getCaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String captchaText = captchaProducer.createText();
        // Save the "captchaText" into session (Client's Cookies).
        request.getSession().setAttribute("captcha", captchaText);
        BufferedImage bi = captchaProducer.createImage(captchaText);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "jpg", baos);
        String base64Image = Base64.getEncoder().encodeToString(baos.toByteArray());

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("captcha", base64Image);
        // Use captchaText to generate image and return back to client.
        return ResponseEntity.ok(responseMap);
    }

    @PostMapping("/api/validateCaptcha")
    public ResponseEntity<Map<String, Object>> validateCaptcha(HttpServletRequest request, @RequestBody Map<String, String> captchaRequest) {
        // Get the "captchaText" from client request cookies.
        String captcha = (String) request.getSession().getAttribute("captcha");
        String captchaInput = captchaRequest.get("captcha");
        Map<String, Object> responseMap = new HashMap<>();

        // Validate the result.
        if (captcha != null && captcha.equals(captchaInput)) {
            responseMap.put("status", "success");
        } else {
            responseMap.put("status", "failure");
        }
        return ResponseEntity.ok(responseMap);
    }
}
