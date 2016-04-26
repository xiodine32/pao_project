package game.engine;

import game.interfaces.Engine;
import game.interfaces.Screen;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * pao_project - xiodine.
 * 3/28/2016
 */
public class SimpleEngine implements Engine {

    private static final double FPS = 1 / 60.0;

    //TODO: Create ThreadedEngine

    private long window = 0;
    private Screen runningScreen = null;

    private void initGLFW() {
        if (glfwInit() != GLFW_TRUE)
            throw new IllegalStateException("GLFW no init");
        window = glfwCreateWindow(800, 600, "Tankies", 0, 0);
        if (window == NULL)
            throw new IllegalStateException("GLFW no screen");

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
    }

    private void initOpenGL() {
        GL.createCapabilities();
        glEnable(GL_BLEND);
        glEnable(GL_TEXTURE_2D);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_DEPTH_TEST);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glViewport(0, 0, 800, 600);

        double fH = Math.tan(90.0 / 360 * Math.PI) * 0.1;
        double fW = fH * (800 / 600);
        glFrustum(-fW, fW, -fH, fH, 0.1, 100);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }


    public void run(Screen startScreen) {

        this.runningScreen = startScreen;
        this.runningScreen.load();

        double lastTime = glfwGetTime();
        
        while (glfwWindowShouldClose(window) == GLFW_FALSE) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            if (runningScreen != null)
                runningScreen.tick();

            if (runningScreen != null)
                runningScreen.draw();

            try {
                Thread.sleep((long) (1000 * FPS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    @Override
    public void init() {
        initGLFW();
        initOpenGL();
    }

    @Override
    public void dispose() {
        if (this.runningScreen != null)
            this.runningScreen.unload();
        glfwDestroyWindow(window);
    }

    @Override
    public void changeScreen(Screen newScreen) {

        this.runningScreen.unload();

        this.runningScreen = newScreen;

        this.runningScreen.load();
    }
}
