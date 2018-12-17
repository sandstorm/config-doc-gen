import {combineReducers, createStore} from "redux";
import UiActions from "./Ui/UiActions";
import DataActions from "./Data/DataActions";

export default createStore(
    combineReducers({
        ui: UiActions.reducer,
        data: DataActions.reducer
    }),
    window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
);
