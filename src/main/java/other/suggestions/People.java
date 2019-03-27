package other.suggestions;

/**
 * 建议99：泛型多重界限
 */
class People implements Staff, Passenger {
    @Override
    public int getSalary() {
        return 2000;
    }

    @Override
    public boolean isStanding() {
        return true;
    }
}

interface Staff {
    public int getSalary();
}

interface Passenger {
    public boolean isStanding();
}
