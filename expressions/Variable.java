package expressions;
import lombok.AllArgsConstructor;
import program.*;

@AllArgsConstructor
public class Variable implements Expression{

    private final Character name;

    @Override
    public int execute(ExecutionState state) {
        return(state.getValue(name));
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
