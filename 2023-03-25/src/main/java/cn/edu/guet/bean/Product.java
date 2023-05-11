package cn.edu.guet.bean;

/**
 * @Author liwei
 * @Date 2023/3/12 13:11
 * @Version 1.0
 */
public class Product {

    private int id;
    private String name;
    private Float price;
    private String type;
    private int number;
    private String path;

    public Product(int id, String name, Float price, String type, int number, String path) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.number = number;
        this.path = path;
    }
    public Product(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath(){
        return path;
    }
}
