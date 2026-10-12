package dao.optional;
import dao.Product;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class ProductMemoryDao implements Dao<Product> {

    private final Map<Long, Product> products = new HashMap<>();

    @Override
    public Optional<Product> get(long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List getAll() {
        return products.values().stream().collect(Collectors.toCollection(ArrayList::new));
    }


    @Override
    public void save(Product p) {
        if (p == null)
            throw new IllegalArgumentException();
        if (products.containsKey(p.getId()))
            throw new IllegalArgumentException();

        p = products.put(Long.valueOf(p.getId()),p);


    }

    @Override
    public void update(Product p, String[] params) {
        try {
            Integer id = Integer.parseInt(params[0]);
            String name = params[1];
            Double price = Double.parseDouble(params[2]);
            Integer quantity = Integer.parseInt(params[3]);


            products.put(Long.valueOf(id), new Product(id, name, price, quantity));
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void delete(Product p) {
        if (p == null || !products.containsKey((long) p.getId())) {
            throw new IllegalArgumentException();
        }
        products.remove((long) p.getId());
    }
}

