import com.back.App;
import com.back.global.AppContext;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class AppTestRunner {


    public static String run(String input){
        Scanner sc = TestUtil.genScanner(input + "\n종료");
        AppContext.init(sc);
        ByteArrayOutputStream outputStream = TestUtil.setOutToByteArray();

        new App().run();;


        return outputStream.toString();



    }
}
