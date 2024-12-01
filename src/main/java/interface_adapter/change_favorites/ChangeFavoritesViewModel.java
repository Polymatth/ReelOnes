package interface_adapter.change_favorites;

import interface_adapter.ViewModel;

public class ChangeFavoritesViewModel extends ViewModel <ChangeFavoritesState>{

    public ChangeFavoritesViewModel() {
        super("Change Favorites");
        setState(new ChangeFavoritesState());
    }


}
