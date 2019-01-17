package java2.shoppinglist.services.shoppinglists.getAll;

import java2.shoppinglist.database.ShoppingListRepository;
import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.services.ShoppingListError;
import java2.shoppinglist.services.shoppinglists.getAll.GetAllShoppingListsRequest;
import java2.shoppinglist.services.shoppinglists.getAll.GetAllShoppingListsResponse;
import java2.shoppinglist.services.shoppinglists.getAll.GetAllShoppingListsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class GetAllShoppingListsService {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private GetAllShoppingListsValidator validator;

    public GetAllShoppingListsResponse execute(GetAllShoppingListsRequest request) {

        List<ShoppingList> shoppingLists = shoppingListRepository.findShoppingLists(request.getUser());
        List<ShoppingListError> shoppingListErrors = validator.validate(request);
        if (!shoppingListErrors.isEmpty()) {
            return new GetAllShoppingListsResponse(shoppingLists, shoppingListErrors);
        }
        return new GetAllShoppingListsResponse(shoppingLists, shoppingListErrors);

    }

}