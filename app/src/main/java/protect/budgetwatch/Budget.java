package protect.budgetwatch;

public class Budget {
    public final String name;
    public final int max;
    public final int current;
    public final boolean canView;

    public Budget(final String name, final int max, final int value, final int canView) {
        this.name = name;
        this.max = max;
        this.current = value;
        this.canView = canView == 1;
    }
}
