package repository.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import repository.ProductDao;

import domain.Product;

public class JdbcProductDao extends SimpleJdbcDaoSupport implements ProductDao{

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
	
	public List<Product> getProductList() {
		logger.info("Getting products!");
		List<Product> products = getSimpleJdbcTemplate().query("select id, description, price from products", new ProductMapper());
		logger.info("Returned: " + products.size() + " products");
		return products;
	}

	public void saveProduct(Product prod) {
		logger.info("Saving product: " + prod.getDescription());
        int count = getSimpleJdbcTemplate().update("update products set description = :description, price = :price where id = :id",new MapSqlParameterSource().addValue("description", prod.getDescription())
                .addValue("price", prod.getPrice())
                .addValue("id", prod.getId()));
        logger.info("Rows affected: " + count);
		
	}
	
	public void newProduct(Product prod) {
		int count = getSimpleJdbcTemplate().update("INSERT INTO products (description, price) values (:description, :price)",new MapSqlParameterSource()
			.addValue("description", prod.getDescription())
			.addValue("price", prod.getPrice())			
			);
		logger.info(count + " products have been inserted");
	}
	
	public Product getById(long userId) {
		Product product = null;
		logger.info("Getting product!");
		List<Product> products = getSimpleJdbcTemplate().query("select id, description, price from products where id = :id", new ProductMapper(), new MapSqlParameterSource()
		.addValue("id", userId));
		if(products != null && !products.isEmpty()){
			product = products.get(0);
			logger.info("returning Product " + product.toString());
		}
		return product;
	}
	
	public boolean destroy(Product product) {
		int count = 0;
		logger.info("Destroying Product" + product.toString());
		count = getSimpleJdbcTemplate().update("DELETE FROM products WHERE id = :id",new MapSqlParameterSource()
		.addValue("id", product.getId()));
		
		if(count > 0)
			return true;
		return false;
	}

	public boolean destroy(long id) {
		int count = 0;
		logger.info("Destroying Product with ID = " + id);
		count = getSimpleJdbcTemplate().update("DELETE FROM products WHERE id = :id",new MapSqlParameterSource()
		.addValue("id", id));
		
		if(count > 0)
			return true;
		return false;
	}

	private static class ProductMapper implements ParameterizedRowMapper<Product> {
		
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product prod = new Product();
			prod.setId(rs.getInt("id"));
			prod.setDescription(rs.getString("description"));
			prod.setPrice(new Double(rs.getDouble("price")));
			return prod;
		}
	}
}