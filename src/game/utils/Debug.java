package game.utils;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

/**
 * pao_project - xiodine.
 * 4/3/2016
 */
public class Debug {

    public static LoggingEnum currentLog = LoggingEnum.LOG;

    public static void l(String s) {
        if (currentLog != LoggingEnum.LOG)
            return;
        System.out.printf("* [%.2f]:%s\n", glfwGetTime(), s);
    }

    public static void w(String s) {
        if (currentLog == LoggingEnum.LOG || currentLog == LoggingEnum.WARN)
            return;
        System.out.printf("# [%.2f]:%s\n", glfwGetTime(), s);
    }

    public static void e(String s) {
        System.out.printf("! [%.2f]:%s\n", glfwGetTime(), s);
        if (currentLog != LoggingEnum.ERROR) {
            Thread.currentThread().interrupt();
        }
    }

    public enum LoggingEnum {
        LOG,
        WARN,
        ERROR
    }
}
