package resource;

import dao.ShoppingDAO;
import domain.ShoppingItem;
import org.jooby.Jooby;
import org.jooby.Status;

public class ShoppingListResource extends Jooby {

	public ShoppingListResource(ShoppingDAO dao) {

		path("/api/shopping", () -> {

			get(() -> {
				System.out.println("\n** GET Collection called on REST service **");
				return dao.getList();
			});

			post((req, rsp) -> {
				ShoppingItem item = req.body().to(ShoppingItem.class);
				System.out.println("\n** POST called on REST service: **");
				System.out.println(item);
				dao.addItem(item);
				rsp.status(Status.CREATED).send(item);
			});

		});

	}

}
