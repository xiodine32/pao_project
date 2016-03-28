package game;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * pao_project - xiodine.
 * 3/28/2016
 */
public class Engine {
    private long window;

    public Engine() {
        initGLFW();

        initOpenGL();
    }

    private void initGLFW() {
        if (glfwInit() != GLFW_TRUE)
            throw new IllegalStateException("GLFW no init");
        window = glfwCreateWindow(800, 600, "Game", 0, 0);
        if (window == NULL)
            throw new IllegalStateException("GLFW no screen");

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
    }

    private void initOpenGL() {
        GL.createCapabilities();
    }

    public void run() {
        while (glfwWindowShouldClose(window) == GLFW_FALSE) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);


            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }
}
