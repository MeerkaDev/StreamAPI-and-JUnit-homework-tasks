import java.util.ArrayList;
import java.util.List;

public class StreamAPITasks {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();

        products.add(new Product("Яблоко", 450, ProductType.FRUIT));
        products.add(new Product("Банан", 700, ProductType.FRUIT));
        products.add(new Product("Морковь", 300, ProductType.VEGETABLE));
        products.add(new Product("Помидор", 600, ProductType.VEGETABLE));
        products.add(new Product("Апельсин", 800, ProductType.FRUIT));
        products.add(new Product("Огурец", 400, ProductType.VEGETABLE));
        products.add(new Product("Виноград", 1200, ProductType.FRUIT));
        products.add(new Product("Картофель", 200, ProductType.VEGETABLE));
        products.add(new Product("Говядина", 4500, ProductType.MEAT));
        products.add(new Product("Курица", 2000, ProductType.MEAT));
        products.add(new Product("Свинина", 3000, ProductType.MEAT));
        products.add(new Product("Молоко", 500, ProductType.DAIRY));
        products.add(new Product("Сыр", 1500, ProductType.DAIRY));
        products.add(new Product("Йогурт", 800, ProductType.DAIRY));

        // Задания необходимо раскомментирують и запускать по очереди

        // 1) Найдите все фрукты, у которых цена меньше 1000 тенге, увеличьте цену каждого из них на 20%,
        // отсортируйте их по возрастанию цены и соберите результаты в новый список продуктов.

        /*
        List<Product> task1List = products.stream()
                .filter(product -> product.getType() == ProductType.FRUIT && product.getPrice() < 1000)
                .peek(product -> product.setPrice((int) (product.getPrice() * 1.2)))
                .sorted((product1, product2) -> Integer.compare(product1.getPrice(), product2.getPrice()))
                .toList();
        task1List.stream()
                .forEach(product -> System.out.println(product.getName() + " - " + product.getPrice()));
        */

        // 2) Найдите общую стоимость всех мясных и молочных продуктов,
        // цена которых после скидки в 10% больше 2000 тенге, отсортируйте их по убыванию цены
        // и выведите названия вместе с их новыми ценами.

        /*
        int total;
        List<Product> task2List = products.stream()
                .filter(product -> (product.getType() == ProductType.MEAT || product.getType() == ProductType.DAIRY) &&
                        product.getPrice() * 0.9 > 2000)
                .sorted((product1, product2) -> Integer.compare(product2.getPrice(), product1.getPrice()))
                .toList();
        total = task2List.stream()
                .mapToInt(product -> (int) (product.getPrice() * 0.9))
                .sum();

        task2List.stream()
                .forEach(product -> System.out.println(product.getName() + " - " + (int) (product.getPrice() * 0.9)));

        System.out.println("Общая стоимость - " + total);
        */
    }
}

class Product {
    private String name;
    private int price;
    private ProductType type;

    public Product(String name, int price, ProductType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }
}

enum ProductType {
    FRUIT,
    VEGETABLE,
    MEAT,
    DAIRY
}

