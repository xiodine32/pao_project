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
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private long window = 0;
    private Screen runningScreen = null;
    private KeyboardEventListener keyboardEventListener;
    private boolean running;

    private void initGLFW() {
        if (glfwInit() != GLFW_TRUE)
            throw new IllegalStateException("GLFW no init");
        window = glfwCreateWindow(WIDTH, HEIGHT, "Tankies", 0, 0);
        if (window == NULL)
            throw new IllegalStateException("GLFW no screen");

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);

        keyboardEventListener = new KeyboardEventListener();

        glfwSetKeyCallback(window, keyboardEventListener);
    }

    private void initOpenGL() {
        GL.createCapabilities();
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        initOpenGLProjection();
        initOpenGLModelView();
    }

    private void initOpenGLModelView() {
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }

    private void initOpenGLProjection() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glViewport(0, 0, WIDTH, HEIGHT);

        double fH = Math.tan(90.0 / 360 * Math.PI) * 0.1;
        double fW = fH * (WIDTH / HEIGHT);
        glFrustum(-fW, fW, -fH, fH, 0.1, 100);
    }

    public void run(Screen startScreen) {

        this.runningScreen = startScreen;
        this.runningScreen.load(this);
        this.runningScreen.bindKeys(keyboardEventListener);

        double lastTime = glfwGetTime();
        running = true;
        while (running) {
            if (glfwWindowShouldClose(window) != GLFW_FALSE)
                running = false;
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            while (glfwGetTime() - lastTime > FPS) {
                lastTime += FPS;
                if (runningScreen != null)
                    runningScreen.tick();
            }


            if (runningScreen != null)
                runningScreen.draw();

            try {
                Thread.sleep((long) (1000 * FPS) / 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    @Override
    public void stopRunning() {
        running = false;
    }

    @Override
    public void init() {
        initGLFW();
        initOpenGL();
    }

    @Override
    public void dispose() {
        if (this.runningScreen != null)
            this.runningScreen.unload(this);
        glfwDestroyWindow(window);
    }

    @Override
    public void changeScreen(Screen newScreen) {

        this.runningScreen.unbindKeys(keyboardEventListener);
        this.runningScreen.unload(this);

        this.runningScreen = newScreen;

        this.runningScreen.load(this);
        this.runningScreen.bindKeys(keyboardEventListener);
    }
}
