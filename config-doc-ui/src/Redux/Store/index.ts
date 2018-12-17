import {combineReducers} from 'redux';

import * as Data from './Data';
import * as Ui from './Ui';

export interface ApplicationState {
    readonly Data: Data.DataState;
    readonly Ui: Ui.UiState;
}

export const actions = {
    Data: Data.actions,
    Ui: Ui.actions,
};

export const selectors = {
    Data: Data.selectors,
    Ui: Ui.selectors,
};

export const rootReducer = combineReducers({
    Data: Data.reducer,
    Ui: Ui.reducer,
});
