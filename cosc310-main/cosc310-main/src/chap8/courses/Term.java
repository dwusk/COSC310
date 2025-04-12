package chap8.courses;

public class Term {
    protected String name;
    protected String year;

    public Term(String name, String year) {
        this.name = name;
        this.year = year;
    }

    @Override
    public String toString() {
        return name + " " + year;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }
}