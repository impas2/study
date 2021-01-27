public class Cat {

    private String catName = "";
    private int catAge = 0;

    public Cat() {

    }

    public Cat(String name, int age) {
        catName = name;
        catAge = age;
    }

    public void setCanName(String name) {
        catName = name;
    }

    public void setCatAge(int age) {
        catAge = age;
    }

    public String getCatName() {
        return catName;
    }

    public int getCatAge() {
        return catAge;
    }
}
