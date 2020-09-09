package graphql;

import dao.CatalogueDAO;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class InventoryDataFetcher {

	private final CatalogueDAO dao;

	public InventoryDataFetcher(CatalogueDAO dao) {
		this.dao = dao;
	}

	public DataFetcher getBookByIdFetcher() {
		return (DataFetchingEnvironment dfe) -> {
			String id = dfe.getArgument("id");
			return dao.getById(id);
		};
	}

	public DataFetcher getCatalogueFetcher() {
		return (DataFetchingEnvironment dfe) -> {
			return dao.getCatalogue();
		};
	}

	public DataFetcher getCatalogueByCategoryFetcher() {
		return (DataFetchingEnvironment dfe) -> {
			String category = dfe.getArgument("category");
			return dao.getCategory(category);
		};
	}

	public DataFetcher getReorderFetcher() {
		return (DataFetchingEnvironment dfe) -> {
			return dao.reorder();
		};
	}

}
