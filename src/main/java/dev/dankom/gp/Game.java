package dev.dankom.gp;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;

import java.nio.ByteBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Game implements Runnable {

    private final String NAME = "Galactic Prime", VERSION = "B1", AUTHOR = "Dankom";

    private int width = 1280, height = 720;

    private Thread thread;
    private boolean running = false;

    private long window;

    public Game() {
        start();
    }

    public synchronized void start() {
        running = false;
        thread = new Thread(this, "Game");
        thread.start();
    }

    @Override
    public void run() {
        init();
        while(running) {
            update();
            render();
        }
    }

    private void init() {
        if (glfwInit()) {

        }

        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        window = glfwCreateWindow(width, height, NAME + " (" + VERSION + ") by " + AUTHOR, NULL, NULL);

        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (int) ((vidMode.width() - window) / 2), (int) ((vidMode.height() - window) / 2));
    }

    private void update() {

    }

    private void render() {

    }

    public static void main(String[] args) {
        Game g = new Game();
    }
}
