package testModel.model;


import java.util.Objects;

public class TestModel {
    private String Id;
    private String name;
    private int value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestModel testModel = (TestModel) o;
        return value == testModel.value &&
                Objects.equals(Id, testModel.Id) &&
                Objects.equals(name, testModel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, value);
    }

    public TestModel() {
    }

    public TestModel(String id, String name, int value) {
        Id = id;
        this.name = name;
        this.value = value;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

