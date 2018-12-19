import {combineReducers} from 'redux';

import * as Data from './Data';
import * as Ui from './Ui';

export interface IApplicationState {
    readonly Data: Data.IDataState;
    readonly Ui: Ui.IUiState;
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
