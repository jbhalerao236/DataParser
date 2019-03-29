import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private List<State> states;

    public DataManager(List<State> states) {
        this.states = states;
    }
    public DataManager() {
        this.states = new ArrayList<State>();
    }
    public void addState (State s){
        states.add(s);
    }
    public State removeState (int index){
        return states.remove(index);
    }
    public void removeState (State s){
        states.remove(s);
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }
}
