import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestShopRepository {

    public Product product = new Product(1, "Тестовый продукт", 100);
    public Product product1 = new Product(2, "Тестовый продукт 1", 200);
    public Product product2 = new Product(3, "Тестовый продукт 2", 300);

    ShopRepository shopRepo = new ShopRepository();

    @Test
    public void testSuccessDeleteProduct() { // Корректность удаления продукта, переданы валидные данные.

        shopRepo.add(product);
        shopRepo.add(product1);
        shopRepo.add(product2);

        shopRepo.removeById(2);

        Product[] ext = {product, product2};
        Product[] act = shopRepo.findAll();

        Assertions.assertArrayEquals(ext, act);

    }

    @Test
    public void testDeleteProductException() { // Удаление продукта, получить исключение.

        shopRepo.add(product);
        shopRepo.add(product1);
        shopRepo.add(product2);

        Assertions.assertThrows(NotFoundException.class, () -> {
            shopRepo.removeById(4);
        });

    }

    @Test
    public void testAlreadyExistsException() { // Добавление продукта, получить исключение.

        shopRepo.add(product);
        shopRepo.add(product1);
        shopRepo.add(product2);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            shopRepo.add(product2);
        });

    }
}
