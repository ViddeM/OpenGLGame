package java.vm.game.engine;

import java.vm.game.engine.render.RenderHandler;

public class EngineHandler {
    private Thread renderer;

    public EngineHandler() {
        renderer = new Thread(new RenderHandler(), "Renderer");
        renderer.start();
    }
}
