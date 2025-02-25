package calculator.utility;

import java.util.logging.Logger;
public class Log {
    private static final Logger logger = Logger.getLogger(Log.class.getName());

    private Log() {
        throw new UnsupportedOperationException("Log는 유틸리티 클래스이므로 외부에서 인스턴스 생성 불가!");
    }

    public static void info(String message) {
        logger.info(message);
    }

}
