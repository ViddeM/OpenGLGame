package vm.game.engine;

import vm.game.engine.render.RenderHandler;

public class EngineHandler {
    private RenderHandler renderer;

    public EngineHandler() {
        renderer = new RenderHandler();
        renderer.run();
        /*
        renderer = new Thread(new RenderHandler(), "Renderer");
        renderer.start();
        */
    }
}
