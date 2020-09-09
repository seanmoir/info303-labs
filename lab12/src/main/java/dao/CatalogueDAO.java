package dao;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import domain.Product;
import domain.StockLevel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CatalogueDAO {

	private static final Map<String, Product> itemsById = new TreeMap<>();
	private static final Multimap<String, Product> itemsByCategory = HashMultimap.create();

	public CatalogueDAO() {
		/*
		 * Some dummy data for testing
		 */
		if (itemsById.isEmpty()) {

			StockLevel s1 = new StockLevel(12.5, 10.0, 20.0, "Wally's Widgets", "kg");
			Product p1 = new Product("WD1234", "Green Widget", "A widget that has gone mouldy.", "Widgets", new BigDecimal("21.43"), s1);
			addItem(p1);

			StockLevel s2 = new StockLevel(10.0, 20.0, 50.0, "Deidres' Doohickies", null);
			Product p2 = new Product("DH8832", "Dodgy Doohicky", "A doohicky that might work, or it might not...", "Doohickies", new BigDecimal("12.32"), s2);
			addItem(p2);
			
			StockLevel s3 = new StockLevel(5.0, 10.0, 50.0, "Wally's Widgets", null);
			Product p3 = new Product("WD3242", "Fuzzy Widget", "A widget that fell in some cat hair.", "Doohickies", new BigDecimal("11.63"), s3);
			addItem(p3);
		}
	}

	/**
	 * Gets all products in the catalogue ordered by ID.
	 *
	 * @return All products ordered by ID.
	 */
	public List<Product> getCatalogue() {
		return new ArrayList<>(itemsById.values());
	}

	/**
	 * Adds a product to the catalogue.
	 *
	 * @param item The product being added.
	 */
	public final void addItem(Product item) {
		itemsById.put(item.getId(), item);
		itemsByCategory.put(item.getCategory(), item);
	}

	/**
	 * Gets a single product that matches the given ID.
	 *
	 * @param id The ID to search for.
	 * @return The product matching the given ID, or null if no match found.
	 */
	public Product getById(String id) {
		return itemsById.get(id);
	}

	/**
	 * Gets a all product in a given category.
	 *
	 * @param category
	 * @return The products in the given category.
	 */	
	public Collection<Product> getCategory(String category) {
		return itemsByCategory.get(category);
	}	
	
	/**
	 * Deletes a product.
	 *
	 * @param id The ID of the product to delete.
	 */
	public void delete(String id) {
		Product p = itemsById.remove(id);
		itemsByCategory.remove(p.getCategory(), p);
	}

	/**
	 * Updates a product (effectively replaces it).
	 *
	 * @param id The ID of the product to replace.
	 * @param updatedProduct The product to replace it with.
	 */
	public void updateItem(String id, Product updatedProduct) {
		itemsById.put(id, updatedProduct);
	}

	/**
	 * Checks if a product exists.
	 *
	 * @param id The ID of the product being checked.
	 * @return <code>true</code> if product exists, <code>false</code> if not.
	 */
	public boolean exists(String id) {
		return itemsById.containsKey(id);
	}

	/**
	 * Find products that are due for reordering.
	 *
	 * @return Products that need to be reordered (their stock level is below the threshold).
	 */	
	public Collection<Product> reorder() {
		return itemsById.values().stream()
			.filter(p -> p.getStockLevel().getCurrentStockLevel() < p.getStockLevel().getReorderThreshold())
			.collect(Collectors.toSet());
	}

}
