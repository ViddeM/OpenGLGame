package java.vm.game.engine.render.graphics;

import java.vm.game.engine.maths.Vector3f;

public class Vertex {
    private Vector3f position;

    public Vertex(Vector3f position) {
        this.position = position;
    }

    public Vector3f getPosition() {
        return position;
    }
}
