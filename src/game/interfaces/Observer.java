package game.interfaces;

/**
 * pao_project - xiodine.
 * 4/10/2016
 */
public interface Observer<State, Return> {

    void addEventListener(State listener);

    void fire(Return returnType);

    void removeEventListener(State listener);
}
