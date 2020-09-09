package resource;

import dao.ShoppingDAO;
import domain.ShoppingItem;
import org.jooby.Jooby;
import org.jooby.Status;

public class ShoppingItemResource extends Jooby {

	public ShoppingItemResource(ShoppingDAO dao) {

		path("/api/shopping/items", () -> {

			delete("/:name", (req, rsp) -> {


				String name = req.param("name").value();
				System.out.println("\n** DELETE called on REST service **");
				System.out.println("Name: " + name);

				if (!dao.exists(name)) {
					rsp.status(Status.NOT_FOUND).send("Item named '" + name + "' does not exist");
				} else {
					ShoppingItem item = dao.getByName(name);
					dao.delete(item);
					rsp.status(Status.NO_CONTENT).send("Deleted");
				}

			});

		});

	}

}
