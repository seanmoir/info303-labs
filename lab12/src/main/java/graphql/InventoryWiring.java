package graphql;

import dao.CatalogueDAO;
import graphql.schema.idl.RuntimeWiring;
import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

public class InventoryWiring {

	private final CatalogueDAO dao;

	public InventoryWiring(CatalogueDAO dao) {
		this.dao = dao;
	}

	public RuntimeWiring getWiring() {
		return RuntimeWiring
			.newRuntimeWiring()
			.type(
				newTypeWiring("InventoryDetails")
					.dataFetcher("product", new InventoryDataFetcher(dao).getBookByIdFetcher())
					.dataFetcher("catalogue", new InventoryDataFetcher(dao).getCatalogueFetcher())
					.dataFetcher("category", new InventoryDataFetcher(dao).getCatalogueByCategoryFetcher())
					.dataFetcher("reorder", new InventoryDataFetcher(dao).getReorderFetcher())
			)
			.build();
	}

}
