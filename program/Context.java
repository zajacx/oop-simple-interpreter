package program;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Context {

    // Variable for debugging: a minimal size of the instructions' stack
    // at which this context exists and is stored on contexts' stack.
    @Getter
    private int minStackSize = 0;

    @Getter
    private final Integer[] variables = new Integer[26];

    public Context(int minStackSize){
        this.minStackSize = minStackSize;
    }
}
