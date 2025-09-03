public final class Student {
    private final long id;
    private final String fullName;
    private final String bloodGroup;
    private final float cgpa;

    public Student(long id, String fullName, String bloodGroup, float cgpa) {
        this.id = id;
        this.fullName = fullName;
        this.bloodGroup = bloodGroup;
        this.cgpa = cgpa;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void print() {
        System.out.println("ID: " + id);
        System.out.println("Full Name: " + fullName);
        System.out.println("Blood Group: " + bloodGroup);
        System.out.println("CGPA: " + cgpa);
        System.out.println("-----------------------");
    }

    public boolean matches(String query) {
        String[] nameParts = fullName.trim().split(" ");
        String lastName = nameParts.length > 1 ? nameParts[nameParts.length - 1] : "";
        if (query.equals(bloodGroup) || query.equals(lastName)) {
            return true;
        }
        return false;

    }
}
