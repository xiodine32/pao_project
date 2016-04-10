package game.interfaces;

/**
 * pao_project - xiodine.
 * 4/10/2016
 */
public interface Observer<State, Return> {

    boolean respondsTo(State state);

    void addEventListener();

    void fired(State eventType, Return returnType);

    void removeEventListener();
}
