package dev.dankom.gp;

import dev.dankom.gp.exception.GPException;
import dev.dankom.gp.input.Input;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Game implements Runnable {

    private final String NAME = "Galactic Prime", VERSION = "B1", AUTHOR = "Dankom";

    private final int width = 1280;
    private final int height = 720;

    private Thread thread;
    private boolean running = false;

    private long window;

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Game");
        thread.start();
    }

    @Override
    public void run() {
        init();
        while (running) {
            update();
            render();

            if (glfwWindowShouldClose(window)) {
                running = false;
            }
        }
    }

    private void init() {
        if (!glfwInit()) {
            try {
                throw new GPException("Failed to init GLFW", getClass());
            } catch (GPException e) {
                e.printStackTrace();
            }
        }

        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        window = glfwCreateWindow(width, height, NAME + " (" + VERSION + ")", glfwGetPrimaryMonitor(), GLFW_FALSE);
        if (window == NULL) {
            try {
                throw new GPException("Could not create window", getClass());
            } catch (GPException e) {
                e.printStackTrace();
            }
        }
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (int) ((vidMode.width() - window) / 2), (int) ((vidMode.height() - window) / 2));

        glfwSetKeyCallback(window, new Input());

        glfwMakeContextCurrent(window);
        glfwShowWindow(window);

        GL.createCapabilities();

        glClearColor(1.0f, 0.0f, 1.0f, 1.0f);
        glEnable(GL_DEPTH_TEST);
        System.out.println("OpenGL: " + glGetString(GL_VERSION));
    }

    private void update() {
        glfwPollEvents();
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glfwSwapBuffers(window);
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.start();
    }
}
